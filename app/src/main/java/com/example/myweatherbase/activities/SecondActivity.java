package com.example.myweatherbase.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myweatherbase.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class SecondActivity extends AppCompatActivity {

    private ImageView imageViewCiudad;
    private Spinner spinnerCiudad;
    private Button buttonForecast;
    private FloatingActionButton bAddCity;
    private Ciudad city;
    private List<Ciudad> ciudades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        imageViewCiudad = findViewById(R.id.imageViewCity);
        spinnerCiudad = findViewById(R.id.spinnerCiudad);
        buttonForecast = findViewById(R.id.buttonPrevision);
        bAddCity = findViewById(R.id.addCity);

        ArrayAdapter<Ciudad> myAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                CiudadRepository.getInstance().getAll());
        spinnerCiudad.setAdapter(myAdapter);

        ActivityResultLauncher<Intent> someActivityResultLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                            if (result.getResultCode() == RESULT_CANCELED)
                                Toast.makeText(this, "Cancelado por el usuario",
                                        Toast.LENGTH_LONG).show();
                            else if (result.getResultCode() == Activity.RESULT_OK) {
                                Intent data = result.getData();
                                Ciudad ciudad;
                                if (data != null) {
                                    ciudad = (Ciudad)
                                            data.getExtras().getSerializable("ciudad");
                                    if (!(ciudad.getName().equals("") && ciudad.getPath().equals(""))) {
                                        CiudadRepository.getInstance().add(ciudad);
                                        Toast.makeText(this, "Nueva ciudad " +
                                                        ciudad.getName(),
                                                Toast.LENGTH_LONG).show();
                                    } else
                                        Toast.makeText(this, "No puede haber campos vacios", Toast.LENGTH_LONG).show();
                                }
                            }
                        });

        spinnerCiudad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                imageViewCiudad.setImageResource(CiudadRepository.getInstance().getAll().get(i).getImagen());
                city = CiudadRepository.getInstance().getAll().get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        if (savedInstanceState != null) {
            ciudades = CiudadRepository.getInstance().getAll();
            Bundle extras = getIntent().getExtras();
            if (extras != null) {
//            String name = extras.getString("name");
//            String edad = extras.getString("age");
                city = (Ciudad) extras.getSerializable("ciudad");
                CiudadRepository.getInstance().getAll().add(city);
            }
        }

        buttonForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("ciudad", city);
                someActivityResultLauncher.launch(i);
            }
        });

        bAddCity.setOnClickListener(view ->

        {
            Intent intent = new Intent(this, FormularioActivity.class);
            someActivityResultLauncher.launch(intent);
        });
    }
}
