package org.example.eje3;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private ComboBox<String> provinciasComboBox;
    @FXML
    private ComboBox<String> institutoComboBox;

    @FXML
    protected void initialize(){
        provinciasComboBox.getItems().addAll("Zaragoza","Teruel","Huesca");
        provinciasComboBox.setOnAction(event -> populateInstitutos());
    }
    @FXML
    protected  void populateInstitutos(){
        institutoComboBox.getItems().clear(); // Clear existing items
        if (provinciasComboBox.getValue() == null) {
            return;
        }
        String selectedProvincia = provinciasComboBox.getValue();
        if ("Teruel".equals(selectedProvincia)){
            institutoComboBox.getItems().addAll("Segundo de Chomon","Ies vega del turia ");
        }else if ("Zaragoza".equals(selectedProvincia)){
            institutoComboBox.getItems().addAll("Miguel Catalán","Acosur");

        }else if ("Huesca".equals(selectedProvincia)) {
            institutoComboBox.getItems().addAll("Pirámide","Salesas");
        }
    }
}