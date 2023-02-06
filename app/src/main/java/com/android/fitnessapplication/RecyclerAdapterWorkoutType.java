package com.android.fitnessapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapterWorkoutType extends RecyclerView.Adapter<RecyclerAdapterWorkoutType.MyRecyclerHolderWorkoutType> {
    private ArrayList<WorkoutTypeObject> workout_list;

    public class MyRecyclerHolderWorkoutType extends RecyclerView.ViewHolder {
        private TextView workout_name;
        public MyRecyclerHolderWorkoutType(@NonNull View itemView) {
            super(itemView);
            workout_name = (TextView) itemView.findViewById(R.id.workout_name_card);
        }
    }

    public RecyclerAdapterWorkoutType(ArrayList<WorkoutTypeObject> data) {
        this.workout_list = data;
    }

    @NonNull
    @Override
    public MyRecyclerHolderWorkoutType onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.workout_type_card, parent, false);

        view.setOnClickListener((ChooseWorkoutsActivity.myOnClickListener));

        MyRecyclerHolderWorkoutType myRecyclerHolderWorkoutType = new MyRecyclerHolderWorkoutType(view);
        return myRecyclerHolderWorkoutType;
    }

    @Override
    public void onBindViewHolder(final MyRecyclerHolderWorkoutType holder, final int listPosition) {
        TextView workout_name = holder.workout_name;
        workout_name.setText(workout_list.get((listPosition)).getWorkout_name());
    }

    @Override
    public int getItemCount() {
        return workout_list.size();
    }
}
