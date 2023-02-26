package com.android.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;

import java.util.ArrayList;

public class SetWorkoutParametersActivity extends AppCompatActivity {

    private NumberPicker setsNumberPicker;
    private Button beginWorkoutButton;
    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<ExerciseTypeObject> exercise_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_workout_parameters);

        Intent intent = getIntent();
        ArrayList<String> selected_exercise_list = intent.getStringArrayListExtra("exercise_list");

        beginWorkoutButton = findViewById(R.id.beginWorkoutButton);
        beginWorkoutButton.setOnClickListener(view -> {
            Intent intent2 = new Intent(SetWorkoutParametersActivity.this, PerformWorkoutActivity.class);
            intent2.putExtra("workout_name", "Custom Workout");     // Workout name
            int numSets = setsNumberPicker.getValue();
            intent2.putExtra("sets", numSets);                            // Number of Sets
            intent2.putExtra("exercise_list", selected_exercise_list);    // Exercise List

            // Get the number picker values from each card
            ArrayList<String> rep_list = new ArrayList<>();
            for (int i = 0; i < recyclerView.getChildCount(); i++) {
                RecyclerView.ViewHolder holder = recyclerView.getChildViewHolder(recyclerView.getChildAt(i));
                NumberPicker numberPicker = holder.itemView.findViewById(R.id.repsNumberPicker);
                rep_list.add(String.valueOf(numberPicker.getValue()));
            }
            intent2.putExtra("rep_list", rep_list);                       // Number of Reps

            startActivity((intent2));
        });

        // Set the default values for the sets number picker
        setsNumberPicker = findViewById(R.id.setsNumberPicker);
        setsNumberPicker.setMinValue(1);
        setsNumberPicker.setMaxValue(50);
        setsNumberPicker.setValue(5);
        setsNumberPicker.setWrapSelectorWheel(false);

        // Set up the recyclerview
        recyclerView = (RecyclerView) findViewById(R.id.listOfExercisesRecyclerView);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        exercise_list = new ArrayList<>();
        for (int i = 0; i < selected_exercise_list.size(); i++)
            exercise_list.add(new ExerciseTypeObject(selected_exercise_list.get(i)));

        adapter = new RecyclerAdapterChooseReps(exercise_list);
        recyclerView.setAdapter(adapter);
    }
}