package ejercicios_repaso_dia1.eje4;

import java.util.HashSet;
import java.util.Set;

public class eje4 {
    public static void main(String[] args) {
        Set<String> conjunto = new HashSet();
        conjunto.add("Hola");
        conjunto.add("mari");
        conjunto.add("levi");
        conjunto.add("Hola");
        conjunto.add("pimby my man");
        for (String str: conjunto){
            System.out.println(str);
        }
        System.out.println("\n-----------------");


    }
}
