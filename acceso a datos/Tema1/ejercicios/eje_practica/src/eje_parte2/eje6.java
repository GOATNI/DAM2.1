package eje_parte2;

import java.util.*;
import java.util.function.Consumer;

/*Aplicar una operación a cada elemento de una lista: Crea una lista de números
enteros. Utiliza un “Consumer” para aplicar una operación a cada número en la
lista (por ejemplo, multiplicar cada número por 2).*/
public class eje6 {
    public static void main(String[] args) {

        List<Integer> numeros = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        Consumer<Integer> consumer = n -> System.out.print(n*2+" ");

        numeros.forEach(consumer);
        }


}
