package com.android.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Add click functionality for workoutsCardView
        CardView workoutsCardView = findViewById(R.id.workoutsCardView);
        workoutsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to register screen on click
                Intent intent = new Intent(MainActivity.this, WorkoutsActivity.class);
                startActivity((intent));
            }
        });

        //Add click functionality for customWorkoutCardView
        CardView customWorkoutCardView = findViewById(R.id.customWorkoutCardView);
        customWorkoutCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to register screen on click
                Intent intent = new Intent(MainActivity.this, CreateWorkoutActivity.class);
                startActivity((intent));
            }
        });

        //Add click functionality for calorieCounterCardView
        CardView calorieCounterCardView = findViewById(R.id.calorieCounterCardView);
        calorieCounterCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to register screen on click
                Intent intent = new Intent(MainActivity.this, CalorieCounterActivity.class);
                startActivity((intent));
            }
        });

        //Add click functionality for recordWeightCardView
        CardView recordWeightCardView = findViewById(R.id.recordWeightCardView);
        recordWeightCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to register screen on click
                Intent intent = new Intent(MainActivity.this, RecordWeightActivity.class);
                startActivity((intent));
            }
        });

    }
}