package org.example.radiobutton;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

public class HelloController {
    @FXML
    private ToggleButton option1ToggleButton;
    @FXML
    private ToggleButton option2ToggleButton;
    @FXML
    private ToggleButton option3ToggleButton;

    @FXML
    private TextField selectedOptionTextField;

    private ToggleGroup toggleGroup;

    @FXML
    void initialize() {
        toggleGroup = new ToggleGroup();
        option1ToggleButton.setToggleGroup(toggleGroup);
        option2ToggleButton.setToggleGroup(toggleGroup);
        option3ToggleButton.setToggleGroup(toggleGroup);

        toggleGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle != null) {
                selectedOptionTextField.setText(((ToggleButton) newToggle).getText());
            } else {
                selectedOptionTextField.setText("");
            }
        });
    }
}