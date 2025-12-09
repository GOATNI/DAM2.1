package com.example.jobapp.excepciones;

public class JobNoEncontrado extends RuntimeException{
    public JobNoEncontrado(String message){
        super("No Encontre El job con el id"+message);
    }
}
