package org.iesch.ad.dgt_api.model;

import org.iesch.ad.dgt_api.model.enums.TipoCombustible;
import lombok.Data;

@Data
public class CaracteristicasTecnicas {
    private Integer cilindrada;
    private Integer potencia;
    private String numeroBastidores;
    private TipoCombustible combustible;
    private String emisiones;
    private Integer plazas;
    private Integer pesoMaximo;
    private String tipoMotor;
}