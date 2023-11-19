package com.example.fitlifepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

        //initialize the Database Manager
        dbManager = new DatabaseManager(this);
        try {
            dbManager.open();

        } catch (Exception e) {
            e.printStackTrace();
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