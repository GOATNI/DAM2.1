package ejercicios_repaso_dia1.eje4.eje6;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class eje5 {
    public static void main(String[] args) {
        List<String> listaString = new ArrayList<>();
        listaString.add("hokla");
        listaString.add("hoka");
        listaString.add("hola");
        listaString.add("hola");
        Set<String> conjuntoUnico = obtenerLetrasUnicos(listaString);
    }

    private static Set<String> obtenerLetrasUnicos(List<String> listaString) {
        return new HashSet<>(listaString);
    }
}
