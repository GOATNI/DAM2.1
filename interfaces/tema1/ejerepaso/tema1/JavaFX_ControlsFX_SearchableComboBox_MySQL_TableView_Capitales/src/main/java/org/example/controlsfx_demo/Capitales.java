package org.example.controlsfx_demo;

public class Capitales {
    public String provincias;
    public String autonomia;
    public int poblacion;

    public Capitales() {
    }

    public Capitales(String provincias, String autonomia,int poblacion) {
        this.provincias = provincias;
        this.autonomia = autonomia;
        this.poblacion = poblacion;
    }

    public String getProvincias() {
        return provincias;
    }

    public void setProvincias(String provincias) {
        this.provincias = provincias;
    }

    public String getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(String autonomia) {
        this.autonomia = autonomia;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }

    @Override
    public String toString() {
        return "Capitales{" +
                "provincias='" + provincias + '\'' +
                ", autonomia='" + autonomia + '\'' +
                ", poblacion='" + poblacion + '\'' +
                '}';
    }
}

