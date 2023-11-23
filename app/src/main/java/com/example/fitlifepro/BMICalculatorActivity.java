package com.example.fitlifepro;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.sql.SQLDataException;

public class BMICalculatorActivity extends AppCompatActivity {

    private TextView txtViewBMIResult, txtViewBMIValue;
    private Button btnCalculateBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);

        ImageView btnBack = findViewById(R.id.imgViewArrowBack);
        txtViewBMIResult = findViewById(R.id.txtViewBMIResult);
        txtViewBMIValue = findViewById(R.id.txtViewBMIValue);

        // Retrieve user information from the database and calculate BMI
       try {
            calculateAndDisplayBMI();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }

        btnBack.setOnClickListener((View view) -> {
            Intent intent = new Intent(BMICalculatorActivity.this, HomePageActivity.class);
            startActivity(intent);
        });

    }


    private void calculateAndDisplayBMI() throws SQLDataException {
        // Fetch user information from the database
        UserDatabaseManager dbManager = new UserDatabaseManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        if (cursor != null && cursor.moveToFirst()) {
            // Extract weight and height from the database
            @SuppressLint("Range") int weight = cursor.getInt(cursor.getColumnIndex(UserDatabaseHelper.WEIGHT));
            @SuppressLint("Range") int height = cursor.getInt(cursor.getColumnIndex(UserDatabaseHelper.HEIGHT));

            // Calculate BMI
            double bmi = calculateBMI(weight, height);

            // Display BMI result
            txtViewBMIValue.setText(String.format("Your Body Mass Index is %.2f.", bmi));

            // Determine BMI category
            String bmiCategory = getBMICategory(bmi);

            // Display BMI category
            txtViewBMIResult.setText(String.format("This is considered %s",bmiCategory));
        }
    }

    private String getBMICategory(double bmi) {
        if (bmi < 18.5) {
            return "Underweight";
        } else if (bmi >= 18.5 && bmi < 24.9) {
            return "Normal Weight";
        } else if (bmi >= 25 && bmi < 29.9) {
            return "Overweight";
        } else {
            return "Obese";
        }
    }

    private double calculateBMI(int weight, int height) {
        // Convert height from cm to meters
        double heightInMeters = height / 100.0;

        // Calculate BMI using the formula: BMI = weight (kg) / (height (m))^2
        return weight / (heightInMeters * heightInMeters);
    }

    public void calculateBMI(android.view.View view) throws SQLDataException {
        calculateAndDisplayBMI();
    }
}
