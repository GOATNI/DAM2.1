package nombre_eje2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class snif {
    public static void main(String[] args) {
        List<String> nombres = new ArrayList<>(Arrays.asList("Ana", "Carlos", "María", "Luis", "Sofía"));
        nombres.removeIf(n -> !n.startsWith("A"));

        System.out.println(nombres);

    }
}
