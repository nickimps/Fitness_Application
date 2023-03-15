package com.android.fitnessapplication;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;

public class RecordYourMealActivity extends AppCompatActivity {

    private TextInputEditText getProteinPicker, getFatPicker, getCarbsPicker, getCaloriesPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_your_meal);

        Button recordMealButton = findViewById(R.id.recordYourMealButton);
        //Making the functions of the save the meal below
        recordMealButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View myView) {
                getCaloriesPicker = findViewById(R.id.recordCaloriesTextInputField);
                getProteinPicker = findViewById(R.id.recordProteinTextInputField);
                getFatPicker = findViewById(R.id.recordFatsTextInputField);
                getCarbsPicker = findViewById(R.id.recordCarbsTextInputField);

                //Update this to store as integer values, temporary fix
                String calories = getCaloriesPicker.getText().toString();
                String protein = getProteinPicker.getText().toString();
                String fats = getFatPicker.getText().toString();
                String carbs = getCarbsPicker.getText().toString();

                //Test, remove when fixed
                System.out.println(calories);
                System.out.println(protein);
                System.out.println(carbs);
                System.out.println(fats);
            }
        });
    }


}