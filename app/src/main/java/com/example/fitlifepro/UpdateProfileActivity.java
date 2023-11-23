package com.example.fitlifepro;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Calendar;
import java.util.regex.Pattern;

public class UpdateProfileActivity extends AppCompatActivity {

    UserDatabaseManager dbManager;

    private DatePickerDialog datePickerDialog;
    private Button dateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_update_profile);
        initDatePicker();

        Button datePickerButton = findViewById(R.id.datePickerUpdateButton);
        ImageView btnBack = findViewById(R.id.imgViewArrowBack);
        EditText updateName = findViewById(R.id.editTxtUpdateName);
        EditText updateEmail = findViewById(R.id.editTxtUpdateEmail);
        EditText updateWeight = findViewById(R.id.editTxtUpdateWeight);
        EditText updateHeight = findViewById(R.id.editTxtUpdateHeight);
        RadioButton radBtnMale = findViewById(R.id.radBtnUpdateMale);
        RadioButton radBtnFemale = findViewById(R.id.radBtnUpdateFemale);
        Button btnSaveChanges = findViewById(R.id.btnSaveChanges);
        RadioGroup radGrpUpdateGender = findViewById(R.id.radGrpUpdateGender);

        String fitnessLvl;

        //initialize the Database Manager
        dbManager = new UserDatabaseManager(this);
        try {
            dbManager.open();

        } catch (Exception e) {
            e.printStackTrace();
        }

        //fetch all user data from the database and display
        try (Cursor cursor = dbManager.fetch()) {
            cursor.moveToFirst();

            updateName.setText(cursor.getString(1));
            updateEmail.setText(cursor.getString(0));
            updateHeight.setText(cursor.getString(3));
            updateWeight.setText(cursor.getString(4));

            fitnessLvl = cursor.getString(6);

            dateButton = findViewById(R.id.datePickerUpdateButton);
            dateButton.setText(cursor.getString(2));

            if (cursor.getString(5).equals("Male")) {
                radBtnMale.setChecked(true);
                radBtnFemale.setChecked(false);
            } else {
                radBtnFemale.setChecked(true);
                radBtnMale.setChecked(false);
            }
        }

        btnBack.setOnClickListener((View view) -> {
            Intent intent = new Intent(UpdateProfileActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        btnSaveChanges.setOnClickListener((View view) -> {
            try {


                String name = updateName.getText().toString();
                String email = updateEmail.getText().toString();
                String birthday = datePickerButton.getText().toString();
                String heightStr = updateHeight.getText().toString();
                String weightStr = updateWeight.getText().toString();
                String updatedGender;

                if (radGrpUpdateGender.getCheckedRadioButtonId() == R.id.radBtnUpdateFemale) {
                    updatedGender = "Female";
                } else {
                    updatedGender = "Male";
                }

                boolean isEmailValid = isValidEmail(email);
                boolean isHeightValid = isValidHeight(heightStr);
                boolean isWeightValid = isValidWeight(weightStr);

                if (isEmailValid && isHeightValid && isWeightValid) {
                    int height = Integer.parseInt(heightStr);
                    int weight = Integer.parseInt(weightStr);

                    // Check if height and weight are within reasonable ranges
                    if (height >= 50 && height <= 250 && weight >= 20 && weight <= 500) {
                        // All validations passed, proceed to the next activity
                        Toast.makeText(UpdateProfileActivity.this, "Your profile has been updated successfully.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(UpdateProfileActivity.this, ProfileActivity.class);
                        startActivity(intent);
                    } else {
                        // Height or weight is out of range, show specific error messages
                        if (height < 49 || height > 221) {
                            Toast.makeText(UpdateProfileActivity.this, "Invalid height. Height should be between 50 and 220", Toast.LENGTH_SHORT).show();
                        }
                        if (weight < 19 || weight > 131) {
                            Toast.makeText(UpdateProfileActivity.this, "Invalid weight. Weight should be between 20 and 130", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    // Show specific error messages for each validation
                    if (!isEmailValid) {
                        Toast.makeText(UpdateProfileActivity.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                    }
                    if (!isHeightValid) {
                        Toast.makeText(UpdateProfileActivity.this, "Invalid height. Height should be a number", Toast.LENGTH_SHORT).show();
                    }
                    if (!isWeightValid) {
                        Toast.makeText(UpdateProfileActivity.this, "Invalid weight. Weight should be a number", Toast.LENGTH_SHORT).show();
                    }
                }

                //insert in the database
                dbManager.update(email, name, birthday, Integer.parseInt(weightStr), Integer.parseInt(heightStr), updatedGender, fitnessLvl);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    private void initDatePicker()
    {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day)
            {
                month = month + 1;
                String date = makeDateString(day, month, year);
                dateButton.setText(date);
            }
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;

        datePickerDialog = new DatePickerDialog(this, style,dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    private String getMonthFormat(int month) {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default
        return "JAN";
    }

    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    private boolean isValidHeight(String heightStr) {
        try {
            int height = Integer.parseInt(heightStr);
            return height >= 50 && height <= 220;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isValidWeight(String weightStr) {
        try {
            int weight = Integer.parseInt(weightStr);
            return weight >= 20 && weight <= 130;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}