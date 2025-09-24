package fruta.eje1;

import fruta.eje1.modelo.Fruta;

import java.util.Arrays;
import java.util.List;

public class colorDistinct_eje2 {
    public static void main(String[] args) {
        List<Fruta> fruteria = Arrays.asList(new Fruta("Manzana","roja"),new Fruta("Pera","verde")
                ,new Fruta("Naranja","Nranaja"),new Fruta("Platano","Amarillo"),new Fruta("uva","morada")
                ,new Fruta("cereza","rojo"),new Fruta("Kivi","verde"),new Fruta("Mango","Amarillo"));

        List<String> coloresNoRepetidos = fruteria.stream().map(Fruta::getColor).distinct().toList();

        System.out.println(coloresNoRepetidos);
    }
}
