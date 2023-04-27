package com.example.myweatherbase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherbase.API.Connector;
import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.model.Root;
import com.example.myweatherbase.base.BaseActivity;
import com.example.myweatherbase.base.CallInterface;
import com.example.myweatherbase.base.ImageDownloader;
import com.example.myweatherbase.base.Parameters;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends BaseActivity implements CallInterface {

   private TextView textViewCiudad;
    private Root root;
    private RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler);
        textViewCiudad = findViewById(R.id.textViewCiudad);




        ActivityResultLauncher<Intent> someActivityResultLauncher =
                registerForActivityResult(
                        new ActivityResultContracts.StartActivityForResult(),
                        result -> {});
//        txtView = findViewById(R.id.txtView);
//        textViewDay = findViewById(R.id.textViewDay);
//        textViewDayOfWeek = findViewById(R.id.textViewDayOfWeek);
//        txtView = findViewById(R.id.txtView);
//        imageView = findViewById(R.id.imageView);

        // Mostramos la barra de progreso y ejecutamos la llamada a la API
        showProgress();
        executeCall(this);
    }

    // Realizamos la llamada y recogemos los datos en un objeto Root
    @Override
    public void doInBackground() {
        root = Connector.getConector().get(Root.class,"&lat=39.5862518&lon=-0.5411163");
    }

    // Una vez ya se ha realizado la llamada, ocultamos la barra de progreso y presentamos los datos
    @Override
    public void doInUI() {
        hideProgress();
        AdaptadorRecicler adaptador = new AdaptadorRecicler(this, root);
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        textViewCiudad.setText(root.getCity());
//        txtView.setText(root.list.get(0).weather.get(0).description);//cielo
//        ImageDownloader.downloadImage(Parameters.ICON_URL_PRE + root.list.get(0).weather.get(0).icon + Parameters.ICON_URL_POST, imageView);
//
//        Date date = new Date((long)root.list.get(0).dt*1000);
//        SimpleDateFormat dateDayOfWeek = new SimpleDateFormat("E");
//        SimpleDateFormat dateDay = new SimpleDateFormat("EEE, d MMM yyyy HH:mm");
//        textViewDayOfWeek.setText(dateDayOfWeek.format(date));
//        textViewDay.setText(dateDay.format(date));

        //para inicializar RecyclerView y asociarle el LinearLayout ha de ser despues del hideProgress()


    }
}