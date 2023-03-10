package com.android.fitnessapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapterPerformWorkout extends RecyclerView.Adapter<RecyclerAdapterPerformWorkout.MyRecyclerHolderPerformWorkout> {

    private ArrayList<WorkoutTypeObject> exercise_name_list, rep_numbers_list;

    public class MyRecyclerHolderPerformWorkout extends RecyclerView.ViewHolder {
        private TextView exercise_name;
        private TextView num_reps;

        public MyRecyclerHolderPerformWorkout(@NonNull View itemView) {
            super(itemView);
            exercise_name = (TextView) itemView.findViewById(R.id.exerciseNameTextView);
            num_reps = (TextView) itemView.findViewById(R.id.repNumberTextView);
        }
    }

    public RecyclerAdapterPerformWorkout(ArrayList<WorkoutTypeObject> exercise_name_list, ArrayList<WorkoutTypeObject> rep_numbers_list) {
        this.exercise_name_list = exercise_name_list;
        this.rep_numbers_list = rep_numbers_list;
    }

    @NonNull
    @Override
    public MyRecyclerHolderPerformWorkout onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.full_exercise_card, parent, false);

        view.setOnClickListener((PerformWorkoutActivity.myOnClickListener));

        MyRecyclerHolderPerformWorkout myRecyclerHolderPerformWorkout = new MyRecyclerHolderPerformWorkout(view);
        return myRecyclerHolderPerformWorkout;
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerHolderPerformWorkout holder, int position) {
        TextView exercise_name = holder.exercise_name;
        TextView num_reps = holder.num_reps;
        exercise_name.setText(exercise_name_list.get((position)).getExercise_name());
        num_reps.setText(rep_numbers_list.get((position)).getNum_reps());

    }

    @Override
    public int getItemCount() {
        return exercise_name_list.size();
    }


}
