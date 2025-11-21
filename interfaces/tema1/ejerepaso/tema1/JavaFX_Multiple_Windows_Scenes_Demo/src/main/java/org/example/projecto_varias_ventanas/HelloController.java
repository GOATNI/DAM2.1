package org.example.projecto_varias_ventanas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField dniTextField;
    @FXML
    private TextField dateTextField;
    @FXML
    private TextArea queryTextArea;
    @FXML
    private Button openVentana2Button;
    @FXML
    private Button sendButton;

    @FXML
    private CheckBox termsCheckBox;

    @FXML
    protected void openVentana2() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ventana2-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = new Stage();
            stage.setTitle("Ventana 2");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "No se pudo abrir la Ventana 2.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}