package com.example.myweatherbase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myweatherbase.API.Connector;
import com.example.myweatherbase.R;
import com.example.myweatherbase.activities.logic.AdaptadorRecicler;
import com.example.myweatherbase.activities.model.Root;
import com.example.myweatherbase.base.BaseActivity;
import com.example.myweatherbase.base.CallInterface;

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

        // Mostramos la barra de progreso y ejecutamos la llamada a la API
        showProgress();
        executeCall(this);
    }

    // Realizamos la llamada y recogemos los datos en un objeto Root

    @Override
    public void doInBackground() {
        Bundle bundle = getIntent().getExtras();
        Ciudad ciudad = (Ciudad)bundle.get("ciudad");
        textViewCiudad.setText(ciudad.getName());
        root = Connector.getConector().get(Root.class, ciudad.getPath());
    }

    // Una vez ya se ha realizado la llamada, ocultamos la barra de progreso y presentamos los datos
    //para inicializar RecyclerView y asociarle el LinearLayout ha de ser despues del hideProgress()
    @Override
    public void doInUI() {
        hideProgress();
        AdaptadorRecicler adaptador = new AdaptadorRecicler(this, root);
        recyclerView.setAdapter(adaptador);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}