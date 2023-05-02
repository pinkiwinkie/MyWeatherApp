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
    private String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

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
                i.putExtra("name", city);
                someActivityResultLauncher.launch(i);
            }
        });

        ArrayAdapter<Ciudad> myAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                CiudadRepository.getInstance().getAll());
        spinnerCiudad.setAdapter(myAdapter);


        spinnerCiudad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0){
                    city = CiudadRepository.getInstance().getAll().get(0).getName();
                    imageViewCiudad.setImageResource(CiudadRepository.getInstance().getAll().get(0).getImagen());
                } else if (i == 1) {
                    city = CiudadRepository.getInstance().getAll().get(1).getName();
                    imageViewCiudad.setImageResource(CiudadRepository.getInstance().getAll().get(1).getImagen());
                }else if (i == 2) {
                    city = CiudadRepository.getInstance().getAll().get(2).getName();
                    imageViewCiudad.setImageResource(CiudadRepository.getInstance().getAll().get(2).getImagen());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
