package com.example.fitlifepro;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.icu.text.LocaleDisplayNames;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.util.Patterns;
import java.util.regex.Pattern;
import android.widget.TextView;
import android.widget.Toast;
//import com.example.fitlifepro.databinding.ActivitySignupBinding;

import org.w3c.dom.Text;

import java.util.Calendar;

public class SignUpActivity extends AppCompatActivity {
    //ActivitySignupBinding binding;
    DatabaseHelper dbHelper;
    DatabaseManager dbManager;

    private DatePickerDialog datePickerDialog;
    private Button dateButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initDatePicker();
        dateButton = findViewById(R.id.datePickerButton);
        dateButton.setText(getTodaysDate());

        //pass object from SelectFitnessLvlActivity
        Bundle bundle = getIntent().getExtras();
        String fitness_level = bundle.getString("FITNESS_LEVEL", "NOTHING");
        dbHelper = new DatabaseHelper(this);

        Button btnStart = findViewById(R.id.btnStartStep3);
        Button datePickerButton = findViewById(R.id.datePickerButton);
        EditText editTxtName = findViewById(R.id.editTxtName);
        EditText editTxtEmail = findViewById(R.id.editTxtEmail);
        EditText editTxtHeight = findViewById(R.id.editTxtHeight);
        EditText editTxtWeight = findViewById(R.id.editTxtWeight);
        RadioGroup radGrpGender = findViewById(R.id.radGrpGender);

        //initialize the Database Manager
        dbManager = new DatabaseManager(this);
        try {
            dbManager.open();

        } catch (Exception e) {
            e.printStackTrace();
        }

        btnStart.setOnClickListener((View view) -> {
            try {
                String name = editTxtName.getText().toString();
                String email = editTxtEmail.getText().toString();
                String birthday = datePickerButton.getText().toString();
                String heightStr = editTxtHeight.getText().toString();
                String weightStr = editTxtWeight.getText().toString();
                String gender;

                if (radGrpGender.getCheckedRadioButtonId() == R.id.radBtnFemale) {
                    gender = "female";
                } else {
                    gender = "male";
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
                        Intent intent = new Intent(SignUpActivity.this, HomePageActivity.class);
                        startActivity(intent);
                    } else {
                        // Height or weight is out of range, show specific error messages
                        if (height < 49 || height > 221) {
                            Toast.makeText(SignUpActivity.this, "Invalid height. Height should be between 50 and 220", Toast.LENGTH_SHORT).show();
                        }
                        if (weight < 19 || weight > 131) {
                            Toast.makeText(SignUpActivity.this, "Invalid weight. Weight should be between 20 and 130", Toast.LENGTH_SHORT).show();
                        }
                    }
                } else {
                    // Show specific error messages for each validation
                    if (!isEmailValid) {
                        Toast.makeText(SignUpActivity.this, "Invalid email address", Toast.LENGTH_SHORT).show();
                    }
                    if (!isHeightValid) {
                        Toast.makeText(SignUpActivity.this, "Invalid height. Height should be a number", Toast.LENGTH_SHORT).show();
                    }
                    if (!isWeightValid) {
                        Toast.makeText(SignUpActivity.this, "Invalid weight. Weight should be a number", Toast.LENGTH_SHORT).show();
                    }
                }

                //insert in the database
                dbManager.insert(email, name, birthday, Integer.parseInt(weightStr), Integer.parseInt(heightStr), gender, fitness_level);


                //check if it was added in the database
                fetchData();
            } catch (Exception e) {
                e.printStackTrace();
            }


        });
    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
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

    public void fetchData() {
        Cursor cursor = dbManager.fetch();

        if (cursor.moveToFirst()) {

                @SuppressLint("Range") String email = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_EMAIL));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.USER_NAME));
                @SuppressLint("Range") String birthday = cursor.getString(cursor.getColumnIndex(DatabaseHelper.BIRTHDAY));
                @SuppressLint("Range") String weight = cursor.getString(cursor.getColumnIndex(DatabaseHelper.WEIGHT));
                @SuppressLint("Range") String height = cursor.getString(cursor.getColumnIndex(DatabaseHelper.HEIGHT));
                @SuppressLint("Range") String gender = cursor.getString(cursor.getColumnIndex(DatabaseHelper.GENDER));
                @SuppressLint("Range") String fitness_level = cursor.getString(cursor.getColumnIndex(DatabaseHelper.FITNESS_LEVEL));

                Log.d("DBFITLIFEPRO", "Email: " + email + "; Name: " + name + "; Birthday: " + birthday
                        + "; Weight: " + weight + "; Height: " + weight + "; Gender: " + gender + "; Fitness Level: " + fitness_level);

        }
    }


}