package com.android.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CalorieCounterActivity extends AppCompatActivity {

public int totalCal = 0;

public int totalCarb = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_counter);

        //need to get the value of totalCal from record meal screen along with fat,protein and others

        Intent intent = getIntent();
        Intent carbIntents = getIntent();
        String calIntent = intent.getStringExtra("calorie");
        String carbIntent = carbIntents.getStringExtra("carb");

        System.out.print(carbIntent + "paul");//for testing
        if (carbIntent != null){
            totalCarb = totalCarb + Integer.parseInt(calIntent);
        }

        TextView calTotal = findViewById(R.id.totalCalTextView);
        TextView carbs = findViewById(R.id.totalCarbsTextView);
        calTotal.setText(String.valueOf(totalCal));
        carbs.setText(String.valueOf(totalCarb));

        //sets remaining calories calculations
        TextView calrem = findViewById(R.id.calsRemainTextView);
        calrem.setText(calIntent);





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