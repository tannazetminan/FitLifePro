package com.example.fitlifepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    DatabaseManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //initialize the Database Manager
        dbManager = new DatabaseManager(this);
        try {
            dbManager.open();

        } catch (Exception e) {
            e.printStackTrace();
        }

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                //start next activity

                if (dbManager.hasData() == true) {
                    startActivity(
                            new Intent(SplashActivity.this, HomePageActivity.class));
                } else {
                    startActivity(
                            new Intent(SplashActivity.this, MainActivity.class));
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask,3000);

    }
}