package com.example.myweatherbase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myweatherbase.R;

public class TerceraActividad extends AppCompatActivity {

    private TextView hora,
            sensacion,
            viento,
            rafagas,
            humedad,
            dia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.third_activity);

        dia = findViewById(R.id.textViewdia);
        hora = findViewById(R.id.textViewHora);
        sensacion = findViewById(R.id.textViewGetSensacion);
        viento = findViewById(R.id.textViewGetViento);
        rafagas = findViewById(R.id.textViewGetRafagas);
        humedad = findViewById(R.id.textViewGetHumedad);
    }

     ActivityResultLauncher<Intent> someActivityResultLauncher =
                    registerForActivityResult(
                            new ActivityResultContracts.StartActivityForResult(),
                            result -> {
                            });
}
