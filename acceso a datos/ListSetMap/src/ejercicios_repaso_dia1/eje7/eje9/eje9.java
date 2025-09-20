package ejercicios_repaso_dia1.eje7.eje9;

import ejercicios_repaso_dia1.eje7.eje9.modelo.Estudiante;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class eje9 {
    public static void main(String[] args) {
        List<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(new Estudiante("shlat",32,"joel","Dam"));
        estudiantes.add(new Estudiante("shilp",85,"jl","Dawn"));
        estudiantes.add(new Estudiante("saul",72,"goodman","law"));

        Map<String,Estudiante> mapaEstudiante = mapearEstudiantes(estudiantes);
        for (Map.Entry<String,Estudiante> est : mapaEstudiante.entrySet()){
            System.out.println(est);
        }
    }

    private static Map<String, Estudiante> mapearEstudiantes(List<Estudiante> estudiantes) {
        Map<String,Estudiante> Estudiantes = new HashMap<>();
        for (Estudiante estudiante: estudiantes){
            Estudiantes.put(estudiante.getNombre(),estudiante);
        }
        return Estudiantes;
    }
}
