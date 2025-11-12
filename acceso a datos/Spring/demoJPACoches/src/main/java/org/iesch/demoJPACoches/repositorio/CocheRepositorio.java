package org.iesch.demoJPACoches.repositorio;

import org.iesch.demoJPACoches.modelo.coche;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CocheRepositorio extends JpaRepository<coche,Long> {

    List<coche> findBycolor(String color);
    List<coche> findBycolorandmarca(String color,String marca);

}
