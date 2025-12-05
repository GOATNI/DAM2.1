package org.example.imccalculo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HelloController {
    @FXML
    private TextField txtPeso;

    @FXML
    private TextField txtTalla;

    @FXML
    private Label lblResultado;

    @FXML
    private Label lblEstado;

    @FXML
    private Button btnCalcular;

    @FXML
    private Button btnCancelar;
    @FXML
    private Button btnayudar;

    @FXML
    public void initialize() {
        // Opcional: se ejecuta al cargar la ventana
    }

    @FXML
    private void calcularIMC() {
        try {
            double peso = Double.parseDouble(txtPeso.getText());
            double tallaCm = Double.parseDouble(txtTalla.getText());

            // Convertir cm a metros
            double tallaM = tallaCm / 100;

            // Fórmula IMC
            double imc = peso / (tallaM * tallaM);

            lblResultado.setText(String.format("%.2f", imc));
            getEstado(imc);

        } catch (Exception e) {
            lblResultado.setText("Error");
            lblEstado.setText("Datos inválidos");
        }
    }

    @FXML
    private void limpiarCampos() {
        txtPeso.clear();
        txtTalla.clear();
        lblResultado.setText("");
        lblEstado.setText("");
    }

    private void getEstado(double imc) {
        String text;
        String color;
        if (imc < 18.5){
            text= "Bajo Peso";
            color = "orange";
        } else if (imc < 25){
           text= "Peso Normal";
           color="green";
        } else if (imc < 30){
            text ="Sobrepeso";
            color = "pink";
        } else if (imc < 35) {
            text = "Obesidad I";
            color= "#e14444";
        } else if (imc < 40){
            text= "Obesidad II";
            color="#ff0000";
        } else {
            text = "Obesidad III";
            color = "#320101";
        }
        lblEstado.setText(text);
        lblEstado.setTextFill(Paint.valueOf(color));

    }

    @FXML
    private void ayudar() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("ayudaIMC.chm"));
    }
}