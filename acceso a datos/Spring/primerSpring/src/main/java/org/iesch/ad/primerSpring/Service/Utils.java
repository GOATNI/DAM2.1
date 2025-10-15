package org.iesch.ad.primerSpring.Service;


import org.springframework.stereotype.Service;

@Service
public class Utils {
    public String trasnformarAMayusculas(String texto){
        return texto.toUpperCase();
    }
}
