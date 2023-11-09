package com.example.fitlifepro;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
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

        txtViewBMIResult = findViewById(R.id.txtViewBMIResult);
        txtViewBMIValue = findViewById(R.id.txtViewBMIValue);
        btnCalculateBMI = findViewById(R.id.btnCalculateBMI);

        // Retrieve user information from the database and calculate BMI
       try {
            calculateAndDisplayBMI();
        } catch (SQLDataException e) {
            throw new RuntimeException(e);
        }
    }

    private void calculateAndDisplayBMI() throws SQLDataException {
        // Fetch user information from the database
        DatabaseManager dbManager = new DatabaseManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        if (cursor != null && cursor.moveToFirst()) {
            // Extract weight and height from the database
            @SuppressLint("Range") int weight = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.WEIGHT));
            @SuppressLint("Range") int height = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.HEIGHT));

            // Calculate BMI
            double bmi = calculateBMI(weight, height);

            // Display BMI result
            txtViewBMIValue.setText(String.format("%.2f", bmi));
        }

    }

    private double calculateBMI(int weight, int height) {
        // Convert height from cm to meters
        double heightInMeters = height / 100.0;

        // Calculate BMI using the formula: BMI = weight (kg) / (height (m))^2
        return weight / (heightInMeters * heightInMeters);
    }

    // You can use this method as the onClick attribute in the XML layout
    public void calculateBMI(android.view.View view) throws SQLDataException {
        calculateAndDisplayBMI();
    }
}
