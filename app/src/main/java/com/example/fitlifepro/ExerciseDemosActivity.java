package com.example.fitlifepro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class ExerciseDemosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_demos);

        WebView webViewExDemos = findViewById(R.id.webViewExDemos);

        String webViewExDemosURL = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/cbEbavXLAsM?si=vbrvsmCRREbLMrLB\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";

        webViewExDemos.loadData(webViewExDemosURL, "text/html", "utf-8");

        webViewExDemos.getSettings().setJavaScriptEnabled(true);

        webViewExDemos.setWebChromeClient(new WebChromeClient());
    }
}