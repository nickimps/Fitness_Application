package com.android.fitnessapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CreateWorkoutActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapterSelected, adapterToChoose;
    private RecyclerView.LayoutManager layoutManagerSelected, layoutManagerToChoose;
    private static RecyclerView recyclerViewExerciseSelected, recyclerViewExerciseToChoose;
    private static ArrayList<ExerciseTypeObject> exercise_list, selected_exercise_list;
    public static View.OnClickListener myOnClickListenerSelected, myOnClickListenerToChoose;
    private Button setParamsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);

        setParamsButton = findViewById(R.id.setParamsButton);
        setParamsButton.setOnClickListener(view -> {
            Intent intent = new Intent(CreateWorkoutActivity.this, SetWorkoutParametersActivity.class);

            ArrayList<String> string_exercise_list = new ArrayList<>();

            for (ExerciseTypeObject exercise : selected_exercise_list){
                string_exercise_list.add(exercise.getExercise_name());
            }

            intent.putExtra("exercise_list", string_exercise_list);
            startActivity((intent));
        });

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

        selected_exercise_list = new ArrayList<>();

        exercise_list = new ArrayList<>();
        for (int i = 0; i < ListOfWorkouts.exercises.length; i++)
            exercise_list.add(new ExerciseTypeObject(ListOfWorkouts.exercises[i][0]));

        adapterSelected = new RecyclerAdapterExerciseSelected(selected_exercise_list);
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
            int selectedItemPosition = recyclerViewExerciseSelected.getChildAdapterPosition(view);

            selected_exercise_list.remove(selectedItemPosition);
            adapterSelected.notifyItemRemoved(selectedItemPosition);

            if (selected_exercise_list.size() != 0)
                setParamsButton.setVisibility(View.VISIBLE);
            else
                setParamsButton.setVisibility(View.INVISIBLE);
        }
    }

    public class MyOnClickListenerToChoose implements View.OnClickListener {
        private final Context context;

        public MyOnClickListenerToChoose(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View view) {
            int selectedItemPosition = recyclerViewExerciseToChoose.getChildAdapterPosition(view);
            RecyclerView.ViewHolder viewHolder = recyclerViewExerciseToChoose.findViewHolderForAdapterPosition(selectedItemPosition);

            TextView textViewWorkoutType = (TextView) viewHolder.itemView.findViewById(R.id.workout_name_card);
            String selectedExercise = (String) textViewWorkoutType.getText();

            selected_exercise_list.add(new ExerciseTypeObject(selectedExercise));
            adapterSelected.notifyItemInserted(selected_exercise_list.size());

            setParamsButton.setVisibility(View.VISIBLE);
        }
    }
}