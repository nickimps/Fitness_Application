package com.android.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CalorieCounterActivity extends AppCompatActivity {

public static int totalCal = 0;

public static int totalCarb = 0;
public static int totalFat = 0;
public static int totalProtein = 0;

public static int remainingCalStart =2000;
public static int remainingCalMoving = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_counter);

        //need to get the value of totalCal from record meal screen along with fat,protein and others

        Intent intent = getIntent();
        Intent intentMeal = getIntent();
        String calIntent = intent.getStringExtra("calorie");

        Bundle mealValues = intentMeal.getExtras();
        System.out.println(mealValues + "gamer");//for testing
        if (calIntent != null) {
            remainingCalStart = Integer.parseInt(calIntent);
        }
//test
        if (totalCal != 0 && calIntent != null){
            TextView calrem = findViewById(R.id.calsRemainTextView);
            remainingCalMoving = remainingCalStart - totalCal;
            remainingCalStart = remainingCalMoving;
            calrem.setText("Calories Remaining: " + remainingCalMoving);

        }
        try {
            String cal = mealValues.getString("mealCal");
            String carb = mealValues.getString("mealcarb");
            String fat = mealValues.getString("mealfat");
            String protein = mealValues.getString("mealprotein");

            System.out.println(protein + "paul");//for testing
            System.out.println(fat + "fewcs");//for testing
            System.out.println(cal + "sdc");//for testing
            System.out.println(fat + "chu");//for testing

            totalCarb = totalCarb + Integer.parseInt(carb);
            totalCal = totalCal + Integer.parseInt(cal);
            totalProtein = totalProtein + Integer.parseInt(protein);
            totalFat = totalFat + Integer.parseInt(fat);

            TextView calTotal = findViewById(R.id.totalCalTextView);
            TextView carbs = findViewById(R.id.totalCarbsTextView);
            TextView fatsView = findViewById(R.id.totalFatsTextView);
            TextView proteinView = findViewById(R.id.totalProteinTextView);

            calTotal.setText("Total Calories: " + String.valueOf(totalCal));
            carbs.setText("Total Carbs: " + String.valueOf(totalCarb));
            fatsView.setText("Total Fats: " + String.valueOf(totalFat));
            proteinView.setText("Total Protein: " + String.valueOf(totalProtein));

            //sets remaining calories calculations
            TextView calrem = findViewById(R.id.calsRemainTextView);
            remainingCalMoving = remainingCalStart - totalCal;
            remainingCalStart = remainingCalMoving;
            calrem.setText("Calories Remaining: " + remainingCalMoving);
            System.out.println(remainingCalMoving);//for testing
            System.out.println(remainingCalStart);

        } catch (Exception e) {

            TextView calTotal = findViewById(R.id.totalCalTextView);
            TextView carbs = findViewById(R.id.totalCarbsTextView);
            TextView fatsView = findViewById(R.id.totalFatsTextView);
            TextView proteinView = findViewById(R.id.totalProteinTextView);

            calTotal.setText("Total Calories: " + String.valueOf(totalCal));
            carbs.setText("Total Carbs: " + String.valueOf(totalCarb));
            fatsView.setText("Total Fats: " + String.valueOf(totalFat));
            proteinView.setText("Total Protein: " + String.valueOf(totalProtein));

            //sets remaining calories calculations
            TextView calrem = findViewById(R.id.calsRemainTextView);

            calrem.setText("Calories Remaining: " + remainingCalStart);
        }


        CardView recordMealCardView = findViewById(R.id.recordMealCardView);
        recordMealCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Go to register screen on click
                Intent intent = new Intent(CalorieCounterActivity.this, RecordYourMealActivity.class);
                startActivity((intent));
            }
        });

        CardView updateParamsCardView = findViewById(R.id.updateParamsCardView);
        updateParamsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Go to register screen on click
                Intent intent = new Intent(CalorieCounterActivity.this, UpdateParametersActivity.class);
                startActivity((intent));
            }
        });
    }
}