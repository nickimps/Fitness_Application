package com.android.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.NumberPicker;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class PerformWorkoutActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<WorkoutTypeObject> exercise_name_list, rep_numbers_list;
    public static View.OnClickListener myOnClickListener;

    private static int max_num_of_sets, current_set_num;

    View buttonGapView;
    Button pauseButton, startStopButton, logButton;
    TextView setsRemainingTextView;

    long chronoTimeWhenPaused;
    Boolean timerActive;
    Chronometer timerChronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perform_workout);

        // Change workout name
        Intent intent = getIntent();
        String workoutName = intent.getStringExtra("workout_name");
        TextView workoutNameHeaderTextView = findViewById(R.id.workoutNameHeaderTextView);
        workoutNameHeaderTextView.setText(workoutName);

        exercise_name_list = new ArrayList<>();
        rep_numbers_list = new ArrayList<>();
        if (workoutName.equals("Custom Workout")) {
            max_num_of_sets = intent.getIntExtra("sets", 0);

            ArrayList<String> string_exercise_list = intent.getStringArrayListExtra("exercise_list");
            ArrayList<String> string_rep_list = intent.getStringArrayListExtra("rep_list");

            for (int i = 0; i < string_exercise_list.size(); i++) {
                exercise_name_list.add(new WorkoutTypeObject(string_exercise_list.get(i), 0));
                rep_numbers_list.add(new WorkoutTypeObject(string_rep_list.get(i), 1));
            }
        } else {
            max_num_of_sets = Integer.parseInt(ListOfWorkouts.listOfWorkouts[0][2]);

            int workout_index = 0;
            for (int i = 0; i < ListOfWorkouts.listOfWorkouts.length; i++){
                if (workoutName.equals(ListOfWorkouts.listOfWorkouts[i][0])) {
                    workout_index = i;
                    break;
                }
            }

            for (int i = 3; i < ListOfWorkouts.listOfWorkouts[0].length; i++) {
                exercise_name_list.add(new WorkoutTypeObject(ListOfWorkouts.listOfWorkouts[workout_index][i], 0));
                rep_numbers_list.add(new WorkoutTypeObject(ListOfWorkouts.listOfWorkouts[workout_index][++i], 1));
            }
        }

        startStopButton = (Button) findViewById(R.id.startStopButton);
        buttonGapView = findViewById(R.id.buttonGapView);
        pauseButton = findViewById(R.id.pauseButton);
        logButton = findViewById(R.id.logButton);
        setsRemainingTextView = findViewById(R.id.setsRemainingTextView);

        chronoTimeWhenPaused = 0;
        timerActive = false;
        timerChronometer = findViewById(R.id.timerChronometer);

        // Get number of sets to show
        current_set_num = 0;
        setsRemainingTextView.setText(String.format(Locale.getDefault(),"%d/%d", current_set_num, max_num_of_sets));

        // Default hide the pause button until the start button is clicked
        buttonGapView.setVisibility(View.GONE);
        pauseButton.setVisibility(View.GONE);

        // When start/stop button has been pressed
        startStopButton.setOnClickListener(view -> {
            if (startStopButton.getText().toString().equals("START")) {
                //startStopButton.setBackgroundColor(getResources().getColor(R.color.stop_red));
                startStopButton.setBackgroundColor(ContextCompat.getColor(this, R.color.stop_red));
                startStopButton.setText(getResources().getString(R.string.stop));

                timerChronometer.setBase(SystemClock.elapsedRealtime() + chronoTimeWhenPaused);
                timerChronometer.start();
                timerActive = true;


                buttonGapView.setVisibility(View.VISIBLE);
                pauseButton.setVisibility(View.VISIBLE);
            } else {
                startStopButton.setBackgroundColor(ContextCompat.getColor(this, R.color.start_green));
                startStopButton.setText(getResources().getString(R.string.start));

                // show just start button (don't' reset timer)
                buttonGapView.setVisibility(View.GONE);
                pauseButton.setVisibility(View.GONE);

                timerChronometer.setBase(SystemClock.elapsedRealtime());
                chronoTimeWhenPaused = 0;
                timerChronometer.stop();
                timerActive = false;

                // Reset the set number
                current_set_num = 0;
                setsRemainingTextView.setText(String.format(Locale.getDefault(),"%d/%d", current_set_num, max_num_of_sets));
            }
        });

        // When pause button is pressed
        pauseButton.setOnClickListener(view -> {
            if (timerActive) {
                chronoTimeWhenPaused = timerChronometer.getBase() - SystemClock.elapsedRealtime();
                timerChronometer.stop();
                timerActive = false;
            }
            else {
                timerChronometer.setBase(SystemClock.elapsedRealtime() + chronoTimeWhenPaused);
                timerChronometer.start();
                timerActive = true;
            }
        });

        // When the log button is pressed
        logButton.setOnClickListener(view -> {
            current_set_num++;
            setsRemainingTextView.setText(String.format(Locale.getDefault(),"%d/%d", current_set_num, max_num_of_sets));
        });


        // Populate the exercise cards
        myOnClickListener = new MyOnClickListener(this); // not sure if we need this, idk if we allow clicks on the exercises

        recyclerView = (RecyclerView) findViewById(R.id.exercisePerformRecyclerView);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new RecyclerAdapterPerformWorkout(exercise_name_list, rep_numbers_list);
        recyclerView.setAdapter(adapter);
    }

    public class MyOnClickListener implements View.OnClickListener {
        private final Context context;

        public MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            //do click things
        }
    }
}