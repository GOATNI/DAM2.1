package eje5;

import java.util.List;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<empleado> Empleados = List.of(new empleado("Juan","ventas"),
                new empleado("pedro","ventas"),
                new empleado("maria","sistemas"),
                new empleado("luis","sistemas"),
                new empleado("ana","ventas"),
                new empleado("Gorge","ventas"),
                new empleado("Marta","sistemas"),
                new empleado("Lucia","desarollo"),
                new empleado("Julia","desarollo"),
                new empleado("Carmen","desarollo"),
                new empleado("raul","desarollo"),
                new empleado("alberto","desarollo"),
                new empleado("javier","desarollo"));
            Map<String, Long> empleadosPorDepartamento = Empleados.stream()
                .collect(Collectors.groupingBy(empleado::getDepartamento, Collectors.counting()));

            System.out.println(empleadosPorDepartamento);

            String deparatamento = "ventas";
            System.out.println(empleadosPorDepartamento.get(deparatamento));

            String nombre = "juan";
             Empleados.stream().filter(n -> n.getNombre().equalsIgnoreCase(nombre))
                    .map(empleado::getDepartamento).forEach(System.out::println);




    }
}
