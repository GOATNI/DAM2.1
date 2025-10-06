package org.example.projecto_varias_ventanas;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class Ventana2 {
    @FXML
    private ImageView ventana;

    @FXML
    void abrir() throws IOException {
        FXMLLoader fxmlLoader = new
                FXMLLoader(HelloApplication.class.getResource("Ventana2.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        Stage stage2 = new Stage();
        stage2.setTitle("Nueva ventana");
        stage2.setScene(scene);
        stage2.show();
    }
}
