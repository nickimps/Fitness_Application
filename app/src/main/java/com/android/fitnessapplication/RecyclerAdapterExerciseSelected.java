package com.android.fitnessapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapterExerciseSelected extends RecyclerView.Adapter<RecyclerAdapterExerciseSelected.MyRecyclerHolderExerciseSelected> {
    private ArrayList<ExerciseTypeObject> exercise_list;

    public class MyRecyclerHolderExerciseSelected extends RecyclerView.ViewHolder {
        private TextView exercise_name;

        public MyRecyclerHolderExerciseSelected(@NonNull View itemView) {
            super(itemView);
            exercise_name = (TextView) itemView.findViewById(R.id.workout_name_card);
        }
    }

    public RecyclerAdapterExerciseSelected(ArrayList<ExerciseTypeObject> data) {
        this.exercise_list = data;
    }

    @NonNull
    @Override
    public MyRecyclerHolderExerciseSelected onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chosen_exercise_card, parent, false);

        view.setOnClickListener((CreateWorkoutActivity.myOnClickListenerSelected));

        MyRecyclerHolderExerciseSelected myRecyclerHolderExerciseSelected = new MyRecyclerHolderExerciseSelected(view);
        return myRecyclerHolderExerciseSelected;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerHolderExerciseSelected holder, int position) {
        TextView exercise_name = holder.exercise_name;
        exercise_name.setText(exercise_list.get((position)).getExercise_name());
    }

    @Override
    public int getItemCount() {
        return exercise_list.size();
    }
}
