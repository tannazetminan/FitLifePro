package com.example.fitlifepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.atomic.AtomicInteger;

public class RestDayActivity extends AppCompatActivity {

    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_day);

        //initialize the Database Manager
        dbManager = new DatabaseManager(this);
        try {
            dbManager.open();

        } catch (Exception e) {
            e.printStackTrace();
        }

        int doneDays = 0;

        //get done_days from the database
        try (Cursor cursor = dbManager.fetchPlan()) {
            cursor.moveToFirst();
            doneDays = Integer.parseInt(cursor.getString(14));
            }

        //pass object from SelectFitnessLvlActivity
        Bundle bundle = getIntent().getExtras();
        String dayX = bundle.getString("DAY_X", "NOTHING");

        TextView txtDayX = findViewById(R.id.txtViewDayX);
        ImageView btnBack = findViewById(R.id.imgViewBackToExerciseList);
        Button btnDone = findViewById(R.id.btnDone);

        txtDayX.setText(dayX);

        btnBack.setOnClickListener((View view) -> {
            Intent intent = new Intent(RestDayActivity.this, ProgressTrackerActivity.class);
            startActivity(intent);
        });

        AtomicInteger setDays = new AtomicInteger(doneDays);

        btnDone.setOnClickListener((View view) -> {
            Intent intent = new Intent(RestDayActivity.this, ProgressTrackerActivity.class);
            startActivity(intent);
            setDays.getAndIncrement();
            dbManager.updatePlan(String.valueOf(setDays));
        });
    }
}