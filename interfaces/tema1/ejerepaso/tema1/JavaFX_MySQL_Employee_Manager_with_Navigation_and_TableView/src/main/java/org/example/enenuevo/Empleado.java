package org.example.enenuevo;

public class Empleado {
    private String nombre;
    private String apellidos;
    private String localidad;
    private double salario;

    public Empleado(String nombre, String apellidos, String localidad, double salario) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.localidad = localidad;
        this.salario = salario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    @Override
    public String toString() {
        return "Empleado{" +
                "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", localidad='" + localidad + '\'' +
                ", salario=" + salario +
                '}';
    }
}
