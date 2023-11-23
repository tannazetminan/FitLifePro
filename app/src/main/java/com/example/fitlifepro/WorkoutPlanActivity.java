package com.example.fitlifepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class WorkoutPlanActivity extends AppCompatActivity {
    UserDatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plan);

        ImageView btnBack = findViewById(R.id.imgViewBtnBackWorkoutPlan);
        TextView fitnessLvl = findViewById(R.id.txtViewPlanFitnessLvl);
        Spinner spinnerLengthOfPlan = findViewById(R.id.spinnerLengthOfPlan);

        //initialize the Database Manager
        dbManager = new UserDatabaseManager(this);
        try {
            dbManager.open();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //fetch fitness level from the database
        try (Cursor cursor = dbManager.fetch()) {
            cursor.moveToFirst();
            fitnessLvl.setText(cursor.getString(6));
        }

        btnBack.setOnClickListener((View view) -> {
            Intent intent = new Intent(WorkoutPlanActivity.this, HomePageActivity.class);
            startActivity(intent);
        });


    }
}