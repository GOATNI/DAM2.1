package org.iesch.demoJPACoches.service;

import org.iesch.demoJPACoches.modelo.coche;
import org.iesch.demoJPACoches.repositorio.CocheRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CocheService {
    @Autowired
    CocheRepositorio cocheRepositorio;
    public coche guaradrcoche(coche coche) {
        return cocheRepositorio.save(coche);
    }

    public List<coche> obtenertodos() {
        return cocheRepositorio.findAll();
    }

    public coche obtener1(Long id) {
        return cocheRepositorio.findById(id).get();
    }

    public coche update1(Long id, coche coche1) {
        Optional<coche> cocheguardar = cocheRepositorio.findById(id);
        if (cocheguardar.isEmpty()){
            return null;
        }else
            return cocheRepositorio.save(coche1);

    }

    public coche delete1(Long id) {
        coche cochebbdd = cocheRepositorio.findById(id).get();
        cocheRepositorio.delete(cochebbdd);
        return cochebbdd ;
    }
}
