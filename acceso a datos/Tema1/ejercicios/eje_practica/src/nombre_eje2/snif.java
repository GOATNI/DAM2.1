package nombre_eje2;

import java.util.List;

public class snif {
    public static void main(String[] args) {
        List<String> nombres = List.of("Ana", "Carlos", "María", "Luis", "Sofía");
        List<String> nombresFiltrados = nombres.stream().filter(n -> !n.startsWith("A")).toList();

        System.out.println(nombres);
        System.out.println(nombresFiltrados);
    }
}
