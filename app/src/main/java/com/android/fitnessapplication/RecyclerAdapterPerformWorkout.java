package com.android.fitnessapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import pl.droidsonroids.gif.GifImageView;

public class RecyclerAdapterPerformWorkout extends RecyclerView.Adapter<RecyclerAdapterPerformWorkout.MyRecyclerHolderPerformWorkout> {

    private ArrayList<WorkoutTypeObject> exercise_name_list, rep_numbers_list, rep_string_type_list, gif_ids_list;

    public class MyRecyclerHolderPerformWorkout extends RecyclerView.ViewHolder {
        private TextView rep_string_type_name, exercise_name, num_reps;
        private GifImageView gif_resource;

        public MyRecyclerHolderPerformWorkout(@NonNull View itemView) {
            super(itemView);
            exercise_name = (TextView) itemView.findViewById(R.id.exerciseNameTextView);
            num_reps = (TextView) itemView.findViewById(R.id.repNumberTextView);
            rep_string_type_name = (TextView) itemView.findViewById(R.id.repNameTextView);
            gif_resource = (GifImageView) itemView.findViewById(R.id.videoCard);
        }
    }

    public RecyclerAdapterPerformWorkout(ArrayList<WorkoutTypeObject> exercise_name_list, ArrayList<WorkoutTypeObject> rep_numbers_list, ArrayList<WorkoutTypeObject> rep_string_type_list, ArrayList<WorkoutTypeObject> gif_ids_list) {
        this.exercise_name_list = exercise_name_list;
        this.rep_numbers_list = rep_numbers_list;
        this.rep_string_type_list = rep_string_type_list;
        this.gif_ids_list = gif_ids_list;
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
        TextView REPS = holder.rep_string_type_name;
        GifImageView gif = holder.gif_resource;

        exercise_name.setText(exercise_name_list.get((position)).getExercise_name());
        num_reps.setText(rep_numbers_list.get((position)).getNum_reps());
        REPS.setText(rep_string_type_list.get((position)).getRep_string_type_name());
        gif.setImageResource(gif_ids_list.get((position)).getGif_id());
    }

    @Override
    public int getItemCount() {
        return exercise_name_list.size();
    }


}
