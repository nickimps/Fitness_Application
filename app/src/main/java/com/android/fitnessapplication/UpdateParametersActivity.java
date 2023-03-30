package com.android.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
        String calorie = parameterIntent.getStringExtra("calorie_text");
        TextView totalCal = findViewById(R.id.recordCaloriesTextInputField);

        Button update = findViewById(R.id.updateCaloriesButton);
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                String totalCalorie = String.valueOf(totalCal.getText());
                myEdit.putString("caloriesRemaining", totalCalorie);
                myEdit.commit();
                Intent intent = new Intent(UpdateParametersActivity.this, CalorieCounterActivity.class);
                startActivity((intent));
            }
        });

    }




}