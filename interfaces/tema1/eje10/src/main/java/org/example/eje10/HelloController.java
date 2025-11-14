package org.example.eje10;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import java.io.File;
import java.util.concurrent.atomic.AtomicReference;

public class HelloController {
    @FXML
    private Button play;
    @FXML
    private Button Stop;
    @FXML
    private Button Paus;
    @FXML
    private Menu menu;
    @FXML
    private MenuItem item1;
    @FXML
    private MenuItem item2;
    @FXML
    private MenuItem item3;
    @FXML
    void initialize(){
        AtomicReference<String> filepath = new AtomicReference<>("music1.mp3");
        String audiofile = new File(filepath.get()).toURI().toString();
        Media media = new Media(audiofile);
        final MediaPlayer[] mediaPlayer = {new MediaPlayer(media)};

        item1.setOnAction(actionEvent -> mediaPlayer[0] = new MediaPlayer(new Media(new File("music1.mp3").toURI().toString())));
        item2.setOnAction(actionEvent -> mediaPlayer[0] = new MediaPlayer(new Media(new File("music2.mp3").toURI().toString())));
        item3.setOnAction(actionEvent -> mediaPlayer[0] = new MediaPlayer(new Media(new File("music3.mp3").toURI().toString())));
        play.setOnAction(e -> mediaPlayer[0].play());
        Stop.setOnAction(e-> mediaPlayer[0].stop());
        Paus.setOnAction(e -> mediaPlayer[0].pause());
    }


}