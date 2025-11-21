package org.example.fotosize;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;

public class HelloController {
    @FXML
    private ImageView imageView;
    @FXML
    private Slider sizeSlider;

    @FXML
    void initialize(){
        // Set initial values for the slider and image view
        sizeSlider.setMin(10); // Minimum size
        sizeSlider.setMax(200); // Maximum size, adjust as needed
        sizeSlider.setValue(50); // Initial size
        imageView.setFitHeight(sizeSlider.getValue());
        imageView.setFitWidth(sizeSlider.getValue()); // Keep aspect ratio

        // Add a listener to the slider's value property
        sizeSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            changeImageSize(newVal.doubleValue());
        });
    }
    @FXML
    void changeImageSize(double size){
        imageView.setFitHeight(size);
        imageView.setFitWidth(size); // Keep aspect ratio
    }
}