package com.example.fitlifepro;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        ImageView btnBack = findViewById(R.id.imgViewBackToExerciseList);
        TextView txtViewDaysLeft = findViewById(R.id.txtViewDaysLeft);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        TextView txtViewPercentage = findViewById(R.id.txtViewPercentage);

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
        } catch (Exception e) {
            e.printStackTrace();
        }

        int totalDays;
        int doneDays;

        //get length of plan from the database
        try (Cursor cursor = dbManager.fetchPlan()) {
            cursor.moveToFirst();

            totalDays = Integer.parseInt(cursor.getString(13));
            doneDays = Integer.parseInt(cursor.getString(14));

            String daysArray[] = new String[totalDays];
            int i;
            for (i = 0; i < totalDays; i++) {
                daysArray[i] = ("Day " + (i + 1));
            }

            ProgressTrackerDays = new ArrayList<>(Arrays.asList(daysArray));

            DaysList = new ArrayList<>(); //empty list

            LoadDaysData();

            //Instantiate ListView
            ListView listViewDays = findViewById(R.id.listViewDays);

            //Create adapter object
            ProgressTrackerAdapter myAdapter = new ProgressTrackerAdapter(DaysList);

            //Set adapter object onto ListView
            listViewDays.setAdapter(myAdapter);

            int percentProgress = (doneDays * 100) / totalDays;

            txtViewDaysLeft.setText((totalDays-doneDays) + " Days Left");
            txtViewPercentage.setText(percentProgress + "%");
            progressBar.setProgress(percentProgress);

            //set onItemClick listener for ListView
            listViewDays.setOnItemClickListener((AdapterView<?> adapterView, View view, int x, long l) -> {
                myAdapter.setSelectedInd(x);
                int index = myAdapter.getSelectedInd();

                try {
                    Cursor cursor_day_tracker = dbManager.fetchTracker(String.valueOf(index + 1));
                    if(cursor_day_tracker.getCount() >= 0) {
                        cursor_day_tracker.moveToFirst();
                        @SuppressLint("Range") String activity = cursor_day_tracker.getString(cursor_day_tracker.getColumnIndex(DatabaseHelper.DAY_ACTIVITY));
                        //Toast.makeText(ProgressTrackerActivity.this, "Activity " + activity, Toast.LENGTH_SHORT).show();
                        Bundle bundle = new Bundle();
                        bundle.putString("DAY_X", "Day " + (index + 1));
                        Intent intent;
                        if (activity.equals("0")) {
                            intent = new Intent(ProgressTrackerActivity.this, RestDayActivity.class);
                        } else {
                            //Toast.makeText(ProgressTrackerActivity.this, "Activity Day " + x, Toast.LENGTH_SHORT).show();
                            intent = new Intent(ProgressTrackerActivity.this, ExerciseDayActivity.class);
                        }
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }
                }finally {
                    cursor.close();
                }
            });

            btnBack.setOnClickListener((
                    View view) -> {
                Intent intent = new Intent(ProgressTrackerActivity.this, HomePageActivity.class);
                startActivity(intent);
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void LoadDaysData() {
        for (int i = 0; i < ProgressTrackerDays.size(); i++) {
            ProgressTrackerDays eachDay =
                    new ProgressTrackerDays(ProgressTrackerDays.get(i));
            DaysList.add(eachDay);
        }
    }


}