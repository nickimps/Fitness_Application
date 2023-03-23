package com.android.fitnessapplication;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;

public class RecordYourMealActivity extends AppCompatActivity {

    private TextInputEditText getProteinPicker, getFatPicker, getCarbsPicker, getCaloriesPicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_your_meal);

        Intent mealCalIntent = getIntent();
        String mealCal = mealCalIntent.getStringExtra("mealcalorie");
        TextView cals = findViewById(R.id.recordCaloriesTextInputField);

        Intent mealproIntent = getIntent();
        String mealpro = mealproIntent.getStringExtra("mealprotein");
        TextView protein = findViewById(R.id.recordProteinTextInputField);

        Intent mealfatIntent = getIntent();
        String mealfat = mealfatIntent.getStringExtra("mealfat");
        TextView fat = findViewById(R.id.recordFatsTextInputField);

        Intent mealcarbIntent = getIntent();
        String mealcarb = mealcarbIntent.getStringExtra("mealcarbs");
        TextView carb = findViewById(R.id.recordCarbsTextInputField);

        Button recordMealButton = findViewById(R.id.recordYourMealButton);
        //Making the functions of the save the meal below
        recordMealButton.setOnClickListener(new View.OnClickListener(){


            @Override
            public void onClick(View myView) {
                String calsVar = String.valueOf(cals.getText());
                Intent intent = new Intent(RecordYourMealActivity.this, CalorieCounterActivity.class);
                intent.putExtra("calorie", calsVar);
                startActivity((intent));

                String proVar = String.valueOf(protein.getText());
                Intent intentprotein = new Intent(RecordYourMealActivity.this, CalorieCounterActivity.class);
                intentprotein.putExtra("protein", proVar);
                startActivity((intentprotein));

                String fatVar = String.valueOf(fat.getText());
                Intent intentfat = new Intent(RecordYourMealActivity.this, CalorieCounterActivity.class);
                intentfat.putExtra("fat", fatVar);
                startActivity((intentfat));

                String carbVar = String.valueOf(carb.getText());
                Intent intentcarb = new Intent(RecordYourMealActivity.this, CalorieCounterActivity.class);
                intentfat.putExtra("carb", carbVar);
                startActivity((intentcarb));
            }
        });
    }


}