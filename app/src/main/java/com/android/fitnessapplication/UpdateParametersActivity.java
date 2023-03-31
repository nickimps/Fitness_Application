package com.android.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class UpdateParametersActivity extends AppCompatActivity {

    @Override//test
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_parameters);

        // Send daily calorie allowance to calorie counter on button press
        Button update = findViewById(R.id.updateCaloriesButton);
        update.setOnClickListener(v -> {
            TextView totalCal = findViewById(R.id.recordCaloriesTextInputField);

            // Get the shared preferences
            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();

            // Store the calories remaining value
            myEdit.putString("caloriesRemaining", String.valueOf(totalCal.getText()));
            myEdit.apply();

            // Go back to calorie counter
            startActivity(new Intent(UpdateParametersActivity.this, CalorieCounterActivity.class));
            finish();
        });

    }




}