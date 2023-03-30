package com.android.fitnessapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.stream.IntStream;

public class RecordWeightActivity extends AppCompatActivity {
    private Calendar calendar;
    private Button chooseDateButton;
    private NumberPicker newWeightNumberPicker, newWeightDecimalNumberPicker, goalWeightNumberPicker, goalWeightDecimalNumberPicker, heightNumberPicker;
    private int day, month, year;
    TextView currentBMI;
    Context context;

    LineGraphSeries<DataPoint> weightHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_weight);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE); // weightNumber, weightDecimal, heightNumber
        int weightNumber = sharedPreferences.getInt("weightNumber", 0);
        int weightDecimal = sharedPreferences.getInt("weightDecimal", 0);
        int heightNumber = sharedPreferences.getInt("heightNumber", 0);

        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        currentBMI = findViewById(R.id.currentBMITextView);
        /*
        double BMI = LOAD THE LAST SET BMI FROM THE SHARED PREF

        if (no BMI value saved in SharedPref) {
            currentBMI.setText("No BMI");
        } else {
            //Display BMI
            String bmiString = String.format(Locale.getDefault(),"Current BMI: %.3f", BMI);
            currentBMI.setText(bmiString);
        }
        */

        // For the record number pickers -----------------------------------------------------------
        newWeightNumberPicker = findViewById(R.id.newWeightNumberPicker);
        newWeightNumberPicker.setMinValue(15);
        newWeightNumberPicker.setMaxValue(250);
        newWeightNumberPicker.setValue(weightNumber);
        newWeightNumberPicker.setWrapSelectorWheel(false);

        newWeightDecimalNumberPicker = findViewById(R.id.newWeightDecimalNumberPicker);
        newWeightDecimalNumberPicker.setMinValue(0);
        newWeightDecimalNumberPicker.setMaxValue(9);
        newWeightDecimalNumberPicker.setValue(weightDecimal);
        newWeightDecimalNumberPicker.setWrapSelectorWheel(true);

        // Clicking this button brings up a date picker and then changes the button to be the new date that was selected
        chooseDateButton = findViewById(R.id.chooseDateButton);
        chooseDateButton.setOnClickListener(view -> {
            calendar = Calendar.getInstance();
            day = calendar.get(Calendar.DAY_OF_MONTH);
            month = calendar.get(Calendar.MONTH);
            year = calendar.get(Calendar.YEAR);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    RecordWeightActivity.this,
                    (datePicker, year, month, day) -> chooseDateButton.setText(String.format(Locale.getDefault(),month + "/" + day + "/" + year)),
                    year, month, day
            );
            datePickerDialog.show();
        });
        //------------------------------------------------------------------------------------------

        // For the goal number pickers -------------------------------------------------------------
        goalWeightNumberPicker = findViewById(R.id.goalWeightNumberPicker);
        goalWeightNumberPicker.setMinValue(15);
        goalWeightNumberPicker.setMaxValue(200);
        goalWeightNumberPicker.setValue(weightNumber);
        goalWeightNumberPicker.setWrapSelectorWheel(false);

        // Update things when the number is adjusted
        goalWeightNumberPicker.setOnValueChangedListener((numberPicker, i, i1) -> {
            myEdit.putInt("weightNumber", goalWeightNumberPicker.getValue());
            myEdit.apply();

            // UPDATE THE GOAL LINE ON GRAPH ACCORDINGLY
        });

        goalWeightDecimalNumberPicker = findViewById(R.id.goalWeightDecimalNumberPicker);
        goalWeightDecimalNumberPicker.setMinValue(0);
        goalWeightDecimalNumberPicker.setMaxValue(9);
        goalWeightDecimalNumberPicker.setValue(weightDecimal);
        goalWeightDecimalNumberPicker.setWrapSelectorWheel(true);

        // Update things when the number is adjusted
        goalWeightDecimalNumberPicker.setOnValueChangedListener((numberPicker, i, i1) -> {
            myEdit.putInt("weightDecimal", goalWeightDecimalNumberPicker.getValue());
            myEdit.apply();

            // UPDATE THE GOAL LINE ON GRAPH ACCORDINGLY
        });

        heightNumberPicker = findViewById(R.id.heightNumberPicker);
        heightNumberPicker.setMinValue(30);
        heightNumberPicker.setMaxValue(285);
        heightNumberPicker.setValue(heightNumber);               // SET THIS TO THE SAVED CURRENT HEIGHT OF THEM
        heightNumberPicker.setWrapSelectorWheel(true);

        // Update things when the number is adjusted
        heightNumberPicker.setOnValueChangedListener((numberPicker, i, i1) -> {
            myEdit.putInt("heightNumber", heightNumberPicker.getValue());
            myEdit.apply();

            // UPDATE BMI IF NECESSARY?
        });
        //------------------------------------------------------------------------------------------



        /*


         */

        // this is where the graph is going for weight history
//        GraphView graphView = findViewById(R.id.weightGraph);
//        weightHistory = new LineGraphSeries<>();
//        for(int i = 0, i < length of recorded weights; i++) {
//            weightHistory.appendData(new DataPoint(x, y), true, length of weight history);
//        }
//        graphView.addSeries(weightHistory);

        Button recordWeightButton = findViewById(R.id.recordWeightButton);
        recordWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get date and log it.
                String buttonDate = String.valueOf(chooseDateButton.getText());

                //Check if a date is chosen before recording activity
                if (buttonDate.equals("Choose Date")) {
                    Toast.makeText(getApplicationContext(),"Please choose a date",Toast.LENGTH_SHORT).show();
                } else {
                    //Implementations for GraphView (To be added at a future date)
                    Date date = calendar.getTime();
                    System.out.println(date);

                    int month = Integer.parseInt(buttonDate.split("/")[0]);
                    int day = Integer.parseInt(buttonDate.split("/")[1]);
                    int year = Integer.parseInt(buttonDate.split("/")[2]);

                    //write to text file
//                    try {
//                        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("weight.txt", Context.MODE_PRIVATE));
//                        outputStreamWriter.write("Text to write to the file");
//                        outputStreamWriter.close();
//                    }
//                    catch (IOException e) {
//                        Log.e("Exception", "File write failed: " + e.toString());
//                    }

                    // I think it should override the value if the date already exists in the table
                    // USE THE DATE TO SAVE WITH THE NEW weightKG VALUE

                    //Grab height (Shared Pref)
                    double height = sharedPreferences.getInt("heightNumber", 0);
                    height = height / 100;

                    //Grab Newly Recorded Weight
                    String weightFromPicker = newWeightNumberPicker.getValue() + "." + newWeightDecimalNumberPicker.getValue();
                    double weightKG = Float.parseFloat(weightFromPicker);

                    //Calculate BMI
                    double BMI = weightKG / (height * height) ;

                    //Display BMI
                    String bmiString = String.format(Locale.getDefault(),"Current BMI: %.3f", BMI);
                    currentBMI.setText(bmiString);

                    //Graph View Implementation

                }
            }
        });
    }
}