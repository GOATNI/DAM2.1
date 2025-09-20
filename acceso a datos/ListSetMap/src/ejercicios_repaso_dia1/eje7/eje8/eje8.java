package ejercicios_repaso_dia1.eje7.eje8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class eje8 {
    public static void main(String[] args) {
        List<String> Lista = new ArrayList<>();
        Lista.add("hola");
        Lista.add("wue");
        Lista.add("hola");
        Lista.add("mundo");


        Map<String,Integer> frecuencia = contarFrecuenciaStrings(Lista);
        for (Map.Entry<String,Integer> i: frecuencia.entrySet()){
            System.out.println(i);
        }

    }

    private static Map<String, Integer> contarFrecuenciaStrings(List<String> lista) {
        Map<String,Integer> frecuencia = new HashMap<>();
        for (String str: lista){
            frecuencia.put(str,frecuencia.getOrDefault(str,0)+1);
        }
        return frecuencia;
    }
}
