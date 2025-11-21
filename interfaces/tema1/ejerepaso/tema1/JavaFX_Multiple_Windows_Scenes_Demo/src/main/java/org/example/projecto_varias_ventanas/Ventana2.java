package org.example.projecto_varias_ventanas;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Ventana2 {

    public void openWindow() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ventana2-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = new Stage();
        stage.setTitle("Ventana 2");
        stage.setScene(scene);
        stage.show();
    }
}
