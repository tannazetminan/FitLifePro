package com.example.fitlifepro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class NutritionGuidanceActivity extends AppCompatActivity {

    List<String> Names = new ArrayList<>();
    List<String> URLs = new ArrayList<>();
    List<Video> VideoList = new ArrayList<>();

    Button btnNutGuideBack;
    Button btnNutGuideRem;

    ListView listViewNutGuide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_guidance);

        ImageView btnBack = findViewById(R.id.imgViewBackToExerciseList);
        btnBack.setOnClickListener((View view) -> {
            Intent intent = new Intent(NutritionGuidanceActivity.this, HomePageActivity.class);
            startActivity(intent);
        });

        loadVideos();

        // Creating an adapter
        NutritionVideoAdapter nutVidAdapter = new NutritionVideoAdapter(VideoList, 1);
        // Finding a ListView inside activity_nutrition_guidance.xml
        listViewNutGuide = findViewById(R.id.listViewNutGuide);
        // Setting the adapter to ListView
        listViewNutGuide.setAdapter(nutVidAdapter);

        // Clicking (selecting) event on ListView
        listViewNutGuide.setOnItemClickListener(
                (AdapterView<?> adapterView, View view, int i, long l) -> {
                    if(nutVidAdapter.getSelectedInd() == i){
                        nutVidAdapter.setSelectedInd(-1);
                    }
                    else{
                        nutVidAdapter.setSelectedInd(i);
                    }
        });
    }

    private void loadVideos(){

        // Manually adding Names & URLs
        Names.add("6 Foods that Lower Cortisol");
        Names.add("The Top Foods That Have Been Robbing You of Nutrients (Vitamins & Minerals)");
        Names.add("The Mind-Blowing Benefits of a Lemon");
        Names.add("RED MEAT: The Single BEST Food for Healing and Repair");

        URLs.add("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/1mTzY9qUkIs?si=zRaA-lkKDoUyk9tO\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>");
        URLs.add("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/qDW86yRo-A4?si=VVMZqXkEhFZH-1DO\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>");
        URLs.add("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/i5oLfvDduMQ?si=rl6dNr1OOOsxgfd1\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>");
        URLs.add("<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/EFtMw2ow95M?si=b42bUoOz7uAiSZ5P\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>");

        // Loading videos
        for (int i = 0; i < Names.size(); i++){
            Video eachVideo = new Video(Names.get(i), URLs.get(i));
            VideoList.add(eachVideo);
        }
    }
}