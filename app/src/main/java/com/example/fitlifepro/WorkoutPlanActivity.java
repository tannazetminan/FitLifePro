package com.example.fitlifepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class WorkoutPlanActivity extends AppCompatActivity {
    DatabaseManager dbManager;
    int lengthOfPlan = 0;
    boolean mondaySelected = false;
    boolean tuesdaySelected = false;
    boolean wednesdaySelected = false;
    boolean thursdaySelected = false;
    boolean fridaySelected = false;
    boolean saturdaySelected = false;
    boolean sundaySelected = false;
    boolean chestSelected = false;
    boolean abdominalSelected = false;
    boolean armSelected = false;
    boolean legSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plan);

        ImageView btnBack = findViewById(R.id.imgViewBtnBackWorkoutPlan);
        Spinner spinnerLengthOfPlan = findViewById(R.id.spinnerLengthOfPlan);
        CheckBox checkBoxMonday = findViewById(R.id.checkBoxMonday);
        CheckBox checkBoxTuesday = findViewById(R.id.checkBoxTuesday);
        CheckBox checkBoxWednesday = findViewById(R.id.checkBoxWednesday);
        CheckBox checkBoxThursday = findViewById(R.id.checkBoxThursday);
        CheckBox checkBoxFriday = findViewById(R.id.checkBoxFriday);
        CheckBox checkBoxSaturday = findViewById(R.id.checkBoxSaturday);
        CheckBox checkBoxSunday = findViewById(R.id.checkBoxSunday);
        Button btnStartWorkout = findViewById(R.id.btnStartWorkout);
        CheckBox checkBoxChestMuscles = findViewById(R.id.checkBoxChestMuscles);
        CheckBox checkBoxAbdominalMuscles = findViewById(R.id.checkBoxAbdominalMuscles);
        CheckBox checkBoxArmMuscles = findViewById(R.id.checkBoxArmMuscles);
        CheckBox checkBoxLegMuscles = findViewById(R.id.checkBoxLegMuscles);



        //initialize the User Database Manager
        dbManager = new DatabaseManager(this);
        try {
            dbManager.open();

        } catch (Exception e) {
            e.printStackTrace();
        }

        btnBack.setOnClickListener((View view) -> {
            Intent intent = new Intent(WorkoutPlanActivity.this, HomePageActivity.class);
            startActivity(intent);
        });

        btnStartWorkout.setOnClickListener((View view) -> {
            try {
                //get length of plan
                int index = spinnerLengthOfPlan.getSelectedItemPosition();

                switch (index) {
                    case 0:
                        lengthOfPlan = 2;
                        break;
                    case 1:
                        lengthOfPlan = 3;
                        break;
                    case 2:
                        lengthOfPlan = 4;
                        break;
                    case 3:
                        lengthOfPlan = 5;
                        break;
                    case 4:
                        lengthOfPlan = 6;
                        break;
                    case 5:
                        lengthOfPlan = 7;
                        break;
                    case 6:
                        lengthOfPlan = 8;
                        break;
                }


                //determine the days the user wanted to workout
                if (checkBoxMonday.isChecked()) {
                    mondaySelected = true;
                }
                if (checkBoxTuesday.isChecked()) {
                    tuesdaySelected = true;
                }
                if (checkBoxWednesday.isChecked()) {
                    wednesdaySelected = true;
                }
                if (checkBoxThursday.isChecked()) {
                    thursdaySelected = true;
                }
                if (checkBoxFriday.isChecked()) {
                    fridaySelected = true;
                }
                if (checkBoxSaturday.isChecked()) {
                    saturdaySelected = true;
                }
                if (checkBoxSunday.isChecked()) {
                    sundaySelected = true;
                }

                //determine the activity types selected
                if (checkBoxChestMuscles.isChecked()) {
                    chestSelected = true;
                }
                if (checkBoxAbdominalMuscles.isChecked()) {
                    abdominalSelected = true;
                }
                if (checkBoxArmMuscles.isChecked()) {
                    armSelected = true;
                }
                if (checkBoxLegMuscles.isChecked()) {
                    legSelected = true;
                }


                Date c = Calendar.getInstance().getTime();

                SimpleDateFormat df = new SimpleDateFormat("MMM-dd-yyyy", Locale.getDefault());
                String currentDate = df.format(c);

                //insert in the database
                dbManager.insertPlan(currentDate, lengthOfPlan, mondaySelected, tuesdaySelected, wednesdaySelected, thursdaySelected, fridaySelected, saturdaySelected, sundaySelected, chestSelected, abdominalSelected, armSelected, legSelected);
                Toast.makeText(WorkoutPlanActivity.this, "Workout Plan saved successfully.", Toast.LENGTH_SHORT).show();

            }catch (Exception ex) {
                ex.printStackTrace();
            }
            Intent intent = new Intent(WorkoutPlanActivity.this, HomePageActivity.class);
            startActivity(intent);
        });
    }


//    public void selectChestMuscles(View v) {
//        int countClick = 0;
//
//        ImageView typeChestMuscles = findViewById(R.id.imgViewActivity1);
//        Log.v("WORKOUTPLAN", "chest muscles clicked");
//        typeChestMuscles.setColorFilter(Color.parseColor("#33FFFFFF"), PorterDuff.Mode.LIGHTEN);
//    }
}