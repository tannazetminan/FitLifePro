package com.example.fitlifepro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class TimerActivity extends AppCompatActivity {
    private Button btnDoneWorkout;
    private TextView txtViewCountdown;
    private Button btnStartPause;
    private Button btnReset;

    private TextView txtViewExerciseActivityName;

    private ImageView imgExercise;

    private CountDownTimer countDownTimer;
    private boolean timerRunning;

    private long startTimeInMillis;
    private long timeLeftInMillis;
    private long endTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        //pass object from ExerciseDayActivity
        Bundle bundle = getIntent().getExtras();
        String fitness_level = bundle.getString("FITNESS_LEVEL", "NOTHING");
        String exercise_name = bundle.getString("EXERCISE_NAME", "NOTHING");

        txtViewCountdown = findViewById(R.id.txtViewCountdown);
        btnStartPause = findViewById(R.id.btnStartStopTimer);
        btnReset = findViewById(R.id.btnResetTimer);
        txtViewExerciseActivityName = findViewById(R.id.txtViewExerciseActivityName);
        imgExercise = findViewById(R.id.imgViewExerciseActivity);
        btnDoneWorkout = findViewById(R.id.btnDoneWorkout);

        txtViewExerciseActivityName.setText(exercise_name);

        switch (exercise_name) {
            case "Push Ups":
                imgExercise.setImageResource(R.drawable.exercise_pushups);
                break;

            case "Incline Push Ups":
                imgExercise.setImageResource(R.drawable.exercise_inclinepushups);
                break;

            case "Plank":
                imgExercise.setImageResource(R.drawable.exercise_plank);
                break;

            case "Alternating Curls":
                imgExercise.setImageResource(R.drawable.exercise_alternatingcurls);
                break;

            case "Arm Raises":
                imgExercise.setImageResource(R.drawable.exercise_armraises);
                break;

            case "Biceps Curl":
                imgExercise.setImageResource(R.drawable.exercise_bicepscurls);
                break;

            case "Squat":
                imgExercise.setImageResource(R.drawable.exercise_squats);
                break;

            case "Backward Lunge":
                imgExercise.setImageResource(R.drawable.exercise_backwardlunge);
                break;
        }

        if (fitness_level.equals("Beginner")) {
            setTime(300000);
        } else if (fitness_level.equals("Intermediate")) {
            setTime(600000);
        } else {
            setTime(900000);
        }


        btnStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetTimer();
            }
        });

        btnDoneWorkout.setOnClickListener((View view) -> {
            Intent intent = new Intent(TimerActivity.this, ExerciseDayActivity.class);
            startActivity(intent);
        });
    }

    private void setTime(long milliseconds) {
        startTimeInMillis = milliseconds;
        resetTimer();
    }

    public void startTimer() {
        endTime = System.currentTimeMillis() + timeLeftInMillis;
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                btnStartPause.setText("Start");
                btnStartPause.setVisibility(View.INVISIBLE);
                btnReset.setVisibility(View.VISIBLE);

            }
        }.start();

        timerRunning = true;
        btnStartPause.setText("Pause");
        btnReset.setVisibility(View.INVISIBLE);
    }

    public void pauseTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        btnStartPause.setText("Start");
        btnReset.setVisibility(View.VISIBLE);
    }

    public void resetTimer() {
        timeLeftInMillis = startTimeInMillis;
        updateCountDownText();
        btnReset.setVisibility(View.INVISIBLE);
        btnStartPause.setVisibility(View.VISIBLE);

    }

    public void updateCountDownText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        txtViewCountdown.setText(timeLeftFormatted);
    }
}
