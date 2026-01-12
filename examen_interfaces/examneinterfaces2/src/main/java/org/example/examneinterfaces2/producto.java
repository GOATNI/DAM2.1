package org.example.examneinterfaces2;

public class producto {
    String producto;
    Double precio;
    String Categoria;
    String Marca;
    String Estado;

    public producto() {
    }

    public producto(String producto, Double precio, String categoria, String marca, String estado) {
        this.producto = producto;
        this.precio = precio;
        Categoria = categoria;
        Marca = marca;
        Estado = estado;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public Double getPrecio() {
        return precio*1.21;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }

    public String getMarca() {
        return Marca;
    }

    public void setMarca(String marca) {
        Marca = marca;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }
}
