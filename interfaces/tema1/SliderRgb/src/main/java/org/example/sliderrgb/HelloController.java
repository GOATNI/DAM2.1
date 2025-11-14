package org.example.sliderrgb;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class HelloController {
    @FXML
    private TextField welcomeText;
    @FXML
    private Slider Rojo;
    @FXML
    private Slider Azul;
    @FXML
    private Slider Verde;

    @FXML
    void initialize(){
        Rojo.setMax(255);
        Azul.setMax(255);
        Verde.setMax(255);
    }
    @FXML
    void colorear(){
        int red = (int)Rojo.getValue();
        welcomeText.setBackground(new Background(new BackgroundFill(Color.rgb((int)Rojo.getValue(),(int)Azul.getValue(),(int)Verde.getValue()), null, null)));
    }
}