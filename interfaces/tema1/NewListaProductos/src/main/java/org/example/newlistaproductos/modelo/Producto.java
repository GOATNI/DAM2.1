package org.example.newlistaproductos.modelo;

public class Producto {
    private String Nombre;
    private int Unidades;
    private Double Precio;

    public Producto() {
    }

    public Producto(String nombre, int unidades, Double precio) {
        Nombre = nombre;
        Unidades = unidades;
        Precio = precio;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getUnidades() {
        return Unidades;
    }

    public void setUnidades(int unidades) {
        Unidades = unidades;
    }

    public Double getPrecio() {
        return Precio;
    }

    public void setPrecio(Double precio) {
        Precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "Nombre='" + Nombre + '\'' +
                ", Unidades=" + Unidades +
                ", Precio=" + Precio +
                '}';
    }
}
