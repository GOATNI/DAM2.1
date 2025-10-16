package org.iesch.ad.primerSpring.Service;


import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Utils {


    public  String generaContrasenasAZAlfanumericas(int longitud) {
        Random rn = new Random();
        final String Leaters ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder passwd = new StringBuilder(longitud);
        for (int i = 0; i < longitud; i++) {
            int index = rn.nextInt(Leaters.length());
            passwd.append(Leaters.charAt(index));
        }

        return passwd.toString();
    }

    public  double convierteamillas(int km) {
        float Amillas = 1.069F;
        return km/Amillas;
    }

    public  double convierteafarenhight(int cels) {
        return (cels*9/5+32);
    }

    public String trasnformarAMayusculas(String texto){
        return texto.toUpperCase();
    }
    public  String generaContrasenasAZ( int numCaracter){
        Random rn = new Random();
        final String Leaters ="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder passwd = new StringBuilder(numCaracter);
        for (int i = 0; i < numCaracter ; i++) {
            int index = rn.nextInt(Leaters.length());
            passwd.append(Leaters.charAt(index));
        }

        return passwd.toString();
    }
}
