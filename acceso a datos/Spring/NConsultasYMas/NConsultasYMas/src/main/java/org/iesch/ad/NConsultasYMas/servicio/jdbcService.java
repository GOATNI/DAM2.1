package org.iesch.ad.NconsultasYmas.servicio;

import org.iesch.ad.NconsultasYmas.modelo.Libro;
import org.iesch.ad.NconsultasYmas.repositorio.JdbcTemplateRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class JdbcService {
    @Autowired
private JdbcTemplateRepositorio jdbcTemplateRepositorio;

    public void  demoConsultasJDBCTemplate(){
        System.out.println("Buscar libros de autores argentinos");
        List<Libro> librosArgentinos = jdbcTemplateRepositorio.findLibrosPorNacionalidadDelAutor("Argentina");

        librosArgentinos.forEach(libro ->
                System.out.printf(" · '%s' por %s %s (%.2f€, %d) %n",
                        libro.getTitulo(),
                        libro.getAutor().getNombre(),
                        libro.getAutor().getApellido(),
                        libro.getPrecio(),
                        libro.getAnioPublicacion()
                )
        );

        System.out.println("Estadisticas de libros por autor");
        System.out.println("-".repeat(50));
        List<Map<String, Object>> estadisticas = jdbcTemplateRepositorio.getEstadisticasLibrosPorAutor();

        estadisticas.forEach(estadistica ->{
            System.out.printf("P %s %s (%s):%n",
                    estadistica.get("nombre"),
                    estadistica.get("apellido"),
                    estadistica.get("nacionalidad"));
            System.out.printf("Libros: %d   |   precio promedio: %2f€   |   Periodo: %s-%s%n%n",
                    estadistica.get("total_libros"),
                    estadistica.get("precio_promedio"),
                    estadistica.get("primer_libro"),
                    estadistica.get("ultimo_libro"));
        });





    }
}
