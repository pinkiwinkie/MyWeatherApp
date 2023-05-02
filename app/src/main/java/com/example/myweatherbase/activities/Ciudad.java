package com.example.myweatherbase.activities;

import java.io.Serializable;

public class Ciudad implements Serializable {
    private String name, path;
    private int imagen;

    public Ciudad(String name, String path, int imagen) {
        this.name = name;
        this.path = path;
        this.imagen = imagen;
    }

    public String getName() {
        return name;
    }

    public String getPath() {
        return path;
    }

    public int getImagen() {
        return imagen;
    }

    @Override
    public String toString() {
        return name;
    }
}
