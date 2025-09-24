package eje3;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Integer> numeros = Arrays.asList(1,2,3,4,5,6);
        int numerosCuadrados = numeros.stream().map(n -> n*n).reduce(0,Integer::sum);


        System.out.println(numerosCuadrados);
    }
}
