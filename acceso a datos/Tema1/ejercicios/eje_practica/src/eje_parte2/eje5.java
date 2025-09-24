package eje_parte2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/*5) Filtrar nombres que comienzan con una letra específica: Crea una lista de
nombres. Utiliza un “Predicate” para filtrar la lista y mantener solo los nombres
que comienzan con una letra específica (por ejemplo, "A").*/
public class eje5 {
    public static void main(String[] args) {
        List<String> nombres = new ArrayList<>(Arrays.asList("Ana", "Carlos", "María", "Luis", "Sofía"));
        Predicate<String> filtring = n -> !n.startsWith("A");
         nombres.removeIf(filtring);
        nombres.forEach(System.out::println);


    }
}
