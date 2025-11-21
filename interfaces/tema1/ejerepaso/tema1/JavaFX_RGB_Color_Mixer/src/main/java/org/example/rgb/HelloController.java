package org.example.rgb;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;


public class HelloController {
    @FXML
    private TextField rgbPreviewTextField;
    @FXML
    private ComboBox<Integer> redComboBox;
    @FXML
    private ComboBox<Integer> greenComboBox;
    @FXML
    private ComboBox<Integer> blueComboBox;

    @FXML
    protected void initialize(){
        for (int i = 0; i <= 255; i++) {
            redComboBox.getItems().add(i);
            greenComboBox.getItems().add(i);
            blueComboBox.getItems().add(i);
        }
        redComboBox.setValue(0);
        greenComboBox.setValue(0);
        blueComboBox.setValue(0);

        // Add listeners to update color when ComboBox values change
        redComboBox.setOnAction(event -> applyColor());
        greenComboBox.setOnAction(event -> applyColor());
        blueComboBox.setOnAction(event -> applyColor());
    }
    @FXML
    protected void applyColor(){
        int red = redComboBox.getValue() != null ? redComboBox.getValue() : 0;
        int green = greenComboBox.getValue() != null ? greenComboBox.getValue() : 0;
        int blue = blueComboBox.getValue() != null ? blueComboBox.getValue() : 0;

        rgbPreviewTextField.setBackground(new Background(new BackgroundFill(Color.rgb(red, green, blue),null,null)));
    }
}