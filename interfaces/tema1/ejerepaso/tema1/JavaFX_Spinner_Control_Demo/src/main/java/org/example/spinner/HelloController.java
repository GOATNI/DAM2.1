package org.example.spinner;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class HelloController {
    @FXML
    private Spinner<Integer> numberSpinner;
    @FXML
    private Label spinnerValueLabel;

    @FXML
    protected void initialize(){
        SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,50,5);
        numberSpinner.setValueFactory(factory);

        // Display initial value
        spinnerValueLabel.setText("Valor: " + numberSpinner.getValue());

        // Add listener to update label when spinner value changes
        numberSpinner.valueProperty().addListener((obs, oldValue, newValue) -> {
            spinnerValueLabel.setText("Valor: " + newValue);
        });
    }
}