package eje_parte2;

import java.util.List;
import java.util.function.Consumer;

public class eje2 {
    public static void main(String[] args) {
        //Imprimir todos los elementos de una lista: Crea una lista de cadenas. Utiliza un
        //“Consumer” para imprimir cada cadena en la lista.

        List<String> cadenas = List.of("Hola", "Mundo", "Java", "Lambda", "Lista");

        Consumer<String> printer = n-> System.out.println(n);

       cadenas.forEach(printer);



    }
}
