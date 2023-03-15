package com.android.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalorieCounterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_counter);

        CardView recordMealCardView = findViewById(R.id.recordMealCardView);
        recordMealCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to register screen on click
                Intent intent = new Intent(CalorieCounterActivity.this, RecordYourMealActivity.class);
                startActivity((intent));
            }
        });

    }

}