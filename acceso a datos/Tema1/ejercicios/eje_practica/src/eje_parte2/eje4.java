package eje_parte2;

import java.util.Random;
import java.util.function.Supplier;

/*Generar una secuencia de números aleatorios: Utiliza un “Supplier” para generar e
imprimir una secuencia de diez números aleatorios.*/
public class eje4 {
    public static void main(String[] args) {
        Supplier<Integer> numbers = () -> new Random().nextInt(10);

        for (int i = 0; i < 5; i++) {
            System.out.println(numbers.get());
        }
    }
}
