package ejercicios_repaso_dia1.eje4.eje5;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class eje5 {
    public static void main(String[] args) {
        List<Integer> listaInteros = new ArrayList<>();
        listaInteros.add(5);
        listaInteros.add(52);
        listaInteros.add(52);
        listaInteros.add(520);
        listaInteros.add(520);
        listaInteros.add(96);
        listaInteros.add(37);
        listaInteros.add(5200);

        Set<Integer> conjuntoUnico = obtenerNumerosUnicos(listaInteros);

        System.out.println(conjuntoUnico);
    }

    public static Set<Integer> obtenerNumerosUnicos(List<Integer> listaInteros) {
        return new HashSet<>(listaInteros);
    }
}
