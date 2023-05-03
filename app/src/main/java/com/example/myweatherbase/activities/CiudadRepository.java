package com.example.myweatherbase.activities;

import com.example.myweatherbase.R;

import java.util.ArrayList;
import java.util.List;

public class CiudadRepository {
    private List<Ciudad> cities;
    private static CiudadRepository instance;

    public CiudadRepository() {
        cities = new ArrayList<>();
        cities.add(new Ciudad("Valencia","&lat=39.40793438&lon=-0.5263232", R.mipmap.ic_valencia_foreground));
        cities.add(new Ciudad("Madrid","&lat=40.4380986&lon=-3.8443483", R.mipmap.ic_madrid_foreground));
        cities.add(new Ciudad("Barcelona","&lat=41.7571627&lon=1.4092638 ", R.mipmap.ic_barcelona_foreground));
    }

    public static CiudadRepository getInstance() {
        if (instance == null)
            instance = new CiudadRepository();
        return instance;
    }

    public void add(Ciudad ciudad) {
        cities.add(ciudad);
    }
    public List<Ciudad> getAll() {
        return new ArrayList<>(cities);
    }

}
