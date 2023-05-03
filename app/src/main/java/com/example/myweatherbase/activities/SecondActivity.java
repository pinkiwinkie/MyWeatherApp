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
import androidx.appcompat.app.AppCompatActivity;
import com.example.myweatherbase.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class SecondActivity extends AppCompatActivity {

    private ImageView imageViewCiudad;
    private Spinner spinnerCiudad;
    private Button buttonForecast;
    private FloatingActionButton bAddUser;
    private Ciudad city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        imageViewCiudad = findViewById(R.id.imageViewCity);
        spinnerCiudad = findViewById(R.id.spinnerCiudad);
        buttonForecast = findViewById(R.id.buttonPrevision);
        bAddUser = findViewById(R.id.addUser);

        ActivityResultLauncher<Intent> someActivityResultLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {
                        });

        ArrayAdapter<Ciudad> myAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                CiudadRepository.getInstance().getAll());
        spinnerCiudad.setAdapter(myAdapter);


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

        buttonForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.putExtra("ciudad", city);
                someActivityResultLauncher.launch(i);
            }
        });

        bAddUser.setOnClickListener(view -> {
            Intent intent = new Intent(this, FormularioActivity.class);
            someActivityResultLauncher.launch(intent);
        });
    }
}
