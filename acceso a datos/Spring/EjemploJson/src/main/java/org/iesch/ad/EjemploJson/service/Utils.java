package org.iesch.ad.EjemploJson.service;

import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class Utils {


    public String transformarAMayusculas(String texto) {
        return texto.toUpperCase();
    }
    public static String generaContraseñasAZ(int numeroCaracteres){
        final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder passwd = new StringBuilder(numeroCaracteres);
        Random r = new Random();
        for (int i = 0; i < numeroCaracteres; i++) {
            int index = r.nextInt(LETTERS.length());
            passwd.append(LETTERS.charAt(index));
        }

        return passwd.toString();
    }
    public static String generaContraseñasAlfanumericas(int numeroCaracteres){
        final String LETTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder passwd = new StringBuilder(numeroCaracteres);
        Random r = new Random();
        for (int i = 0; i < numeroCaracteres; i++) {
            int index = r.nextInt(LETTERS.length());
            passwd.append(LETTERS.charAt(index));
        }

        return passwd.toString();
    }

}
