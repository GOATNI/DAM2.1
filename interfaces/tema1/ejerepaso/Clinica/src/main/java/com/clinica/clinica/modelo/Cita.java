package com.clinica.clinica.modelo;

import java.time.LocalDate;
import java.time.LocalTime;

public class Cita {
    private String nombrePaciente;
    private LocalDate fecha;
    private LocalTime hora;

    public Cita(String nombrePaciente, LocalDate fecha, LocalTime hora) {
        this.nombrePaciente = nombrePaciente;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return String.format("Paciente: %s | Fecha: %s | Hora: %s",
                nombrePaciente, fecha, hora);
    }
}
