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

        Intent mealIntent = getIntent();
        Bundle extra = new Bundle();
        String mealCal = mealIntent.getStringExtra("mealcalorie");
        TextView cals = findViewById(R.id.recordCaloriesTextInputField);
        cals.setText("0");
        String mealprotein = mealIntent.getStringExtra("mealprotein");
        TextView protein = findViewById(R.id.recordProteinTextInputField);
        protein.setText("0");
        String mealfat = mealIntent.getStringExtra("mealfat");
        TextView fat = findViewById(R.id.recordFatsTextInputField);
        fat.setText("0");
        String mealcarb = mealIntent.getStringExtra("mealcarbs");
        TextView carb = findViewById(R.id.recordCarbsTextInputField);
        carb.setText("0");


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

                /*
                System.out.println(calsVar);//for testing

                String proVar = String.valueOf(protein.getText());
                Intent intentprotein = new Intent(RecordYourMealActivity.this, CalorieCounterActivity.class);
                intentprotein.putExtra("protein", proVar);
                startActivity((intentprotein));

                String fatVar = String.valueOf(fat.getText());
                Intent intentfat = new Intent(RecordYourMealActivity.this, CalorieCounterActivity.class);
                intentfat.putExtra("fat", fatVar);
                startActivity((intentfat));
                System.out.println(fatVar);//for testing

                String carbVar = String.valueOf(carb.getText());
                Intent intentcarb = new Intent(RecordYourMealActivity.this, CalorieCounterActivity.class);
                intentcarb.putExtra("carb", carbVar);
                startActivity((intentcarb));
*/
            }
        });
    }


}