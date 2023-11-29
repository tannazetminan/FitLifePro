package com.example.fitlifepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ExerciseDayActivity extends AppCompatActivity {

    DatabaseManager dbManager;
    List<String> ExerciseName;
    List<Integer> ExercisePics;
    List<String> FitnessLevel;
    List<ExerciseDay> ExerciseList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_day);

        Bundle bundle = getIntent().getExtras();
        String dayX = bundle.getString("DAY_X", "NOTHING");

        String fitnessLvl = null;
        boolean chestActivity = false;
        boolean abdominalActivity = false;
        boolean armActivity = false;
        boolean legActivity = false;
        int totalActivities = 0;

        ImageView btnBack = findViewById(R.id.imgViewBackToExerciseList);
        TextView txtDayX = findViewById(R.id.txtViewDayX);
        Button btnDone = findViewById(R.id.btnDoneExercise);

        txtDayX.setText(dayX);
        txtDayX.setTextSize(18);
        txtDayX.setTextColor(Color.LTGRAY);

        //initialize the Database Manager
        dbManager = new DatabaseManager(this);
        try {
            dbManager.open();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //get fitness lvl from the database
        try (Cursor cursor = dbManager.fetchUser()) {
            cursor.moveToFirst();
            fitnessLvl = cursor.getString(6);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int doneDays = 0;

        //get selected activity types from the database
        try (Cursor cursor = dbManager.fetchPlan()) {
            cursor.moveToFirst();
            doneDays = Integer.parseInt(cursor.getString(14));
            if (cursor.getString(9).equals("1")) {
                chestActivity = true;
                totalActivities = totalActivities + 2;
            }
            if (cursor.getString(10).equals("1")) {
                abdominalActivity = true;
                totalActivities = totalActivities + 2;
            }
            if (cursor.getString(11).equals("1")) {
                armActivity = true;
                totalActivities = totalActivities + 2;
            }
            if (cursor.getString(12).equals("1")) {
                legActivity = true;
                totalActivities = totalActivities + 2;
            }
        }

        String exerciseArray[] = new String[totalActivities];
        Integer exercisePicsArray[] = new Integer[totalActivities];
        String fitnessLvlArray[] = new String[totalActivities];
        int i = -1;
        if (chestActivity == true) {
            i++;
            exerciseArray[i] = "Push Ups";
            exercisePicsArray[i] = (R.drawable.exercise_pushups);
            fitnessLvlArray[i] = fitnessLvl;
            i++;
            exerciseArray[i] = "Incline Push Ups";
            exercisePicsArray[i] = (R.drawable.exercise_inclinepushups);
            fitnessLvlArray[i] = fitnessLvl;
        }
        if (abdominalActivity == true) {
            i++;
            exerciseArray[i] = "Plank";
            exercisePicsArray[i] = (R.drawable.exercise_plank);
            fitnessLvlArray[i] = fitnessLvl;
            i++;
            exerciseArray[i] = "Alternating Curls";
            exercisePicsArray[i] = (R.drawable.exercise_alternatingcurls);
            fitnessLvlArray[i] = fitnessLvl;
        }
        if (armActivity == true) {
            i++;
            exerciseArray[i] = "Arm Raises";
            exercisePicsArray[i] = (R.drawable.exercise_armraises);
            fitnessLvlArray[i] = fitnessLvl;
            i++;
            exerciseArray[i] = "Biceps Curl";
            exercisePicsArray[i] = (R.drawable.exercise_bicepscurls);
            fitnessLvlArray[i] = fitnessLvl;
        }
        if (legActivity == true) {
            i++;
            exerciseArray[i] = "Squat";
            exercisePicsArray[i] = (R.drawable.exercise_squats);
            fitnessLvlArray[i] = fitnessLvl;
            i++;
            exerciseArray[i] = "Backward Lunge";
            exercisePicsArray[i] = (R.drawable.exercise_backwardlunge);
            fitnessLvlArray[i] = fitnessLvl;
        }

        ExerciseName = new ArrayList<>(Arrays.asList(exerciseArray));
        ExercisePics = new ArrayList<>(Arrays.asList(exercisePicsArray));
        FitnessLevel = new ArrayList<>(Arrays.asList(fitnessLvlArray));
        ExerciseList = new ArrayList<>(); //empty list

        LoadDailyExerciseData();

        //Instantiate ListView
        ListView listViewExercises = findViewById(R.id.listViewExercises);

        //Create adapter object
        ExerciseDayAdapter myAdapter = new ExerciseDayAdapter(ExerciseList);

        //Set adapter object onto ListView
        listViewExercises.setAdapter(myAdapter);

        String finalFitnessLvl = fitnessLvl;
        listViewExercises.setOnItemClickListener((AdapterView<?> adapterView, View view, int x, long l) -> {
            myAdapter.setSelectedInd(x);
            int index = myAdapter.getSelectedInd();

            Bundle bundleExercise = new Bundle();
            bundleExercise.putString("FITNESS_LEVEL", finalFitnessLvl);
            bundleExercise.putString("EXERCISE_NAME", exerciseArray[index]);

            Intent intent = new Intent(ExerciseDayActivity.this, TimerActivity.class);
            intent.putExtras(bundleExercise);

            startActivity(intent);
        });



        btnBack.setOnClickListener((View view) -> {
            Intent intent = new Intent(ExerciseDayActivity.this, ProgressTrackerActivity.class);
            startActivity(intent);
        });

        AtomicInteger setDays = new AtomicInteger(doneDays);

        btnDone.setOnClickListener((View view) -> {
            Intent intent = new Intent(ExerciseDayActivity.this, ProgressTrackerActivity.class);
            startActivity(intent);
            setDays.getAndIncrement();
            dbManager.updatePlan(String.valueOf(setDays));
        });

    }

    private void LoadDailyExerciseData() {
        for (int i = 0; i < ExerciseName.size();i++){
            ExerciseDay eachExercise =
                    new ExerciseDay(ExerciseName.get(i),ExercisePics.get(i), FitnessLevel.get(i));
            ExerciseList.add(eachExercise); //SongList is not null
        }
    }
}