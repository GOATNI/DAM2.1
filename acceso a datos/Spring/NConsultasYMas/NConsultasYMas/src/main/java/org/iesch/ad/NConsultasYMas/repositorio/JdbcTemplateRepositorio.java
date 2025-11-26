package org.iesch.ad.NconsultasYmas.repositorio;

import org.iesch.ad.NconsultasYmas.modelo.Autor;
import org.iesch.ad.NconsultasYmas.modelo.Libro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTemplateRepositorio {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    //Buscar libros por nacionalidad del autor
    public List<Libro> findLibrosPorNacionalidadDelAutor(String nacionalidad) {
        String sql = """
                SELECT l.id, l.titulo, l.isbn, l.precio, l.anio_publicacion,
                a.id as autor_id, a.nombre, a.apellido, a.nacionalidad
                FROM libros l
                INNER JOIN autores a ON l.autor_id = a.id
                WHERE a.nacionalidad = ?
                ORDER BY l.titulo
                """;
        return jdbcTemplate.query(sql, new LibroConAutorRowMapper(), nacionalidad);
    }

    public List<Map<String, Object>> getEstadisticasLibrosPorAutor() {
        String sql = """
                SELECT a.nombre, a.apellido, a.nacionalidad,
                count(l.id) as total_libros,
                AVG(l.precio) as precio_promedio,
                MIN(l.anio_publicacion) as primer_libro,
                MAX(l.anio_publicacion) as ultimo_libro
                FROM autores a
                LEFT JOIN libros l ON a.id = l.autor_id
                GROUP BY a.nombre, a.apellido, a.nacionalidad
                ORDER BY total_libros DESC
                """;
        return jdbcTemplate.queryForList(sql);



    }
// buscar libros qye esten en eel rango de precio y del anio
    public List<Libro> encontrarLibrosPorPreciosYanio(double precioInicial, double precioFinal, int anio) {
        String sql = """
                SELECT l.id, l.titulo, l.isbn, l.precio, l.anio_publicacion, a.id AS autor_id, a.nombre, a.apellido
                FROM libros l
                INNER JOIN autores a ON l.autor_id = a.id
                WHERE l.precio BETWEEN ? AND ?
                AND l.anio_publicacion >= ?
                ORDER BY l.precio DESC, l.anio_publicacion DESC
                """;
        return jdbcTemplate.query(sql,new LibroConAutorRowMapper(),precioInicial,precioFinal,anio);
    }


    private static class LibroConAutorRowMapper implements RowMapper<Libro> {
        @Override
        public Libro mapRow(ResultSet rs, int rowNum) throws SQLException{
            Autor autor = new Autor();
            autor.setId(rs.getLong("autor_id"));
            autor.setNombre(rs.getString("nombre"));
            autor.setApellido(rs.getString("apellido"));
            autor.setNacionalidad(rs.getString("nacionalidad"));

            Libro libro = new Libro();
            libro.setId(rs.getLong("id"));
            libro.setTitulo(rs.getString("titulo"));
            libro.setIsbn(rs.getString("isbn"));
            libro.setPrecio(rs.getDouble("precio"));
            libro.setAnioPublicacion(rs.getInt("anio_publicacion"));
            libro.setAutor(autor);

            return libro;
        }
    }
}
