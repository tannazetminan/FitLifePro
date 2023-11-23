package com.example.fitlifepro;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class ExerciseDemosActivity extends AppCompatActivity {

    List<String> Names = new ArrayList<>();
    List<String> URLs = new ArrayList<>();
    List<Video> VideoList = new ArrayList<>();

    Button btnExDemosBack;
    Button btnExDemosRem;

    ListView listViewExDemos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_demos);

        btnExDemosBack = findViewById(R.id.btnExDemosBack);
        btnExDemosRem = findViewById(R.id.btnExDemosRem);

        btnExDemosBack.setOnClickListener((View view) -> {
            startActivity(new Intent(ExerciseDemosActivity.this, HomePageActivity.class));
        });

        btnExDemosRem.setOnClickListener((View view) -> {
            startActivity(new Intent(ExerciseDemosActivity.this, ReminderViewActivity.class));
        });

        loadVideos();

        // Creating an adapter
        ExerciseVideoAdapter exVidAdapter = new ExerciseVideoAdapter(VideoList, 1);
        // Finding a ListView inside activity_exercise_demos.xml
        listViewExDemos = findViewById(R.id.listViewExDemos);
        // Setting the adapter to ListView
        listViewExDemos.setAdapter(exVidAdapter);

        // Clicking (selecting) event on ListView
        listViewExDemos.setOnItemClickListener(
                (AdapterView<?> adapterView, View view, int i, long l) -> {
                    if(exVidAdapter.getSelectedInd() == i){
                        exVidAdapter.setSelectedInd(-1);
                    }
                    else{
                        exVidAdapter.setSelectedInd(i);
                    }
                });
    }

    private void loadVideos(){

        // Manually adding Names and URLs
        Names.add("The Fastest Way to Pull Yourself Out of Any Stressful Situation");
        Names.add("The Huge Benefits of Just 11 Minutes of Exercise a Day");
        Names.add("12 Things That STOP a Good Night's Sleep");
        Names.add("The Root Cause of Depression is NOT a Chemical Imbalance with Serotonin");

        URLs.add("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/cbEbavXLAsM?si=8F-IXP0QcujKVA1k\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>");
        URLs.add("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/4bskn2RrhPM?si=faDfINwavYicaq6l\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>");
        URLs.add("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/VInAKJTTtYE?si=rCu0XdUSBUwofCKM\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>");
        URLs.add("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/_unXtsQI8dg?si=h3p2y1pfXWaIw7BG\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>");

        // Loading videos
        for (int i = 0; i < Names.size(); i++){
            Video eachVideo = new Video(Names.get(i), URLs.get(i));
            VideoList.add(eachVideo);
        }
    }
}