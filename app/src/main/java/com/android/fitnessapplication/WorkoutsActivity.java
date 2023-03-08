package com.android.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WorkoutsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);

        //Add click functionality for chestCardView
        CardView chestCardView = findViewById(R.id.chestCardView);
        chestCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to register screen on click
                Intent intent = new Intent(WorkoutsActivity.this, ChooseWorkoutsActivity.class);
                intent.putExtra("workout_type", "Chest");
                startActivity((intent));
            }
        });

        //Add click functionality for shouldersCardView
        CardView shouldersCardView = findViewById(R.id.shouldersCardView);
        shouldersCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to register screen on click
                Intent intent = new Intent(WorkoutsActivity.this, ChooseWorkoutsActivity.class);
                intent.putExtra("workout_type", "Shoulders");
                startActivity((intent));
            }
        });

        //Add click functionality for bicepsCardView
        CardView bicepsCardView = findViewById(R.id.bicepsCardView);
        bicepsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to register screen on click
                Intent intent = new Intent(WorkoutsActivity.this, ChooseWorkoutsActivity.class);
                intent.putExtra("workout_type", "Biceps");
                startActivity((intent));
            }
        });

        //Add click functionality for tricepsCardView
        CardView tricepsCardView = findViewById(R.id.tricepsCardView);
        tricepsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to register screen on click
                Intent intent = new Intent(WorkoutsActivity.this, ChooseWorkoutsActivity.class);
                intent.putExtra("workout_type", "Triceps");
                startActivity((intent));
            }
        });

        //Add click functionality for backCardView
        CardView backCardView = findViewById(R.id.backCardView);
        backCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to register screen on click
                Intent intent = new Intent(WorkoutsActivity.this, ChooseWorkoutsActivity.class);
                intent.putExtra("workout_type", "Back");
                startActivity((intent));
            }
        });

        //Add click functionality for absCardView
        CardView absCardView = findViewById(R.id.absCardView);
        absCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to register screen on click
                Intent intent = new Intent(WorkoutsActivity.this, ChooseWorkoutsActivity.class);
                intent.putExtra("workout_type", "Abs & Obliques");
                startActivity((intent));
            }
        });

        //Add click functionality for legsCardView
        CardView legsCardView = findViewById(R.id.legsCardView);
        legsCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to register screen on click
                Intent intent = new Intent(WorkoutsActivity.this, ChooseWorkoutsActivity.class);
                intent.putExtra("workout_type", "Legs");
                startActivity((intent));
            }
        });

        //Add click functionality for BumCardView
        CardView BumCardView = findViewById(R.id.BumCardView);
        BumCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Go to register screen on click
                Intent intent = new Intent(WorkoutsActivity.this, ChooseWorkoutsActivity.class);
                intent.putExtra("workout_type", "Glutes");
                startActivity((intent));
            }
        });
    }


}