package com.android.fitnessapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapterExerciseToChoose extends RecyclerView.Adapter<RecyclerAdapterExerciseToChoose.MyRecyclerHolderExerciseToChoose> {
    private ArrayList<ExerciseTypeObject> exercise_list;

    public class MyRecyclerHolderExerciseToChoose extends RecyclerView.ViewHolder {
        private TextView exercise_name;

        public MyRecyclerHolderExerciseToChoose(@NonNull View itemView) {
            super(itemView);
            exercise_name = (TextView) itemView.findViewById(R.id.workout_name_card);
        }
    }

    public RecyclerAdapterExerciseToChoose(ArrayList<ExerciseTypeObject> data) {
        this.exercise_list = data;
    }

    @NonNull
    @Override
    public MyRecyclerHolderExerciseToChoose onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exercise_card, parent, false);

        view.setOnClickListener(CreateWorkoutActivity.myOnClickListenerToChoose);

        MyRecyclerHolderExerciseToChoose myRecyclerHolderExerciseToChoose = new MyRecyclerHolderExerciseToChoose(view);
        return myRecyclerHolderExerciseToChoose;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerHolderExerciseToChoose holder, int position) {
        TextView exercise_name = holder.exercise_name;
        exercise_name.setText(exercise_list.get((position)).getExercise_name());
    }

    @Override
    public int getItemCount() {
        return exercise_list.size();
    }
}
