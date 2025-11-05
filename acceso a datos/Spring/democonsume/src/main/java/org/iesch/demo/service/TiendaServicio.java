package org.iesch.demo.service;

import org.iesch.demo.modelo.Fact;
import org.iesch.demo.modelo.MiRespuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class TiendaServicio {
    @Autowired
    RestTemplate restTemplate;
    String url = "https://catfact.ninja/fact";
    public MiRespuesta obtener(int veces) {
        MiRespuesta miRespuesta = new MiRespuesta();

        for (int i = 0; i < veces; i++) {
            Fact fact = restTemplate.getForObject(url,Fact.class);
            miRespuesta.añadeFact(fact);
        }
        return miRespuesta;
    }
}
