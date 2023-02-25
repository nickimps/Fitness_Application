package com.android.fitnessapplication;

public class ListOfWorkouts {

    static String[] workouts = {
            "Unicorn Flying Jacks",
            "Gigantic Jumps",
            "Wing Flappers",
            "Unicorn Flying Jacks",
            "Gigantic Jumps",
            "Unicorn Flying Jacks",
            "Wing Flappers",
            "Gigantic Jumps",
            "Unicorn Flying Jacks",
            "Gigantic Jumps",
            "Wing Flappers",
            "Wing Flappers",
            "Unicorn Flying Jacks",
            "Gigantic Jumps"
    };

    static String[][] listOfWorkouts = {
            { "chest_workout_1",    //workout name
                "chest",    // filter
                "4",        //# sets
                "Barbell Bench Press", "5-7", //this point down are the exercise name and reps
                "Dumbbell Bench Press", "4",
                "Push-Up", "15-20" },
            { "chest_workout_2",
                "chest",
                "4",
                "Chest Fly", "10",
                "Dumbbell Bench Press", "4",
                "Push-Up", "15" }
    };

    static String[] exercises = {
            "Push Down",
            "Push Up",
            "Pull Far Down",
            "Push Far Up",
            "Crab Walk",
            "Plank"
    };
}
