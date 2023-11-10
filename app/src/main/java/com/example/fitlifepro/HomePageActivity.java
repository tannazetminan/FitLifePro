package com.example.fitlifepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HomePageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        };

    public void ProfilePressed(View v) {
        Intent intent = new Intent(HomePageActivity.this, ProfileActivity.class);
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

}

