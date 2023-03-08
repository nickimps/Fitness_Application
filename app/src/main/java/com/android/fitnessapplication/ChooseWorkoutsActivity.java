package com.android.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class ChooseWorkoutsActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerViewWorkoutType;
    private static ArrayList<WorkoutTypeObject> workouts_list;
    public static View.OnClickListener myOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_workouts);

        //get intent and change label to workoutType
        Intent intent = getIntent();
        String workoutType = intent.getStringExtra("workout_type");
        TextView workoutTypeHeaderTextView = findViewById(R.id.workoutTypeHeaderTextView);
        workoutTypeHeaderTextView.setText(workoutType);

        //change workoutTypeHeaderTextView for passed in intent

        myOnClickListener = new MyOnClickListener(this);

        recyclerViewWorkoutType = (RecyclerView) findViewById(R.id.workoutTypeRecyclerView);
        recyclerViewWorkoutType.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewWorkoutType.setLayoutManager(layoutManager);
        recyclerViewWorkoutType.setItemAnimator(new DefaultItemAnimator());

        workouts_list = new ArrayList<>();
        for (int i = 0; i < ListOfWorkouts.listOfWorkouts.length; i++)
            workouts_list.add(new WorkoutTypeObject(ListOfWorkouts.listOfWorkouts[i][0]));

        adapter = new RecyclerAdapterWorkoutType(workouts_list);
        recyclerViewWorkoutType.setAdapter(adapter);

    }

    public class MyOnClickListener implements View.OnClickListener {
        private final Context context;

        public MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            //do click things
            int selectedItemPosition = recyclerViewWorkoutType.getChildAdapterPosition(view);
            RecyclerView.ViewHolder viewHolder = recyclerViewWorkoutType.findViewHolderForAdapterPosition(selectedItemPosition);

            TextView textViewWorkoutType = (TextView) viewHolder.itemView.findViewById(R.id.workout_name_card);
            String selectedWorkout = (String) textViewWorkoutType.getText();

            // Go To perform workout screen
            Intent intent = new Intent(ChooseWorkoutsActivity.this, PerformWorkoutActivity.class);
            intent.putExtra("workout_name", selectedWorkout);
            startActivity((intent));


        }
    }
}