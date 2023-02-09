package com.android.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class PerformWorkoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perform_workout);

        //Default hide the pause button until the start button is clicked
        View buttonGapView = findViewById(R.id.buttonGapView);
        Button pauseButton = findViewById(R.id.pauseButton);
        buttonGapView.setVisibility(View.GONE);
        pauseButton.setVisibility(View.GONE);
    }
}