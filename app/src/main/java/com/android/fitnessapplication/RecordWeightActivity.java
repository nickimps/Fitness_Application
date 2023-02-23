package com.android.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class RecordWeightActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_weight);

        Button recordWeightButton = findViewById(R.id.recordWeightButton);
        recordWeightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get date and log it.

                //Grab height (Shared Pref)
                double height = 178 / 100;

                //Grab Current Weight
                TextInputEditText weightText = findViewById(R.id.weightOnDateTextInputField);
                double weightKG = Float.valueOf(weightText.getText().toString()) / 2.205;

                //Calc BMI
                double BMI = weightKG / (height * height) ;

                //Display BMI
                TextView currentBMI = findViewById(R.id.currentBMITextView);
                currentBMI.setText("Current BMI" + BMI);
            }
        });

    }

}