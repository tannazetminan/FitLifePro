package com.example.fitlifepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomePageActivity extends AppCompatActivity {

    UserDatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //initialize the Database Manager
        dbManager = new UserDatabaseManager(this);
        try {
            dbManager.open();

        } catch (Exception e) {
            e.printStackTrace();
        }

        TextView userName = findViewById(R.id.txtViewUserName);
        ImageView userImage = findViewById(R.id.imgViewAvatar);

        //fetch name from the database
        Cursor cursor = dbManager.fetch();
        cursor.moveToFirst();
        userName.setText(cursor.getString(1));

        //fetch gender from the database
        String gender = cursor.getString(5);
        if (gender.equals("Male")) {
            userImage.setImageResource(R.drawable.male_avatar);
        } else {
            userImage.setImageResource(R.drawable.female_avatar);
        }
    }


    public void ProfilePressed(View v) {
        Intent intent = new Intent(HomePageActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

    public void WorkoutPlanPressed(View v) {
        Intent intent = new Intent(HomePageActivity.this, WorkoutPlanActivity.class);
        startActivity(intent);
    }

    public void BMIPressed(View v) {
        Intent intent = new Intent(HomePageActivity.this, BMICalculatorActivity.class);
        startActivity(intent);
    }

    public void NutritionPressed(View v) {
        Intent intent = new Intent(HomePageActivity.this, NutritionGuidanceActivity.class);
        startActivity(intent);
    }

    public void ExerciseDemosPressed(View v) {
        Intent intent = new Intent(HomePageActivity.this, ExerciseDemosActivity.class);
        startActivity(intent);
    }

    public void GymFinderPressed(View v) {
        Intent intent = new Intent(HomePageActivity.this, GymFinderActivity.class);
        startActivity(intent);
    }
}

