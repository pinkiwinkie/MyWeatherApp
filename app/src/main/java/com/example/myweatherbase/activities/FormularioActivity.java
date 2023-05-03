package com.example.myweatherbase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myweatherbase.R;
import com.google.android.material.textfield.TextInputEditText;

public class FormularioActivity extends AppCompatActivity {

    private TextInputEditText tietNombre;
    private TextInputEditText tietLat;
    private TextInputEditText tietLong;
    private Button bAceptar;
    private Button bCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.formulario_activity);
        tietNombre = findViewById(R.id.tietNombre);
        tietLat = findViewById(R.id.tietLatitud);
        tietLong = findViewById(R.id.tietlongitud);
        bAceptar = findViewById(R.id.buttonAceptar);
        bCancelar = findViewById(R.id.buttonCancelar);

        bCancelar.setOnClickListener(v -> {
            Intent i = new Intent();
            setResult(RESULT_CANCELED, i);
            finish();
        });

        bAceptar.setOnClickListener(v -> {
            Intent i = new Intent();
            String nombre = String.valueOf(tietNombre.getText());
            String latitud = String.valueOf(tietLat.getText());
            String longi = String.valueOf(tietLong.getText());
            i.putExtra("ciudad", new Ciudad(nombre, getPath(latitud, longi), R.mipmap.ic_generalphoto_foreground));
            setResult(RESULT_OK, i);
            finish();
        });
    }

    public static String getPath(String latitud, String longitiud) {
        return "&lat=" + latitud + "&lon=" + longitiud;
    }
}
//añadir preferencias -> preferences de androidx. en gitHub hay ejemplo.