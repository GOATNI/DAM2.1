package org.iesch.ad.Excepciones.exception;

public class EstudianteNoEncontradoException extends RuntimeException {
    public EstudianteNoEncontradoException(Long id) {
        super("Estudiante con ID " + id + " no encontrado.");
    }

    public EstudianteNoEncontradoException(String mensaje) {
        super("Estudiante no encontrado en el ciclo "+ mensaje);
    }
}
