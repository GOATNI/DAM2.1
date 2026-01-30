package org.example;

public class Main {
    public static void main(String[] args) {
        ClienteAemet clienteAemet = new ClienteAemet();
        Serialize serialize = new Serialize();
        ValoresDiarios valoresDiarios = new ValoresDiarios();
        Estacion estacion = new Estacion();
        ClienteDatos clienteDatos = new ClienteDatos();

        System.out.println(valoresDiarios);
    }
}
