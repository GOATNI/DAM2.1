package org.iesch.ad.dgt_api.model;

import lombok.Data;

@Data
public class Domicilio {
    private String calle;
    private String numero;
    private String piso;
    private String puerta;
    private String codigoPostal;
    private String localidad;
    private String provincia;
}