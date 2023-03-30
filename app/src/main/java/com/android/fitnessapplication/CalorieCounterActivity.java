package com.android.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalorieCounterActivity extends AppCompatActivity {

    public static int totalCal = 0;

    public static int totalCarb = 0;
    public static int totalFat = 0;
    public static int totalProtein = 0;

    public static int remainingCalStart;
    public static int remainingCalMoving = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_counter);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();

        remainingCalStart = Integer.parseInt(sharedPreferences.getString("caloriesRemaining", String.valueOf(2200)));

        //need to get the value of totalCal from record meal screen along with fat,protein and others
        Intent intent = getIntent();
        Intent intentMeal = getIntent();
        String calIntent = intent.getStringExtra("calorie");

        System.out.println(calIntent);

        Date c = Calendar.getInstance().getTime();

        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy", Locale.CANADA);
        String formattedDate = df.format(c);

        String savedDate = sharedPreferences.getString("date", null);

        String dailyLimit = sharedPreferences.getString("allowedCals", null);
        //System.out.println(lastDate);
        System.out.println(dailyLimit);

        Bundle mealValues = intentMeal.getExtras();
        System.out.println(mealValues + "gamer");//for testing
        if (calIntent != null) {
            remainingCalStart = Integer.parseInt(calIntent);
        }

        if (totalCal != 0 && calIntent != null) {
            TextView calrem = findViewById(R.id.calsRemainTextView);
            remainingCalMoving = remainingCalStart - totalCal;
            remainingCalStart = remainingCalMoving;
            calrem.setText("Calories Remaining: " + remainingCalMoving);

        }

        if (!formattedDate.equals(savedDate)) {
            myEdit.putString("date", formattedDate);
            myEdit.commit();
            totalCal = 0;
            totalCarb = 0;
            totalFat = 0;
            totalProtein = 0;
        } else {
            totalCarb = sharedPreferences.getInt("totalCarb", 0);
            totalCal = sharedPreferences.getInt("totalCal", 0);
            totalProtein = sharedPreferences.getInt("totalProtein", 0);
            totalFat = sharedPreferences.getInt("totalFat", 0);
            if (mealValues != null) {
                String cal = mealValues.getString("mealCal", String.valueOf(0));
                String carb = mealValues.getString("mealcarb", String.valueOf(0));
                String fat = mealValues.getString("mealfat", String.valueOf(0));
                String protein = mealValues.getString("mealprotein", String.valueOf(0));

                totalCarb = totalCarb + Integer.parseInt(carb);
                totalCal = totalCal + Integer.parseInt(cal);
                totalProtein = totalProtein + Integer.parseInt(protein);
                totalFat = totalFat + Integer.parseInt(fat);
            }
        }

        myEdit.putInt("totalCarb", totalCarb);
        myEdit.putInt("totalCal", totalCal);
        myEdit.putInt("totalProtein", totalProtein);
        myEdit.putInt("totalFat", totalFat);
        myEdit.commit();

        TextView todayDate = findViewById(R.id.dateTextView);
        TextView calTotal = findViewById(R.id.totalCalTextView);
        TextView carbs = findViewById(R.id.totalCarbsTextView);
        TextView fatsView = findViewById(R.id.totalFatsTextView);
        TextView proteinView = findViewById(R.id.totalProteinTextView);

        calTotal.setText("Total Calories: " + String.valueOf(totalCal));
        carbs.setText("Total Carbs: " + String.valueOf(totalCarb));
        fatsView.setText("Total Fats: " + String.valueOf(totalFat));
        proteinView.setText("Total Protein: " + String.valueOf(totalProtein));
        todayDate.setText("Today's Date: " + sharedPreferences.getString("date", null));

        //sets remaining calories calculations
        TextView calrem = findViewById(R.id.calsRemainTextView);
        remainingCalMoving = remainingCalStart - totalCal;
        remainingCalStart = remainingCalMoving;
        calrem.setText("Calories Remaining: " + remainingCalMoving);
        System.out.println(remainingCalMoving);//for testing
        System.out.println(remainingCalStart);

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
