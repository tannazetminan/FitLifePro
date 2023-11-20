// GymFinderActivity.java
package com.example.fitlifepro;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

public class GymFinderActivity extends AppCompatActivity {
    private static final String GYM_FINDER_URL = "https://www.google.com/maps/search/fitness";
    private WebView webView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_finder);

        // Find WebView and Button in the layout
        webView = findViewById(R.id.webView);
        Button btnPreviousPage = findViewById(R.id.btnPreviousPage);

        // Set up the WebView
        setupWebView();

        // Set click listener for the "Previous Page" button
        btnPreviousPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle button click to go back to the home page
                finish(); // Close the current activity to go back
            }
        });
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void setupWebView() {
        // Enable JavaScript (if needed)
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Set WebViewClient to open links within the WebView
        webView.setWebViewClient(new WebViewClient());

        // Set WebChromeClient to display progress if desired
        webView.setWebChromeClient(new WebChromeClient());

        // Load the external webpage
        webView.loadUrl(GYM_FINDER_URL);
    }

    // Override onBackPressed to handle WebView navigation
    @Override
    public void onBackPressed() {
        // If the WebView can go back, go back in the WebView history
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            // Otherwise, perform default back button behavior
            super.onBackPressed();
        }
    }
}
