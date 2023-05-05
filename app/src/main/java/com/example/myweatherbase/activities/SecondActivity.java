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
    private Ciudad cityOption, city;
    private List<Ciudad> ciudades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        imageViewCiudad = findViewById(R.id.imageViewCity);
        spinnerCiudad = findViewById(R.id.spinnerCiudad);
        buttonForecast = findViewById(R.id.buttonPrevision);
        bAddCity = findViewById(R.id.addCity);

        if (savedInstanceState != null) {
            ciudades = (ArrayList<Ciudad>) savedInstanceState.getSerializable("ciudad");
        } else {
            ciudades = new ArrayList<>(CiudadRepository.getInstance().getAll());
        }

        ArrayAdapter<Ciudad> myAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, ciudades);
        spinnerCiudad.setAdapter(myAdapter);


        ActivityResultLauncher<Intent> someActivityResultLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                            if (result.getResultCode() == Activity.RESULT_CANCELED) {
                                Toast.makeText(this, "CANCELLED", Toast.LENGTH_LONG).show();
                            } else {
                                Intent data = result.getData();
                                city = (Ciudad) data.getExtras().getSerializable("ciudad");
                                ciudades.add(city);
                                myAdapter.notifyDataSetChanged();
                            }
                        });

        spinnerCiudad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                imageViewCiudad.setImageResource(ciudades.get(i).getImagen());
                cityOption = ciudades.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });


        buttonForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("ciudad", cityOption);
                someActivityResultLauncher.launch(i);
            }
        });

        bAddCity.setOnClickListener(view -> {
            Intent intent = new Intent(this, FormularioActivity.class);
            someActivityResultLauncher.launch(intent);
        });
    }
}
