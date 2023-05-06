package com.example.myweatherbase.activities;

import static java.lang.System.out;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.Root;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TerceraActividad extends AppCompatActivity {

    private TextView hora,
            sensacion,
            viento,
            rafagas,
            humedad,
            dia;
    private Root root;
    private int position;

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

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            position = getIntent().getIntExtra("position",0);
            root = (Root) extras.getSerializable("root");
            Date date = new Date((long) root.list.get(position).dt * 1000);
            SimpleDateFormat dateHour = new SimpleDateFormat("HH:mm");
            SimpleDateFormat dateDayOfWeek = new SimpleDateFormat("E");
            dia.setText(dateDayOfWeek.format(date));
            hora.setText(dateHour.format(date));
            viento.setText("" + root.list.get(position).wind.speed + " km/h");
            rafagas.setText("" + root.list.get(position).wind.gust + " km/h");
            sensacion.setText("" + root.list.get(position).main.feels_like + " ºC");
        }
    }
}
