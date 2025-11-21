package com.clinica.propuesta;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.time.LocalDate;

public class PresupuestoController {

    @FXML private TextField nombreField;
    @FXML private TextField apellidosField;
    @FXML private DatePicker fechaEventoPicker;
    @FXML private Spinner<Integer> duracionSpinner;
    @FXML private TextField numInvitadosField;

    // Radio buttons para menú
    @FXML private RadioButton picoteoRadio;
    @FXML private RadioButton menu1Radio;
    @FXML private RadioButton menu2Radio;

    // CheckBoxes para extras
    @FXML private CheckBox discomovilCheck;
    @FXML private CheckBox animacionCheck;
    @FXML private CheckBox transporteCheck;

    @FXML private TextField precioTotalField;
    @FXML private Button calcularButton;

    private ToggleGroup menuGroup;

    // Precios constantes
    private static final double PRECIO_HORA = 10.0;
    private static final double PICOTEO_PRECIO = 10.0;
    private static final double MENU1_PRECIO = 15.0;
    private static final double MENU2_PRECIO = 20.0;
    private static final double DISCOMOVIL = 200.0;
    private static final double ANIMACION = 100.0;
    private static final double TRANSPORTE_POR_PERSONA = 5.0;

    @FXML
    public void initialize() {
        // Configurar grupo de radio buttons
        menuGroup = new ToggleGroup();
        picoteoRadio.setToggleGroup(menuGroup);
        menu1Radio.setToggleGroup(menuGroup);
        menu2Radio.setToggleGroup(menuGroup);

        // Seleccionar Picoteo por defecto
        picoteoRadio.setSelected(true);

        // Configurar spinner de duración (1-4 horas)
        SpinnerValueFactory<Integer> duracionFactory =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, 2);
        duracionSpinner.setValueFactory(duracionFactory);

        // Establecer fecha actual por defecto
        fechaEventoPicker.setValue(LocalDate.now());

        // Campo de precio total no editable
        precioTotalField.setEditable(false);
        precioTotalField.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

        // Validar que numInvitados solo acepte números
        numInvitadosField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (!newVal.matches("\\d*")) {
                numInvitadosField.setText(newVal.replaceAll("[^\\d]", ""));
            }
        });
    }

    @FXML
    private void calcularPresupuesto() {
        try {
            // Validar campos obligatorios
            if (!validarCampos()) {
                return;
            }

            String nombre = nombreField.getText().trim();
            String apellidos = apellidosField.getText().trim();
            LocalDate fecha = fechaEventoPicker.getValue();
            int duracion = duracionSpinner.getValue();
            int numInvitados = Integer.parseInt(numInvitadosField.getText());

            if (numInvitados <= 0) {
                mostrarAlerta("Error", "El número de invitados debe ser mayor a 0");
                return;
            }

            // Calcular precio base (duración * 10€/hora/persona * num invitados)
            double precioBase = duracion * PRECIO_HORA * numInvitados;

            // Calcular precio del menú
            double precioMenu = 0;
            if (picoteoRadio.isSelected()) {
                precioMenu = PICOTEO_PRECIO * numInvitados;
            } else if (menu1Radio.isSelected()) {
                precioMenu = MENU1_PRECIO * numInvitados;
            } else if (menu2Radio.isSelected()) {
                precioMenu = MENU2_PRECIO * numInvitados;
            }

            // Calcular extras
            double extras = 0;
            if (discomovilCheck.isSelected()) {
                extras += DISCOMOVIL;
            }
            if (animacionCheck.isSelected()) {
                extras += ANIMACION;
            }
            if (transporteCheck.isSelected()) {
                extras += TRANSPORTE_POR_PERSONA * numInvitados;
            }

            // Precio total
            double precioTotal = precioBase + precioMenu + extras;

            // Mostrar resultado
            precioTotalField.setText(String.format("%.2f €", precioTotal));

            // Mostrar desglose en un diálogo
            mostrarDesglose(nombre, apellidos, fecha, duracion, numInvitados,
                    precioBase, precioMenu, extras, precioTotal);

        } catch (NumberFormatException e) {
            mostrarAlerta("Error", "Por favor ingrese un número válido de invitados");
        }
    }

    private boolean validarCampos() {
        if (nombreField.getText().trim().isEmpty()) {
            mostrarAlerta("Error", "Por favor ingrese el nombre");
            return false;
        }
        if (apellidosField.getText().trim().isEmpty()) {
            mostrarAlerta("Error", "Por favor ingrese los apellidos");
            return false;
        }
        if (fechaEventoPicker.getValue() == null) {
            mostrarAlerta("Error", "Por favor seleccione la fecha del evento");
            return false;
        }
        if (numInvitadosField.getText().trim().isEmpty()) {
            mostrarAlerta("Error", "Por favor ingrese el número de invitados");
            return false;
        }
        return true;
    }

    private void mostrarDesglose(String nombre, String apellidos, LocalDate fecha,
                                 int duracion, int invitados, double base,
                                 double menu, double extras, double total) {
        String menuSeleccionado = "";
        if (picoteoRadio.isSelected()) menuSeleccionado = "Picoteo";
        else if (menu1Radio.isSelected()) menuSeleccionado = "Menú 1";
        else if (menu2Radio.isSelected()) menuSeleccionado = "Menú 2";

        StringBuilder sb = new StringBuilder();
        sb.append("═══════════════════════════════════════\n");
        sb.append("    PRESUPUESTO DEL EVENTO\n");
        sb.append("═══════════════════════════════════════\n\n");
        sb.append(String.format("Cliente: %s %s\n", nombre, apellidos));
        sb.append(String.format("Fecha: %s\n", fecha));
        sb.append(String.format("Duración: %d hora(s)\n", duracion));
        sb.append(String.format("Nº Invitados: %d\n\n", invitados));
        sb.append("-----------------------------------\n");
        sb.append(String.format("Base (duración × invitados): %.2f €\n", base));
        sb.append(String.format("Menú (%s): %.2f €\n", menuSeleccionado, menu));
        sb.append(String.format("Extras: %.2f €\n", extras));
        sb.append("-----------------------------------\n");
        sb.append(String.format("TOTAL: %.2f €\n", total));
        sb.append("═══════════════════════════════════════\n");

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Presupuesto Calculado");
        alert.setHeaderText("Desglose del Presupuesto");
        alert.setContentText(sb.toString());
        alert.showAndWait();
    }

    @FXML
    private void limpiarFormulario() {
        nombreField.clear();
        apellidosField.clear();
        fechaEventoPicker.setValue(LocalDate.now());
        duracionSpinner.getValueFactory().setValue(2);
        numInvitadosField.clear();
        picoteoRadio.setSelected(true);
        discomovilCheck.setSelected(false);
        animacionCheck.setSelected(false);
        transporteCheck.setSelected(false);
        precioTotalField.clear();
    }

    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}