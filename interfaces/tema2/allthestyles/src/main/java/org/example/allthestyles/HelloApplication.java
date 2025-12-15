package org.example.allthestyles;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Hello!");
        stage.getIcons().add(new Image("icon.jpg"));
        stage.setScene(scene);

        /// more ico0ns IconFilename: "{app}\imc.ico"
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}