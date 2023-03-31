package com.android.fitnessapplication;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class RecordYourMealActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_your_meal);

        // Pass text field inputs when the button is pressed
        Button recordMealButton = findViewById(R.id.recordYourMealButton);
        recordMealButton.setOnClickListener(myView -> {
            TextView cals = findViewById(R.id.recordCaloriesTextInputField);
            TextView protein = findViewById(R.id.recordProteinTextInputField);
            TextView fat = findViewById(R.id.recordFatsTextInputField);
            TextView carb = findViewById(R.id.recordCarbsTextInputField);

            // Create the intent
            Intent intentMeal = new Intent(RecordYourMealActivity.this, CalorieCounterActivity.class);
            Bundle extra = new Bundle();

            // Append the required information
            extra.putString("mealCal", String.valueOf(cals.getText()));
            extra.putString("mealprotein", String.valueOf(protein.getText()));
            extra.putString("mealfat", String.valueOf(fat.getText()));
            extra.putString("mealcarb", String.valueOf(carb.getText()));
            intentMeal.putExtras(extra);

            // Go back to calorie counter
            intentMeal.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intentMeal);
            finish();
        });
    }


}