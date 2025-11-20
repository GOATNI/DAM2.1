package org.iesch.ad.NconsultasYmas.repositorio;


import org.iesch.ad.NconsultasYmas.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibroRepositorio extends JpaRepository<Libro,Long> {

}
