package ejercicios_repaso_dia1.eje1.eje3;

import java.util.ArrayList;
import java.util.List;

public class eje3 {
    public static void main(String[] args) {

            List<String> lista = new ArrayList<>();
            lista.add("Elemento 1");
            lista.add("Elemento 13");
            lista.add("Elemento 165");
            lista.add("Elemento 4157");

            int maxlongitud = longitudmaxima(lista);
        System.out.println(maxlongitud);
    }

    private static int longitudmaxima(List<String> lista) {
        int maxLeanth = 0;
        for (String srt: lista){
            if(srt.length()> maxLeanth){
                maxLeanth = srt.length();
            }
        }
        return maxLeanth;
    }


}
