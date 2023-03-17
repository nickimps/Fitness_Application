package com.android.fitnessapplication;

public class ListOfWorkouts {
    static String[][] listOfWorkouts = {
            { "Explosive Chest",    //workout name
                "chest",    // filter
                "3",        // # sets
                "16",       // number of indexes to loop, this number exercises times 2
                "Barbell Bench Press", "6-8", //this point down are the exercise name and reps
                "Dumbbell Bench Press", "10-12",
                "Chest Flies", "10-12",
                "Cable Flies", "10-12",
                "Chest Press", "10-12",
                "Incline Bench Press", "6-8",
                "Dips", "6-8",
                "Push-Ups", "15-20" },
            { "Pumped Up Shoulders",
                "shoulders",
                "3",
                "16",
                "Military Press", "4-6",
                "Push Press", "AMRAP",              //1
                "Dumbbell Shoulder Press", "8-10",
                "Lateral Raises", "12-15",
                "Shrugs", "20",
                "Cable Extensions", "12",
                "Rear Delt Flies", "10-12",
                "Farmers Carry", "30" },        //sec
            { "Blasting Biceps",
                "biceps",
                "4",
                "12",
                "Dumbbell Curl", "12",     //Each Arm  -- REPS EACH ARM
                "Hammer Curl", "12",       //Each Arm
                "Preacher Curl", "12",
                "Barbell Strict Curl", "8",
                "Chin-Ups", "AMRAP",                //1
                "Concentration Curl", "12" }, //Each Arm
            { "Tearing-Up Triceps",
                "triceps",
                "4",
                "12",
                "Tricep Push-down", "12",
                "Overhead Tricep Extension", "15",
                "Tricep Dumbbell Kickback", "8",
                "Dips", "AMRAP",                    //1
                "Skull-crushers", "10",
                "Push-Ups", "12" },
            { "Killer Core",
                "abs & obliques",
                "3",
                "14",
                "Sit-Ups", "12",
                "Crunches", "20",
                "Planks", "60",                 //sec
                "Hanging Leg Raises", "12",
                "Superman", "10",
                "Lumberjack Swings", "12", //Each Arm
                "Russian Twist", "20" },
            { "Light-Em-Up Legs",
                "legs",
                "3",
                "16",
                "Back Squat", "8",
                "Front Squat", "8",
                "Leg Extensions", "12",
                "Calf Raises", "20",
                "Leg Press", "12",
                "Romanian Dead-lift", "10",
                "Lunges", "25",
                "Leg Curls", "12" },
            { "Bursting Back",
                "back",
                "4",
                "14",
                "Barbell Dead-lift", "6",
                "Cleans", "4",
                "Lat Pull-down", "10-12",
                "Barbell Rows", "8",
                "Cable Rows", "12",
                "Pull-Ups", "AMRAP",                //1
                "T-bar Rows", "10" },
            { "Greedy Glutes",
                "glutes",
                "3",
                "14",
                "Hip Thrusts", "8",
                "Back Squat", "6",
                "Trap-Bar Dead-lift", "6",
                "Kettle-bell Swing", "12",
                "Bulgarian Split Squat", "8",
                "Good Morning", "6",
                "Cable Kickback", "12" }
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
