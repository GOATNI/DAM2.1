package org.example.saludo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField greetingTextField;
    @FXML
    private Button greetButton;
    @FXML
    private TextField nameTextField;

    @FXML
    private void greetUser(){
        greetingTextField.setText("Hola " + nameTextField.getText());
    }
}