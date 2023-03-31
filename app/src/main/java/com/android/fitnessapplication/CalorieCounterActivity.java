package com.android.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalorieCounterActivity extends AppCompatActivity {

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_counter);

        // Initialize variables
        int totalCal = 0;
        int totalCarb = 0;
        int totalFat = 0;
        int totalProtein = 0;

        // Get shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        // Get the remaining calories value
        int remainingCalStart = Integer.parseInt(sharedPreferences.getString("caloriesRemaining", String.valueOf(2200)));

        // Get today's date and the saved date in the shared preferences
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy", Locale.CANADA);
        String formattedDate = df.format(date);
        String savedDate = sharedPreferences.getString("date", null);

        // If today's date does not match the saved date, we need to reset the values, otherwise we
        // load in the values from the shared preferences
        if (!formattedDate.equals(savedDate)) {
            myEdit.putString("date", formattedDate);
            myEdit.apply();
        } else {
            // Get the 'total' variables from shared preferences
            totalCarb = sharedPreferences.getInt("totalCarb", 0);
            totalCal = sharedPreferences.getInt("totalCal", 0);
            totalProtein = sharedPreferences.getInt("totalProtein", 0);
            totalFat = sharedPreferences.getInt("totalFat", 0);

            // Need to get the value of totalCal from record meal screen along with fat,protein and others
            Intent intent = getIntent();
            Bundle mealValues = intent.getExtras();

            // Make sure we pulled values before we access them
            if (mealValues != null) {
                totalCarb = totalCarb + Integer.parseInt(mealValues.getString("mealcarb", String.valueOf(0)));
                totalCal = totalCal + Integer.parseInt(mealValues.getString("mealCal", String.valueOf(0)));
                totalProtein = totalProtein + Integer.parseInt(mealValues.getString("mealprotein", String.valueOf(0)));
                totalFat = totalFat + Integer.parseInt(mealValues.getString("mealfat", String.valueOf(0)));
            }
        }

        // Store the update values in the shared preferences
        myEdit.putInt("totalCarb", totalCarb);
        myEdit.putInt("totalCal", totalCal);
        myEdit.putInt("totalProtein", totalProtein);
        myEdit.putInt("totalFat", totalFat);
        myEdit.apply();

        // Get the ID's of each textView
        TextView todayDate = findViewById(R.id.dateTextView);
        TextView calTotal = findViewById(R.id.totalCalTextView);
        TextView carbs = findViewById(R.id.totalCarbsTextView);
        TextView fatsView = findViewById(R.id.totalFatsTextView);
        TextView proteinView = findViewById(R.id.totalProteinTextView);
        TextView calrem = findViewById(R.id.calsRemainTextView);

        // Set the new display text of the textViews
        calTotal.setText("Total Calories: " + totalCal);
        carbs.setText("Total Carbs: " + totalCarb);
        fatsView.setText("Total Fats: " + totalFat);
        proteinView.setText("Total Protein: " + totalProtein);
        todayDate.setText("Today's Date: " + sharedPreferences.getString("date", null));
        calrem.setText("Calories Remaining: " + (remainingCalStart - totalCal));

        // When button pressed, go to record your meal screen
        CardView recordMealCardView = findViewById(R.id.recordMealCardView);
        recordMealCardView.setOnClickListener(view -> startActivity(new Intent(CalorieCounterActivity.this, RecordYourMealActivity.class)));

        // When button pressed, go to update parameters screen
        CardView updateParamsCardView = findViewById(R.id.updateParamsCardView);
        updateParamsCardView.setOnClickListener(view -> startActivity(new Intent(CalorieCounterActivity.this, UpdateParametersActivity.class)));
    }
}
