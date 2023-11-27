package com.example.fitlifepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProgressTrackerActivity extends AppCompatActivity {

    DatabaseManager dbManager;
    List<String> ProgressTrackerDays;
    List<ProgressTrackerDays> DaysList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_tracker);

        TextView txtViewFitnessLvlValue = findViewById(R.id.txtViewFitnessLvlValue);
        ImageView btnBack = findViewById(R.id.imgViewArrowBack);

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

            txtViewFitnessLvlValue.setText(cursor.getString(6));
            }

        //get length of plan from the database
        try (Cursor cursor = dbManager.fetchPlan()) {
            cursor.moveToFirst();

            int lengthOfPlan = Integer.parseInt(cursor.getString(1));
            int totalDays = lengthOfPlan * 7;

            String daysArray[] = new String[totalDays];
            int i;
            for (i = 0; i < totalDays; i++) {
                daysArray[i] = ("Day " + (i + 1));
            }

            ProgressTrackerDays = new ArrayList<>(Arrays.asList(daysArray));

            DaysList = new ArrayList<>(); //empty list

            LoadLvlData();

            //Instantiate ListView
            ListView listViewDays = findViewById(R.id.listViewDays);

            //Create adapter object
            ProgressTrackerAdapter myAdapter = new ProgressTrackerAdapter(DaysList);

            //Set adapter object onto ListView
            listViewDays.setAdapter(myAdapter);

            btnBack.setOnClickListener((
                    View view) -> {
                Intent intent = new Intent(ProgressTrackerActivity.this, HomePageActivity.class);
                startActivity(intent);
            });

        }
    }

    private void LoadLvlData() {
        for (int i = 0; i < ProgressTrackerDays.size(); i++) {
            ProgressTrackerDays eachDay =
                    new ProgressTrackerDays(ProgressTrackerDays.get(i));
            DaysList.add(eachDay);
        }
    }


}