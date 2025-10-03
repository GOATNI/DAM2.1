package org.example.alerta;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Button concabezado;
    @FXML
    private Button sincabezado;
    @FXML
    private Button error;
    @FXML
    private Button confirmacion;


    @FXML
    protected void Concabezado() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Esto es el título");
        alert.setHeaderText("Esto es el encabezado");
        alert.setContentText("Esto es el contenido");

        alert.show();
    }
    @FXML
    protected void Sincabezado(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Esto es el título");
        alert.setContentText("Esto es el contenido");

        alert.show();
    }
    @FXML
    protected void er(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Esto es el título");
        alert.setHeaderText("Esto es el encabezado");
        alert.setContentText("Esto es el contenido");

        alert.show();
    }
    @FXML
    private void confirmacion(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Esto es el título");
        alert.setHeaderText("Esto es el encabezado");
        alert.setContentText("Esto es el contenido");

        alert.show();
    }
}