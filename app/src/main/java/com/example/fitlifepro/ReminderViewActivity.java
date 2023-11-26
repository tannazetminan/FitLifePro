package com.example.fitlifepro;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class ReminderViewActivity extends AppCompatActivity {

    TextView txtViewRemView;
    Button btnRemViewBack;
    Button btnRemViewDelete;
    ListView listViewReminders;
    List<Reminder> reminderList = new ArrayList<>();
    Reminder myReminder;
    ReminderHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_view);

        helper = new ReminderHelper(ReminderViewActivity.this);

        txtViewRemView = findViewById(R.id.txtViewRemView);

        btnRemViewBack = findViewById(R.id.btnRemViewBack);
        btnRemViewDelete = findViewById(R.id.btnRemViewDelete);

        reminderList = helper.loadReminders();

        ReminderAdapter myAdapter = new ReminderAdapter(reminderList, -1);
        listViewReminders = findViewById(R.id.listViewReminders);
        listViewReminders.setAdapter(myAdapter);

        listViewReminders.setOnItemClickListener(
                (AdapterView<?> adapterView, View view, int i, long l) -> {
                    myAdapter.setSelectedInd(i);
                    myReminder = (Reminder) myAdapter.getItem(i);
                    txtViewRemView.setText(R.string.txtViewRemViewSelected + myReminder.getRemMemo());
        });

        btnRemViewBack.setOnClickListener((View view) -> {
            startActivity(new Intent(ReminderViewActivity.this, ReminderAddActivity.class));
        });

        btnRemViewDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                helper.deleteReminder(myReminder.getRemMemo());
                startActivity(new Intent(ReminderViewActivity.this, ReminderAddActivity.class));
            }
        });
    }
}