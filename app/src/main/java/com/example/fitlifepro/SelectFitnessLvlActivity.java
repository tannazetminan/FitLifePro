package com.example.fitlifepro;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import android.widget.AdapterView;
import android.widget.Toast;

public class SelectFitnessLvlActivity extends AppCompatActivity {

    List<String> FitnessLvl = new ArrayList<>
            (Arrays.asList("Beginner", "Intermediate", "Advanced"));
    List<String> FitnessLvlDesc = new ArrayList<>
            (Arrays.asList("You are new to fitness training", "You have been training regularly", "You're fit and ready for an intensive\nworkout plan"));
    List<FitnessLvl> FitnessList = new ArrayList<>(); //empty list

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selectfitnesslevel);

        LoadLvlData();

        Button btnNext = findViewById(R.id.btnNextStep2);

        //Instantiate ListView
        ListView listViewLvl = findViewById(R.id.listViewLvl);

        //Create adapter object
        FitnessLvlAdapter myAdapter = new FitnessLvlAdapter(FitnessList);

        //Set adapter object onto ListView
        listViewLvl.setAdapter(myAdapter);

        //set onItemClick listener for ListView
        listViewLvl.setOnItemClickListener((AdapterView<?> adapterView, View view, int i, long l) -> {
            myAdapter.setSelectedInd(i);
        });

        btnNext.setOnClickListener((View view) -> {

            String fitness_level = "Beginner";

            if (myAdapter.getSelectedInd() == -1) {
                Toast.makeText(this, "You must select your fitness level.", Toast.LENGTH_SHORT).show();
            } else {
                int index = myAdapter.getSelectedInd();
                switch (index) {
                    case 0:
                        fitness_level = "Beginner";
                        break;
                    case 1:
                        fitness_level = "Intermediate";
                        break;
                    case 2:
                        fitness_level = "Advanced";
                        break;
                }
                Bundle bundle = new Bundle();
                bundle.putString("FITNESS_LEVEL", fitness_level);

                Intent intent = new Intent(SelectFitnessLvlActivity.this, SignUpActivity.class);
                intent.putExtras(bundle);

                startActivity(intent);
            }
        });
    }

    private void LoadLvlData() {
        for (int i = 0; i < FitnessLvl.size(); i++) {
            FitnessLvl eachLvl =
                    new FitnessLvl(FitnessLvl.get(i), FitnessLvlDesc.get(i));
            FitnessList.add(eachLvl); //FitnessLvl is not null
        }
    }
}
