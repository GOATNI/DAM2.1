package eje4;

import java.io.*;

public class eje4 {
    public static void main(String[] args) {
        File archivoDestino = new File("destino.txt");
        try {
            FileOutputStream salidadestino = new FileOutputStream(archivoDestino);
            //recorremos la lista de archivos de origen
            for (String nombrearchivo: args){
                System.out.println(nombrearchivo);

                File archivoOrigen = new File(nombrearchivo);

                FileInputStream  entradaOrigen = new FileInputStream(archivoOrigen);

                byte[] buffer = new byte[1024];
                int byteleidos;
                while ((byteleidos = entradaOrigen.read(buffer))!= -1){
                    salidadestino.write(buffer,0,byteleidos);
                }
                entradaOrigen.close();

            }
            salidadestino.close();
            System.out.println("se ha copiado");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
