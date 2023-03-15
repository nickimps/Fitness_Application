package com.android.fitnessapplication;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class RecordYourMealActivity extends AppCompatActivity {

    private NumberPicker getProteinPicker, getFatPicker, getCarbsPicker, getCaloriesPicker;
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
                getCaloriesPicker.setMinValue(1);
                getCaloriesPicker.setWrapSelectorWheel(false);

                getProteinPicker = findViewById(R.id.recordProteinTextInputField);
                getProteinPicker.setMinValue(1);
                getProteinPicker.setWrapSelectorWheel(false);

                getFatPicker = findViewById(R.id.recordFatsTextInputField);
                getFatPicker.setMinValue(1);
                getFatPicker.setWrapSelectorWheel(false);

                getCarbsPicker = findViewById(R.id.recordCarbsTextInputField);
                getCarbsPicker.setMinValue(1);
                getCarbsPicker.setWrapSelectorWheel(false);
                int calories = getCaloriesPicker.getValue();
                int protein = getProteinPicker.getValue();
                int carbs = getFatPicker.getValue();
                int fats = getCarbsPicker.getValue();
            }
        });
    }


}