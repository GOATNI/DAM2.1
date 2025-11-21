package org.example.sliderrgb;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;

public class HelloController {
    @FXML
    private TextField rgbPreviewTextField;
    @FXML
    private Slider redSlider;
    @FXML
    private Slider greenSlider;
    @FXML
    private Slider blueSlider;

    @FXML
    void initialize(){
        redSlider.setMax(255);
        greenSlider.setMax(255);
        blueSlider.setMax(255);

        // Add listeners to update color when slider values change
        redSlider.valueProperty().addListener((obs, oldVal, newVal) -> applyColor());
        greenSlider.valueProperty().addListener((obs, oldVal, newVal) -> applyColor());
        blueSlider.valueProperty().addListener((obs, oldVal, newVal) -> applyColor());

        applyColor(); // Set initial color
    }
    @FXML
    void applyColor(){
        int red = (int)redSlider.getValue();
        int green = (int)greenSlider.getValue();
        int blue = (int)blueSlider.getValue();
        rgbPreviewTextField.setBackground(new Background(new BackgroundFill(Color.rgb(red, green, blue), null, null)));
    }
}