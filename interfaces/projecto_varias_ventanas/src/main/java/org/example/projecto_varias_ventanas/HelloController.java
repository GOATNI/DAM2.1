package org.example.projecto_varias_ventanas;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.Calendar;

public class HelloController {
    @FXML
    private TextField nombre;
    @FXML
    private TextField apellidos;
    @FXML
    private TextField Dni;
    @FXML
    private TextField fecha;
    @FXML
    private TextArea Consulta;
    @FXML
    private Button helpDni;
    @FXML
    private Button Enviar;

    @FXML
    private CheckBox checkBox;

    @FXML
    protected void help(){
        Ventana2 ventana2 = new Ventana2();
        try {
            ventana2.abrir();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}