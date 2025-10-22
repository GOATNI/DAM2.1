package org.iesch.ad.EjemploJson.service;

import org.iesch.ad.EjemploJson.modelo.Persona;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

@Service
public class GuardaDatos {
    public String guarda(Persona persona){
        Path path = Paths.get("archivo.txt");
        try{
            Files.write(path, Arrays.asList(persona.toString()),
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND);
            return "Texto introducido correctamente";
        } catch (IOException e){
            e.printStackTrace();
        }
        return "Error al escribir en el fichero";
    }
}
