package com.android.fitnessapplication;

public class WorkoutTypeObject {
    private String workout_name, exercise_name, num_reps;

    public WorkoutTypeObject(String workout_name) {
        this.workout_name = workout_name;
    }

    public WorkoutTypeObject(String info, int type) {
        if (type == 0)
            this.exercise_name = info;
        else if (type == 1)
            this.num_reps = info;
    }

    public String getExercise_name() {
        return exercise_name;
    }

    public void setExercise_name(String exercise_name) {
        this.exercise_name = exercise_name;
    }

    public String getNum_reps() {
        return num_reps;
    }

    public void setNum_reps(String num_reps) {
        this.num_reps = num_reps;
    }

    public String getWorkout_name() {
        return workout_name;
    }

    public void setWorkout_name(String workout_name) {
        this.workout_name = workout_name;
    }
}
