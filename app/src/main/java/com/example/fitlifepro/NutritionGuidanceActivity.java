package com.example.fitlifepro;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

public class NutritionGuidanceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrition_guidance);

        WebView webViewTop = findViewById(R.id.webViewTop);

        String webViewTopURL = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/qDW86yRo-A4?si=vJS_qsOn46FaeB4n\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";

        webViewTop.loadData(webViewTopURL, "text/html", "utf-8");

        webViewTop.getSettings().setJavaScriptEnabled(true);

        webViewTop.setWebChromeClient(new WebChromeClient());
    }
}