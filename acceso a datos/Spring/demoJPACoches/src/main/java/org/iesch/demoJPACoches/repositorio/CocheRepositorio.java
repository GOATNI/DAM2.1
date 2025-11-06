package org.iesch.demoJPACoches.repositorio;

import org.iesch.demoJPACoches.modelo.coche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocheRepositorio extends JpaRepository<coche,Long> {
}
