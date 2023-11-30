package com.example.fitlifepro;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private Switch switchNotifications;
    private Switch switchLocation;
    private SeekBar seekBarAudio;
    private TextView textViewAudioLevel;

    // SharedPreferences to store user preferences
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ImageView btnBack = findViewById(R.id.imgViewBackToExerciseList);
        btnBack.setOnClickListener((View view) -> {
            Intent intent = new Intent(SettingsActivity.this, HomePageActivity.class);
            startActivity(intent);
        });

        switchNotifications = findViewById(R.id.switchNotifications);
        switchLocation = findViewById(R.id.switchLocation);
        seekBarAudio = findViewById(R.id.seekBarAudio);
        textViewAudioLevel = findViewById(R.id.appCompatTextView4);

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences("com.example.fitlifepro.PREFERENCES", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // Load user preferences
        boolean notificationsEnabled = sharedPreferences.getBoolean("notificationsEnabled", false);
        boolean locationEnabled = sharedPreferences.getBoolean("locationEnabled", false);
        int audioLevel = sharedPreferences.getInt("audioLevel", 50);

        // Apply user preferences to UI components
        switchNotifications.setChecked(notificationsEnabled);
        switchLocation.setChecked(locationEnabled);
        seekBarAudio.setProgress(audioLevel);
        textViewAudioLevel.setText("Adjust Audio Level: " + audioLevel);

        // Set listeners for UI components
        switchNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("notificationsEnabled", isChecked);
                editor.apply();
            }
        });

        switchLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                editor.putBoolean("locationEnabled", isChecked);
                editor.apply();

                if (isChecked) {
                    if (!isLocationEnabled()) {
                        Toast.makeText(SettingsActivity.this, "Please enable location services", Toast.LENGTH_SHORT).show();
                        switchLocation.setChecked(false);
                    }
                }
            }
        });

        seekBarAudio.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                textViewAudioLevel.setText("Adjust Audio Level: " + progress);
                editor.putInt("audioLevel", progress);
                editor.apply();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    // Check if location services are enabled on the device
    private boolean isLocationEnabled() {
        int locationMode;
        try {
            locationMode = Settings.Secure.getInt(getContentResolver(), Settings.Secure.LOCATION_MODE);
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return locationMode != Settings.Secure.LOCATION_MODE_OFF;
    }
}
