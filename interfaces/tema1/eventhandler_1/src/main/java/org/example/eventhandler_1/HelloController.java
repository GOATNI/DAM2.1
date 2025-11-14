package org.example.eventhandler_1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button Saludar;
    @FXML
    private Button Despedir;

    @FXML
    void initialize(){
        Saludar.setOnAction(e -> welcomeText.setText("Hola"));
        Despedir.setOnAction(e-> welcomeText.setText("Adios"));
    }
}