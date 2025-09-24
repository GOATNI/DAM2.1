package eje_parte2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class eje3 {
    public static void main(String[] args) {
        List<Integer> numeros = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));

        Function<Integer,Integer> square = n -> n*n;

        List<Integer> numerossquare = numeros.stream().map(square).toList();

        System.out.println(numeros);
        System.out.println(numerossquare);
    }
}
