package org.example.newslidertable;

public class Provincia {
   private String Nombre;
   private String Autonomia;
   private int Poblacion;

    public Provincia(String nombre, String autonomia, int poblacion) {
        Nombre = nombre;
        Autonomia = autonomia;
        Poblacion = poblacion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getAutonomia() {
        return Autonomia;
    }

    public void setAutonomia(String autonomia) {
        Autonomia = autonomia;
    }

    public int getPoblacion() {
        return Poblacion;
    }

    public void setPoblacion(int poblacion) {
        Poblacion = poblacion;
    }
}
