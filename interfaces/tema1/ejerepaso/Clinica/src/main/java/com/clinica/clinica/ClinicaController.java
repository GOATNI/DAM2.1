package com.clinica.clinica;


import com.clinica.clinica.modelo.Cita;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ClinicaController {

    @FXML
    private TextField nombrePacienteField;

    @FXML
    private DatePicker fechaCitaPicker;

    @FXML
    private Spinner<Integer> horaSpinner;

    @FXML
    private Spinner<Integer> minutosSpinner;

    @FXML
    private TextArea citasRegistradasArea;

    @FXML
    private Button registrarButton;

    // Almacenamiento local en memoria
    private List<Cita> citas = new ArrayList<>();

    @FXML
    public void initialize() {
        // Configurar spinner de horas (0-23)
        SpinnerValueFactory<Integer> horaFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 8);
        horaSpinner.setValueFactory(horaFactory);

        // Configurar spinner de minutos (0-59)
        SpinnerValueFactory<Integer> minutosFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
        minutosSpinner.setValueFactory(minutosFactory);

        // Establecer fecha actual por defecto
        fechaCitaPicker.setValue(LocalDate.now());

        citasRegistradasArea.setEditable(false);
    }

    @FXML
    private void registrarCita() {
        String nombre = nombrePacienteField.getText().trim();
        LocalDate fecha = fechaCitaPicker.getValue();
        int hora = horaSpinner.getValue();
        int minutos = minutosSpinner.getValue();

        // Validaciones
        if (nombre.isEmpty()) {
            mostrarAlerta("Error", "Por favor ingrese el nombre del paciente");
            return;
        }

        if (fecha == null) {
            mostrarAlerta("Error", "Por favor seleccione una fecha");
            return;
        }

        // Crear la cita
        LocalTime horaCita = LocalTime.of(hora, minutos);
        Cita nuevaCita = new Cita(nombre, fecha, horaCita);

        // Guardar en memoria
        citas.add(nuevaCita);

        // Actualizar el área de texto
        actualizarListaCitas();

        // Limpiar campos
        limpiarCampos();

        mostrarAlerta("Éxito", "Cita registrada correctamente");
    }

    private void actualizarListaCitas() {
        StringBuilder sb = new StringBuilder();
        if (citas.isEmpty()) {
            sb.append("No hay citas registradas.\n");
        } else {
            for (int i = 0; i < citas.size(); i++) {
                sb.append(String.format("[%d] %s\n", i + 1, citas.get(i).toString()));
            }
        }

        citasRegistradasArea.setText(sb.toString());
    }

    private void limpiarCampos() {
        nombrePacienteField.clear();
        fechaCitaPicker.setValue(LocalDate.now());
        horaSpinner.getValueFactory().setValue(8);
        minutosSpinner.getValueFactory().setValue(0);
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(
                titulo.equals("Error") ? Alert.AlertType.ERROR : Alert.AlertType.INFORMATION
        );
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}