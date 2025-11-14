package org.example.controlsfx_demo;

public class Capitales {
    public String Provincias;
    public String Autonomia;
    public int Poblacion;

    public Capitales() {
    }

    public Capitales(String provincias, String autonomia,int poblacion) {
        Provincias = provincias;
        Autonomia = autonomia;
        Poblacion = poblacion;
    }

    public String getProvincias() {
        return Provincias;
    }

    public void setProvincias(String provincias) {
        Provincias = provincias;
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

    @Override
    public String toString() {
        return "Capitales{" +
                "Provincias='" + Provincias + '\'' +
                ", Autonomia='" + Autonomia + '\'' +
                ", Poblacion='" + Poblacion + '\'' +
                '}';
    }
}

