package fruta.eje1;

import fruta.eje1.modelo.Fruta;

import java.util.Arrays;
import java.util.List;

public class Fruteria {
    public static void main(String[] args) {
        List<Fruta> fruteria = Arrays.asList(new Fruta("Manzana","roja"),new Fruta("Pera","verde")
        ,new Fruta("Naranja","Nranaja"),new Fruta("Platano","Amarillo"),new Fruta("uva","morada")
        ,new Fruta("cereza","rojo"),new Fruta("Kivi","verde"),new Fruta("Mango","Amarillo"));

        System.out.println(fruteria);

        List<String> nombres = fruteria.stream().map(n -> n.getNombre()).toList();

        System.out.println(nombres);
    }
}
