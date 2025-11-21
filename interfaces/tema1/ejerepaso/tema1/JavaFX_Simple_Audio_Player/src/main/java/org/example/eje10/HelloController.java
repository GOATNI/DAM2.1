package org.example.eje10;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class HelloController {
    @FXML
    private Button playButton;
    @FXML
    private Button stopButton;
    @FXML
    private Button pauseButton;
    @FXML
    private Menu musicMenu;
    @FXML
    private MenuItem song1MenuItem;
    @FXML
    private MenuItem song2MenuItem;
    @FXML
    private MenuItem song3MenuItem;

    private MediaPlayer mediaPlayer;

    @FXML
    void initialize(){
        // Initialize with the first song
        setMediaPlayer("music1.mp3");

        song1MenuItem.setOnAction(actionEvent -> setMediaPlayer("music1.mp3"));
        song2MenuItem.setOnAction(actionEvent -> setMediaPlayer("music2.mp3"));
        song3MenuItem.setOnAction(actionEvent -> setMediaPlayer("music3.mp3"));

        playButton.setOnAction(e -> {
            if (mediaPlayer != null) {
                mediaPlayer.play();
            }
        });
        stopButton.setOnAction(e-> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
        });
        pauseButton.setOnAction(e -> {
            if (mediaPlayer != null) {
                mediaPlayer.pause();
            }
        });
    }

    private void setMediaPlayer(String filename) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose(); // Release resources
        }
        try {
            String audiofile = new File(filename).toURI().toString();
            Media media = new Media(audiofile);
            mediaPlayer = new MediaPlayer(media);
        } catch (Exception e) {
            System.err.println("Error loading media file: " + filename + " - " + e.getMessage());
            // Optionally show an alert to the user
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error de Reproducción");
            alert.setHeaderText("No se pudo cargar el archivo de música.");
            alert.setContentText("Asegúrate de que el archivo '" + filename + "' existe y es accesible.");
            alert.show();
        }
    }
}