package org.example.enenuevo;

public class Empleado {
    private  String nombre;
    private  String apellidos;
    private  String localidad;
    private  String salario;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public void setSalario(String salario) {
        this.salario = salario;
    }

    public Empleado(String nombre, String apellidos, String localidad, String salario) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.localidad = localidad;
        this.salario = salario;
    }


}
