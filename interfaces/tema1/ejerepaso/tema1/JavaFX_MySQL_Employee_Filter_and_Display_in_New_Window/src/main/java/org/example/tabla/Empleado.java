package org.example.tabla;

public class Empleado {
    String nombre;
    String apellido;
    String localidad;
    double salario;

    public Empleado(String nombre, String apellido, String localidad, double salario) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.localidad = localidad;
        this.salario = salario;
    }

    public Empleado() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
