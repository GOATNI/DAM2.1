package org.iesch.ad.dgt_api.model;

import org.iesch.ad.dgt_api.model.enums.TipoVehiculo;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "vehiculos")
public class Vehiculo {
    @Id
    private String id;

    @Indexed(unique = true)
    private String matricula;

    @Indexed(unique = true)
    private String bastidor;

    private String marca;
    private String modelo;
    private String color;

    private TipoVehiculo tipoVehiculo; // Usamos tu Enum aquí

    private Integer anioFabricacion;
    private LocalDateTime fechaPrimeraMatriculacion;

    // Aquí "embebemos" las clases que acabas de crear arriba
    private CaracteristicasTecnicas caracteristicasTecnicas;
    private Titular titular;
    private SituacionAdministrativa situacionAdministrativa;
    private Itv itv;
    private Impuestos impuestos;

    // Listas
    private List<Multa> multas = new ArrayList<>();
    private List<HistorialTitular> historialTitulares = new ArrayList<>();

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}