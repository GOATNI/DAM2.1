package org.example.saludo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField Show;
    @FXML
    private Button Saludar;
    @FXML
    private TextField textfielNombre;



    @FXML
    private void Saludar(){
        Show.setText("hola "+(textfielNombre.getText()));
    }


}