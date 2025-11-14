package org.example.calculadora;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField num1;
    @FXML
    private TextField num2;
    @FXML
    private TextField resultado;


    @FXML
    private void sumar(){
        resultado.setText(String.valueOf(Integer.valueOf(Integer.parseInt(num1.getText())+Integer.parseInt(num2.getText()))));
    }
    @FXML
    private void restar(){
        resultado.setText(String.valueOf(Integer.valueOf(Integer.parseInt(num1.getText())-Integer.parseInt(num2.getText()))));
    }
    @FXML
    private void multiplicar(){
        resultado.setText(String.valueOf(Integer.valueOf(Integer.parseInt(num1.getText())*Integer.parseInt(num2.getText()))));
    }


}