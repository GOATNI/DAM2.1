package org.example.ejercicio_repaso_examen;

public class Piezas {
    int Id;
    String Nombre;
    float Precio;

    public Piezas(int id, float precio, String nombre) {
        this.Id = id;
        Precio = precio;
        Nombre = nombre;
    }


    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public float getPrecio() {
        return Precio;
    }

    public void setPrecio(float precio) {
        Precio = precio;
    }
}
