package org.example.tabla;

public class Empleado {
    String Nombre;
    String Apellido;
    String Localidad;
    String Salario;

    public Empleado(String nombre, String apellido, String localidad, String salario) {
        Nombre = nombre;
        Apellido = apellido;
        Localidad = localidad;
        Salario = salario;
    }

    public Empleado() {
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellido() {
        return Apellido;
    }

    public void setApellido(String apellido) {
        Apellido = apellido;
    }

    public String getLocalidad() {
        return Localidad;
    }

    public void setLocalidad(String localidad) {
        Localidad = localidad;
    }

    public String getSalario() {
        return Salario;
    }

    public void setSalario(String salario) {
        Salario = salario;
    }
}
