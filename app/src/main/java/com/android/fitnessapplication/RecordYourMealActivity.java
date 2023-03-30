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

//test
        Intent mealIntent = getIntent();
        Bundle extra = new Bundle();
        String mealCal = mealIntent.getStringExtra("mealcalorie");
        TextView cals = findViewById(R.id.recordCaloriesTextInputField);
        String mealprotein = mealIntent.getStringExtra("mealprotein");
        TextView protein = findViewById(R.id.recordProteinTextInputField);
        String mealfat = mealIntent.getStringExtra("mealfat");
        TextView fat = findViewById(R.id.recordFatsTextInputField);
        String mealcarb = mealIntent.getStringExtra("mealcarbs");
        TextView carb = findViewById(R.id.recordCarbsTextInputField);


        Button recordMealButton = findViewById(R.id.recordYourMealButton);
        //Making the functions of the save the meal below
        recordMealButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View myView) {
                String calsVar = String.valueOf(cals.getText());
                String proVar = String.valueOf(protein.getText());
                String fatVar = String.valueOf(fat.getText());
                String carbVar = String.valueOf(carb.getText());
                Intent intentMeal = new Intent(RecordYourMealActivity.this, CalorieCounterActivity.class);
                System.out.println(calsVar + "bruh");
                System.out.println(fatVar);

                extra.putString("mealCal", calsVar);
                extra.putString("mealprotein", proVar);
                extra.putString("mealfat", fatVar);
                extra.putString("mealcarb", carbVar);
                intentMeal.putExtras(extra);
                startActivity((intentMeal));
            }
        });
    }


}