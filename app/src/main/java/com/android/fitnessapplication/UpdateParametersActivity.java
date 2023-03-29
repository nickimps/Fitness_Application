package com.android.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UpdateParametersActivity extends AppCompatActivity {


    @Override//test
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_parameters);




        Intent parameterIntent = getIntent();
        String calroie = parameterIntent.getStringExtra("calorie_text");
        TextView totalCal = findViewById(R.id.recordCaloriesTextInputField);
        //TextView height = findViewById(R.id.heightTextInputField);
        //TextView goalWeight = findViewById(R.id.goalTextInputField);

        Button update = findViewById(R.id.recordYourMealButton);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String totalCalorie = String.valueOf(totalCal.getText());
                Intent intent = new Intent(UpdateParametersActivity.this, CalorieCounterActivity.class);
                intent.putExtra("calorie", totalCalorie);
                startActivity((intent));
            }
        });

    }




}