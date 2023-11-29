package com.example.fitlifepro;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class WorkoutPlanActivity extends AppCompatActivity {
    DatabaseManager dbManager;
    AlertDialog dialog;
    AlertDialog.Builder builder;

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

                int total_days = lengthOfPlan * 7;

                //if user has already created workout plan
                if (dbManager.hasWorkoutPlan() == true) {
                    builder = new AlertDialog.Builder(WorkoutPlanActivity.this);
                    builder.setTitle("Your current workout plan will be deleted. Do you still want to proceed?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dbManager.deletePlan();
                            dbManager.resetIDWorkoutPlan();
                            dbManager.deleteDaysTracker();
                            dbManager.resetIDDaysTracker();
                            try {
                                savePlan(currentDate, total_days);
                            } catch (ParseException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(WorkoutPlanActivity.this, HomePageActivity.class);
                            startActivity(intent);
                        }
                    });
                    dialog = builder.create();
                    dialog.show();
                } else {
                    savePlan(currentDate, total_days);
                }

            }catch (Exception ex) {
                ex.printStackTrace();
            }
        });

    }


    public String getActivity(String day_of_week) {
        String activity = null;

        String mon;
        String tue;
        String wed;
        String thu;
        String fri;
        String sat;
        String sun;

        //get days of workout from Workout_Plan table
        try (Cursor cursor = dbManager.fetchPlan()) {
            cursor.moveToFirst();

            mon = cursor.getString(2);
            tue = cursor.getString(3);
            wed = cursor.getString(4);
            thu = cursor.getString(5);
            fri = cursor.getString(6);
            sat = cursor.getString(7);
            sun = cursor.getString(8);
        }

        if (day_of_week.equals("Monday")) {
            activity = mon;
        } else if (day_of_week.equals("Tuesday")) {
            activity = tue;
        } else if (day_of_week.equals("Wednesday")) {
            activity = wed;
        } else if (day_of_week.equals("Thursday")) {
            activity = thu;
        } else if (day_of_week.equals("Friday")) {
            activity = fri;
        } else if (day_of_week.equals("Saturday")) {
            activity = sat;
        } else {
            activity = sun;
        }

        return activity;
    }

    public void savePlan(String currentDate, int total_days) throws ParseException {
        //insert in the database
        dbManager.insertPlan(currentDate, lengthOfPlan, mondaySelected, tuesdaySelected, wednesdaySelected, thursdaySelected, fridaySelected, saturdaySelected, sundaySelected, chestSelected, abdominalSelected, armSelected, legSelected, total_days, 0);
        Toast.makeText(WorkoutPlanActivity.this, "Workout Plan saved successfully.", Toast.LENGTH_SHORT).show();

        //add plan to days_tracker table
        //for the current design, max length of plan that can be selected is 4 weeks or 28 days
        SimpleDateFormat df = new SimpleDateFormat("MMM-dd-yyyy", Locale.getDefault());
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = df.parse(currentDate);
        String dayOfTheWeek = sdf.format(d);

        String activity = null;
        String previousDay = null;

        int i;
        for (i = 1; i <= 28; i++) {
            if (i == 1) {
                activity = getActivity(dayOfTheWeek);
                dbManager.insertDay(dayOfTheWeek, activity);
                previousDay = dayOfTheWeek;
            } else {
                if (previousDay.equals("Monday")) {
                    activity = getActivity("Tuesday");
                    dbManager.insertDay("Tuesday", activity);
                    previousDay = "Tuesday";
                } else if (previousDay.equals("Tuesday")) {
                    activity = getActivity("Wednesday");
                    dbManager.insertDay("Wednesday", activity);
                    previousDay = "Wednesday";
                } else if (previousDay.equals("Wednesday")) {
                    activity = getActivity("Thursday");
                    dbManager.insertDay("Thursday", activity);
                    previousDay = "Thursday";
                } else if (previousDay.equals("Thursday")) {
                    activity = getActivity("Friday");
                    dbManager.insertDay("Friday", activity);
                    previousDay = "Friday";
                } else if (previousDay.equals("Friday")) {
                    activity = getActivity("Saturday");
                    dbManager.insertDay("Saturday", activity);
                    previousDay = "Saturday";
                } else if (previousDay.equals("Saturday")) {
                    activity = getActivity("Sunday");
                    dbManager.insertDay("Sunday", activity);
                    previousDay = "Sunday";
                } else {
                    activity = getActivity("Monday");
                    dbManager.insertDay("Monday", activity);
                    previousDay = "Monday";
                }
            }
        };
        Intent intent = new Intent(WorkoutPlanActivity.this, HomePageActivity.class);
        startActivity(intent);
    }
}