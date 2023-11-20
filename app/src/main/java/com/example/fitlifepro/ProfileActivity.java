package com.example.fitlifepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;



public class ProfileActivity extends AppCompatActivity {


    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ImageView btnBack = findViewById(R.id.imgViewArrowBack);
        TextView btnDeleteAccount = findViewById(R.id.txtViewDeleteAccount);
        ImageView profileAvatar = findViewById(R.id.imgViewProfilePageAvatar);
        TextView profileName = findViewById(R.id.txtViewDisplayName);
        TextView profileEmail = findViewById(R.id.txtViewDisplayEmail);
        TextView profileFitnessLvl = findViewById(R.id.txtViewDisplayFitnessLvl);
        TextView profileBirthday = findViewById(R.id.txtViewDisplayBirthday);
        TextView profileWeight = findViewById(R.id.txtViewDisplayWeight);
        TextView profileHeight = findViewById(R.id.txtViewDisplayHeight);
        TextView profileGender = findViewById(R.id.txtViewDisplayGender);

        //initialize the Database Manager
        dbManager = new DatabaseManager(this);
        try {
            dbManager.open();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //fetch all user data from the database
        try (Cursor cursor = dbManager.fetch()) {
            cursor.moveToFirst();

            profileName.setText(cursor.getString(1));
            profileEmail.setText(cursor.getString(0));
            profileBirthday.setText(cursor.getString(2));
            profileHeight.setText(cursor.getString(3));
            profileWeight.setText(cursor.getString(4));
            profileGender.setText(cursor.getString(5));
            profileFitnessLvl.setText(cursor.getString(6));

            if (cursor.getString(5).equals("Male")) {
                profileAvatar.setImageResource(R.drawable.male_avatar);
            } else {
                profileAvatar.setImageResource(R.drawable.female_avatar);
            }
        }

        btnBack.setOnClickListener((View view) -> {
            Intent intent = new Intent(ProfileActivity.this, HomePageActivity.class);
            startActivity(intent);
        });

        btnDeleteAccount.setOnClickListener((View view) -> {
            dbManager.delete();
            Toast.makeText(ProfileActivity.this, "Your account was permanently deleted.", Toast.LENGTH_SHORT).show();

            //go back to sign up page
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}