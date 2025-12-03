package org.iesch.ad.demoValid.excepciones;

public class Miexcepcion extends RuntimeException{
    public Miexcepcion(String mensaje){
        super(" " +mensaje);
    }

}
