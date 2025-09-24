package eje_parte2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class eje1 {
    public static void main(String[] args) {
        List<Integer> numeros = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        Predicate<Integer> numerosimapres= n -> n % 2 != 0;

        numeros.removeIf(numerosimapres);

        System.out.println(numeros);
    }
}
