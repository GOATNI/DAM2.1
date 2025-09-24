package eje4;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Persona persona1 = new Persona("pepe",25);
        Persona persona2 = new Persona("maria",19);
        Persona persona3 = new Persona("juan",20);
        Persona persona4 = new Persona("pedro",27);
        Persona persona5 = new Persona("Luisa",29);
        Persona persona6 = new Persona("Jose fina",22);
        Persona persona7 = new Persona("Charli",31);
        Persona persona8 = new Persona("Kirk",21);
        List<Persona> personas = List.of(persona1,persona2,persona3,persona4,persona5,persona6,persona7,persona8);
        personas.stream()
                .sorted(( p1,p2) -> p2.Edad - p1.getEdad())
                .forEach(System.out::println);
        System.out.println("\n \n");
        personas.stream()
                .sorted((p1, p2)-> p1.getNombbre().compareToIgnoreCase(p2.getNombbre()))
                .forEach(System.out::println);
    }
}
