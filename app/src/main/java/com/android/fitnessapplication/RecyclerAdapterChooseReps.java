package com.android.fitnessapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class RecyclerAdapterChooseReps extends RecyclerView.Adapter<RecyclerAdapterChooseReps.MyRecyclerHolderChooseReps> {

    private ArrayList<ExerciseTypeObject> exercise_list;

    public class MyRecyclerHolderChooseReps extends RecyclerView.ViewHolder {
        private TextView exercise_name;

        public MyRecyclerHolderChooseReps(@NonNull View itemView) {
            super(itemView);
            exercise_name = (TextView) itemView.findViewById(R.id.workout_name_card);
        }
    }

    public RecyclerAdapterChooseReps(ArrayList<ExerciseTypeObject> data) {
        this.exercise_list = data;
    }

    @NonNull
    @Override
    public MyRecyclerHolderChooseReps onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.choose_reps_exercise_card, parent, false);

        NumberPicker numberPicker = view.findViewById(R.id.repsNumberPicker);
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(50);
        numberPicker.setValue(5);
        numberPicker.setWrapSelectorWheel(false);

        RecyclerAdapterChooseReps.MyRecyclerHolderChooseReps myRecyclerHolderExerciseToChoose = new RecyclerAdapterChooseReps.MyRecyclerHolderChooseReps(view);
        return myRecyclerHolderExerciseToChoose;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapterChooseReps.MyRecyclerHolderChooseReps holder, int position) {
        TextView exercise_name = holder.exercise_name;
        exercise_name.setText(exercise_list.get((position)).getExercise_name());
    }

    @Override
    public int getItemCount() {
        return exercise_list.size();
    }
}
