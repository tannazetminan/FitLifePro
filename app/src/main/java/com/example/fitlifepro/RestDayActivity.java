package com.example.fitlifepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class RestDayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_day);

        ImageView btnBack = findViewById(R.id.imgViewArrowBack);
        Button btnDone = findViewById(R.id.btnDone);

        btnBack.setOnClickListener((View view) -> {
            Intent intent = new Intent(RestDayActivity.this, ProgressTrackerActivity.class);
            startActivity(intent);
        });

        btnDone.setOnClickListener((View view) -> {
            Intent intent = new Intent(RestDayActivity.this, ProgressTrackerActivity.class);
            startActivity(intent);
        });
    }
}