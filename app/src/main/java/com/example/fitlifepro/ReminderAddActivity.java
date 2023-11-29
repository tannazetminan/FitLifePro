package com.example.fitlifepro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ReminderAddActivity extends AppCompatActivity {

    Button btnRemAddBack;
    Button btnRemSave;
    Button btnRemViewAll;

    CalendarView calendarView;
    Calendar calendar;
    int selected_year, selected_month, selected_day;

    EditText editTextMemo;

    private ReminderHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_add);

        btnRemAddBack = findViewById(R.id.btnRemAddBack);
        btnRemSave  = findViewById(R.id.btnRemSave);
        btnRemViewAll = findViewById(R.id.btnRemViewAll);

        calendarView = findViewById(R.id.calendarView);
        calendar = Calendar.getInstance();
        setDate(1, 1, 2023);
        getDate();

        editTextMemo = findViewById(R.id.editTextMemo);

        helper = new ReminderHelper(ReminderAddActivity.this);

        btnRemAddBack.setOnClickListener((View view) -> {
            startActivity(new Intent(ReminderAddActivity.this, HomePageActivity.class));
        });

        btnRemViewAll.setOnClickListener((View view) -> {
            startActivity(new Intent(ReminderAddActivity.this, ReminderViewActivity.class));
        });

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int year, int month, int day) {
                selected_year = year;
                selected_month = month + 1;
                selected_day = day;

//                Toast.makeText(ReminderAddActivity.this,
//                        selected_year + "/" + selected_month + "/" + selected_day, Toast.LENGTH_SHORT).show();
            }
        });

        btnRemSave.setOnClickListener((View view) -> {
            String myDate = selected_year + "/" + selected_month + "/" + selected_day;
            String myMemo = editTextMemo.getText().toString();

            // Saving to DB
            helper.insertDate(myDate, myMemo);

            // Displaying Toast
             Toast.makeText(ReminderAddActivity.this, "Successfully added", Toast.LENGTH_SHORT).show();
             startActivity(new Intent(ReminderAddActivity.this, ReminderViewActivity.class));
        });
    }

    public void getDate(){
        long date = calendarView.getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());
        calendar.setTimeInMillis(date);
        String selected_date = simpleDateFormat.format(calendar.getTime());
//        Toast.makeText(this, selected_date, Toast.LENGTH_SHORT).show();
    }

    public void setDate(int day, int month, int year){
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month -1);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        long milli = calendar.getTimeInMillis();
        calendarView.setDate(milli);
    }
}