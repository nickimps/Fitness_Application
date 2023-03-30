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
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
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

        try {
            plotPoints();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE); // weightNumber, weightDecimal, heightNumber
        int weightNumber = sharedPreferences.getInt("weightNumber", 0);
        int weightDecimal = sharedPreferences.getInt("weightDecimal", 0);
        int heightNumber = sharedPreferences.getInt("heightNumber", 0);

        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        //Display BMI
        float bmi_number = Float.parseFloat(sharedPreferences.getString("BMI", "0.000"));
        String bmi_string = String.format(Locale.getDefault(),"Current BMI: %.3f", bmi_number);
        currentBMI = findViewById(R.id.currentBMITextView);
        currentBMI.setText(bmi_string);

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
                    (datePicker, year, month, day) -> chooseDateButton.setText(String.format(Locale.getDefault(),(month + 1) + "/" + day + "/" + year)),
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

            load last weight and adjust BMI on load

         */

        // this is where the graph is going for weight history
//        GraphView graphView = findViewById(R.id.weightGraph);
//        weightHistory = new LineGraphSeries<>();
//        for(int i = 0, i < length of recorded weights; i++) {
//            weightHistory.appendData(new DataPoint(x, y), true, length of weight history);
//        }
//        graphView.addSeries(weightHistory);

        Button recordWeightButton = findViewById(R.id.recordWeightButton);
        recordWeightButton.setOnClickListener(v -> {
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

                // Save BMI to shared pref
                myEdit.putString("BMI", String.valueOf(BMI));
                myEdit.apply();

                //write to text file
                String[] data_to_write = {month + "/" + day + "/" + year, weightFromPicker};
                saveLocationToFile(data_to_write);

                try {
                    plotPoints();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }
                //Graph View Implementation

            }
        });
    }

    /**
     * Saves a file for the data
     *
     * @param data The data to be saved to the file
     */
    private void saveLocationToFile(String[] data) {
        try {
            // Check if the file exists
            if (new File(RecordWeightActivity.this.getFilesDir() + "/past_weights.txt").exists()) {
                // Load in the file to check for duplicate dates
                ArrayList<String[]> past_weights = readFromFile();

                // Check if there is a dupe
                int index_of_match = -1;
                for (String[] date : past_weights) {
                    if (data[0].equals(date[0]))
                        index_of_match = past_weights.indexOf(date);
                }



                // Get path of the text file
                Path path = Paths.get(RecordWeightActivity.this.getFilesDir() + "/past_weights.txt");

                // If there was no match, then append, otherwise, need to adjust text file
                if (index_of_match == -1) {
                    String save_string = data[0] + "," + data[1] + "\n";
                    Files.write(path, save_string.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                } else {
                    // Remove the dupe and update with the new entry
                    past_weights.remove(index_of_match);
                    past_weights.add(data);

                    // We proceed in re-writing each line, the first time through we want to erase the file and then subsequent times we will append
                    boolean truncate = true;
                    for (String[] date : past_weights) {
                        String save_string = date[0] + "," + date[1] + "\n";

                        if (truncate) {
                            Files.write(path, save_string.getBytes(StandardCharsets.UTF_8), StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
                            truncate = false;
                        } else {
                            Files.write(path, save_string.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                        }
                    }
                }
            } else {
                File create_file = new File(RecordWeightActivity.this.getFilesDir(), "past_weights.txt");
                create_file.createNewFile();
                // Get path of the text file
                Path path = Paths.get(RecordWeightActivity.this.getFilesDir() + "/past_weights.txt");
                String save_string = data[0] + "," + data[1] + "\n";
                Files.write(path, save_string.getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Reads the text file from the location in the phone and parses the return accordingly
     *
     * @return Arraylist of the values
     */
    private ArrayList<String[]> readFromFile() {
        if (new File(RecordWeightActivity.this.getFilesDir() + "/past_weights.txt").exists()) {
            ArrayList<String[]> past_weights = new ArrayList<>();

            try {
                File file = new File(RecordWeightActivity.this.getFilesDir(), "past_weights.txt");
                Scanner scanner = new Scanner(file);

                while(scanner.hasNextLine())
                    past_weights.add(scanner.nextLine().split(",")); // date = [0], weight = [1]

            } catch (Exception e) {
                past_weights.add(new String[]{"", ""});
            }

            return past_weights;
        }
        return null;
    }

    public void plotPoints() throws IOException, ParseException {
        if (new File(RecordWeightActivity.this.getFilesDir() + "/past_weights.txt").exists()) {
            List<String> lines = Files.readAllLines(Paths.get(RecordWeightActivity.this.getFilesDir() + "/past_weights.txt"));

            // Convert lines to DataPoint objects
            List<DataPoint> dataPoints = new ArrayList<>();
            for (String line : lines) {
                String[] values = line.split(",");
                long x = new SimpleDateFormat("MM/dd/yyyy").parse(values[0]).getTime();
                double y = Double.parseDouble(values[1]);
                dataPoints.add(new DataPoint(x, y));
            }

            // Sort DataPoint objects by X values
            Collections.sort(dataPoints, new Comparator<DataPoint>() {
                @Override
                public int compare(DataPoint dp1, DataPoint dp2) {
                    return Long.compare((long) dp1.getX(), (long) dp2.getX());
                }
            });

            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(dataPoints.toArray(new DataPoint[0]));
            GraphView graphView = findViewById(R.id.weightGraph);
            graphView.removeAllSeries();
            graphView.addSeries(series);
            graphView.getGridLabelRenderer().setLabelFormatter(new DateAsXAxisLabelFormatter(this));
            graphView.getGridLabelRenderer().setNumHorizontalLabels(3);
            graphView.getGridLabelRenderer().setHumanRounding(false);
            graphView.getViewport().setMinX(dataPoints.get(0).getX());
            graphView.getViewport().setMaxX(dataPoints.get(dataPoints.size() - 1).getX());
            graphView.getViewport().setXAxisBoundsManual(true);
        } else {
            File create_file = new File(RecordWeightActivity.this.getFilesDir(), "past_weights.txt");
            create_file.createNewFile();
        }
    }
}