package com.example.fitlifepro;

public class ExerciseDay {

    private String ExerciseName;
    private String Timer;
    private int ExercisePic;
    private String FitnessLvl;

    public String getExerciseName() {
        return ExerciseName;
    }

    public void setExerciseName(String exerciseName) {
        ExerciseName = exerciseName;
    }

    public String getTimer() {
        return Timer;
    }

    public void setTimer(String timer) {
        Timer = timer;
    }

    public int getExercisePic() {
        return ExercisePic;
    }

    public void setExercisePic(int exercisePic) {
        ExercisePic = exercisePic;
    }

    public String getFitnessLvl() {
        return FitnessLvl;
    }

    public void setFitnessLvl(String fitnessLvl) {
        FitnessLvl = fitnessLvl;
    }

    public ExerciseDay(String exerciseName, String timer, int exercisePic, String fitnessLvl) {
        ExerciseName = exerciseName;
        Timer = timer;
        ExercisePic = exercisePic;
        FitnessLvl = fitnessLvl;
    }
}
