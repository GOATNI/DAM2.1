package org.iesch.SegundoSpring.servicios;

import org.iesch.SegundoSpring.modelo.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

@Service
public class usuarioservice {
    @Autowired
    Map<Long,Usuario> usuarios;

    public Usuario adduser(Usuario usuario) {
        Long maxkey = Collections.max(usuarios.keySet());
        usuario.setId(maxkey+1);
        usuarios.put(maxkey+1,usuario);
        return usuario;
    }
}
