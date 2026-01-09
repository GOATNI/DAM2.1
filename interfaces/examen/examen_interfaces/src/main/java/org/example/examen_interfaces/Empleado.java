package org.example.examen_interfaces;


import java.sql.Date;

public class Empleado {
    protected int id;
    protected String Nombre;
    protected Date fecha;
    protected int Sexo;
    protected String departamento;
    protected double Salario;

    public Empleado(int id, String nombre, Date fecha, int sexo, String departamento, double salario) {
        this.id = id;
        Nombre = nombre;
        this.fecha = fecha;
        Sexo = sexo;
        this.departamento = departamento;
        Salario = salario;
    }

    public Empleado(String nombre, String departamento, double salario) {
        Nombre = nombre;
        this.departamento = departamento;
        Salario = salario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getSexo() {
        return Sexo;
    }

    public void setSexo(int sexo) {
        Sexo = sexo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public double getSalario() {
        return Salario;
    }

    public void setSalario(double salario) {
        Salario = salario;
    }
}
