package org.example.newlistaproductos.modelo;

public class Producto {
    private String nombre;
    private int unidades;
    private Double precio;

    public Producto() {
    }

    public Producto(String nombre, int unidades, Double precio) {
        this.nombre = nombre;
        this.unidades = unidades;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", unidades=" + unidades +
                ", precio=" + precio +
                '}';
    }
}
