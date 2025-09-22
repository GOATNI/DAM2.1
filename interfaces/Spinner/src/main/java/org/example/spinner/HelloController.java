package org.example.spinner;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class HelloController {
    @FXML
    private Spinner<Integer> Spin;

    @FXML
    protected void initialize(){
        SpinnerValueFactory<Integer> factory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,100,50,5);
        Spin.setValueFactory(factory);
    }

}