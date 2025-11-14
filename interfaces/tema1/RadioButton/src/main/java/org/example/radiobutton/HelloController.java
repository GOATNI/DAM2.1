package org.example.radiobutton;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;

import java.util.Locale;

public class HelloController {
    @FXML
    private ToggleButton button1;
    @FXML
    private ToggleButton button2;
    @FXML
    private ToggleButton button3;

    @FXML
    private TextField slected;


    @FXML
    protected void isSelected() {
        if (button1.isSelected()) {
            slected.setText(button1.getText().toString());
        } else if (button2.isSelected()) {
            slected.setText(button2.getText().toString());
        }else
            slected.setText(button3.getText().toString());
    }
}