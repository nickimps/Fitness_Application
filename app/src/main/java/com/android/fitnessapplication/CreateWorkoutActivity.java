package com.android.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class CreateWorkoutActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapterSelected, adapterToChoose;
    private RecyclerView.LayoutManager layoutManagerSelected, layoutManagerToChoose;
    private static RecyclerView recyclerViewExerciseSelected, recyclerViewExerciseToChoose;
    private static ArrayList<ExerciseTypeObject> exercise_list;
    public static View.OnClickListener myOnClickListenerSelected, myOnClickListenerToChoose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);

        myOnClickListenerSelected = new MyOnClickListenerSelected(this);
        myOnClickListenerToChoose = new MyOnClickListenerToChoose(this);

        recyclerViewExerciseSelected = (RecyclerView) findViewById(R.id.chosenWorkoutsRecyclerView);
        recyclerViewExerciseSelected.setHasFixedSize(true);
        recyclerViewExerciseToChoose = (RecyclerView) findViewById(R.id.exercisesRecyclerView);
        recyclerViewExerciseToChoose.setHasFixedSize(true);

        layoutManagerSelected = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewExerciseSelected.setLayoutManager(layoutManagerSelected);
        recyclerViewExerciseSelected.setItemAnimator(new DefaultItemAnimator());
        layoutManagerToChoose = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewExerciseToChoose.setLayoutManager(layoutManagerToChoose);
        recyclerViewExerciseToChoose.setItemAnimator(new DefaultItemAnimator());

        exercise_list = new ArrayList<>();
        for (int i = 0; i < ListOfWorkouts.exercises.length; i++)
            exercise_list.add(new ExerciseTypeObject(ListOfWorkouts.exercises[i])); // would change here to not auto  populate for the chosen spot, these 3 lines are placeholder

        exercise_list = new ArrayList<>();
        for (int i = 0; i < ListOfWorkouts.exercises.length; i++)
            exercise_list.add(new ExerciseTypeObject(ListOfWorkouts.exercises[i]));

        adapterSelected = new RecyclerAdapterExerciseSelected(exercise_list);
        recyclerViewExerciseSelected.setAdapter(adapterSelected);
        adapterToChoose = new RecyclerAdapterExerciseToChoose(exercise_list);
        recyclerViewExerciseToChoose.setAdapter(adapterToChoose);
    }

    public class MyOnClickListenerSelected implements View.OnClickListener {
        private final Context context;

        public MyOnClickListenerSelected(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            //do click things
//            int selectedItemPosition = recyclerViewWorkoutType.getChildAdapterPosition(view);
//            RecyclerView.ViewHolder viewHolder = recyclerViewWorkoutType.findViewHolderForAdapterPosition(selectedItemPosition);
//
//            TextView textViewWorkoutType = (TextView) viewHolder.itemView.findViewById(R.id.workout_name_card);
//            String selectedWorkout = (String) textViewWorkoutType.getText();
//
//            System.out.println("selectedWorkout");
        }
    }

    public class MyOnClickListenerToChoose implements View.OnClickListener {
        private final Context context;

        public MyOnClickListenerToChoose(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            //do click things
//            int selectedItemPosition = recyclerViewWorkoutType.getChildAdapterPosition(view);
//            RecyclerView.ViewHolder viewHolder = recyclerViewWorkoutType.findViewHolderForAdapterPosition(selectedItemPosition);
//
//            TextView textViewWorkoutType = (TextView) viewHolder.itemView.findViewById(R.id.workout_name_card);
//            String selectedWorkout = (String) textViewWorkoutType.getText();
//
//            System.out.println("selectedWorkout");
        }
    }
}