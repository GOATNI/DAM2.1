package ejercicios_repaso_dia1.eje1;

import java.util.ArrayList;
import java.util.List;

public class eje1 {
    public static void main(String[] args) {
        List<String> lista = new ArrayList<>();
        lista.add("Elemento 1");
        lista.add("Elemento 2");
        lista.add("Elemento 3");
        lista.add("Elemento 4");
        for (String e:lista){
            System.out.println(e);
        }
    }
}
