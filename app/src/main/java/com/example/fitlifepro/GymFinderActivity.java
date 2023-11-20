


// GymFinderActivity.java
package com.example.fitlifepro;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.List;

public class GymFinderActivity extends AppCompatActivity {

    private TextView gymInfoTextView; // Add this variable

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gym_finder);

        // Initialize the TextView
        gymInfoTextView = findViewById(R.id.gymInfoTextView);

        // Example usage
        searchPlaces("gym");
    }

    private void searchPlaces(String query) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://nominatim.openstreetmap.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NominatimService service = retrofit.create(NominatimService.class);

        Call<List<NominatimPlace>> call = service.search(query, 5);
        call.enqueue(new Callback<List<NominatimPlace>>() {
            @Override
            public void onResponse(Call<List<NominatimPlace>> call, Response<List<NominatimPlace>> response) {
                if (response.isSuccessful()) {
                    List<NominatimPlace> places = response.body();
                    // Process the list of places
                    for (NominatimPlace place : places) {
                        String displayName = place.getDisplayName();
                        // Log the place information for debugging
                        Log.d("GymFinderActivity", "Place: " + displayName);

                        // Display gym information in the TextView
                        gymInfoTextView.setText("Nearest Gym: " + displayName);
                    }
                } else {
                    // Handle error
                    Log.e("GymFinderActivity", "Error: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<NominatimPlace>> call, Throwable t) {
                Log.e("GymFinderActivity", "Error: " + t.getMessage(), t);
            }
        });

    }
}
