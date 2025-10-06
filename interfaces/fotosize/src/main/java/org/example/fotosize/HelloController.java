package org.example.fotosize;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public class HelloController {
    @FXML
    private ImageView welcomeText;
    @FXML
    private Slider sliderimage;

    @FXML
    void initialize(){

        sliderimage.setMax(welcomeText.getFitHeight());
        welcomeText.setFitHeight(50);

    }
    @FXML
    void cambiarTamaño(){
        welcomeText.setFitHeight(sliderimage.getValue());
    }
}