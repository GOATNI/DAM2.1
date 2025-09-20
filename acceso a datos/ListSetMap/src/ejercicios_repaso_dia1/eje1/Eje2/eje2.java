package ejercicios_repaso_dia1.eje1.Eje2;

import java.util.ArrayList;
import java.util.List;

public class eje2 {
    public static void main(String[] args) {
        List<Integer> lista = new ArrayList<>();
        lista.add(1);
        lista.add(2);
        lista.add(3);
        lista.add(4);
        lista.add(5);
        lista.add(6);
        System.out.println(obtenerListaPares(lista));
    }
    private static List<Integer> obtenerListaPares(List<Integer> lista){
        List<Integer> listaPares= new ArrayList<>();
        for (Integer numero :lista){
            if(numero%2 == 0){
                listaPares.add(numero);
            }
        }
        return listaPares;
    }
}
