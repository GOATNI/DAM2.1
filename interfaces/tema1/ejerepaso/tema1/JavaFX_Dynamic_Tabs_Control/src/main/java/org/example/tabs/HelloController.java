package org.example.tabs;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Tab personTab;
    @FXML
    private Tab carTab;
    @FXML
    private Tab minivanTab;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private CheckBox enableCarCheckBox;
    @FXML
    private CheckBox enableMinivanCheckBox;

    @FXML
    void initialize(){
        carTab.setDisable(true);
        minivanTab.setDisable(true);

        enableCarCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            carTab.setDisable(!newValue);
        });

        enableMinivanCheckBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
            minivanTab.setDisable(!newValue);
        });
    }
}