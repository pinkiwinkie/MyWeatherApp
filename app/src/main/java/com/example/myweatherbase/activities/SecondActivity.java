package com.example.myweatherbase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myweatherbase.R;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private ImageView imageViewCiudad;
    private Spinner spinnerCiudad;
    private Button buttonForecast;
    private ArrayList<Ciudad> cities;
    private Ciudad city;

 //   private String[] asd={"mdd","bcn","vlc"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        cities = new ArrayList<>();
        cities.add(new Ciudad("Valencia","&lat=39.40793438&lon=-0.5263232"));
        cities.add(new Ciudad("Madrid","&lat=40.4380986&lon=-3.8443483"));
        cities.add(new Ciudad("Barcelona","&lat=41.7571627&lon=1.4092638 "));
        imageViewCiudad = findViewById(R.id.imageViewCity);
        spinnerCiudad = findViewById(R.id.spinnerCiudad);
        buttonForecast = findViewById(R.id.buttonPrevision);

        ActivityResultLauncher<Intent> someActivityResultLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                        });

        buttonForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                // i.putExtra("option");
                someActivityResultLauncher.launch(i);
            }
        });


        if (savedInstanceState != null)
            cities = (ArrayList<Ciudad>) savedInstanceState.getSerializable("city");
        else {
            cities = new ArrayList<>();
            Bundle extras = getIntent().getExtras();
            if (extras != null){
                city = (Ciudad)extras.getSerializable("city");
                cities.add(city);
            }
        }

        ArrayAdapter<Ciudad> myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
        spinnerCiudad.setAdapter(myAdapter);

        spinnerCiudad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("city", cities);
    }
}
