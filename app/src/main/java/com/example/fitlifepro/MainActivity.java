package com.example.fitlifepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        Button btnGetStarted = findViewById(R.id.btnGetStarted);

        btnGetStarted.setOnClickListener((View view) -> {
            Intent intent = new Intent(MainActivity.this, SelectFitnessLvlActivity.class);
            startActivity(intent);
        });

    // comment by Tom
        //resolved the gradle issue

    // added this comment and committed at home by Tom


    }
}
