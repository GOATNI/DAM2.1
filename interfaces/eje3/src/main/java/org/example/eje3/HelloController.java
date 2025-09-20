package org.example.eje3;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private ComboBox provincias;
    @FXML
    private ComboBox instituto;
    @FXML
    private TextField telling;

    @FXML
    protected void initialize(){
        provincias.getItems().addAll("Zaragoza","Teruel","Huesca");

    }
    @FXML
    protected  void instituto1(){
        if ("Teruel".equals(provincias.getValue().toString())){
            instituto.getItems().addAll("Segundo de Chomon","Ies vega del turia ");
        }else if ("Zaragoza".equals(provincias.getValue().toString())){
            instituto.getItems().addAll("Miguel Catalán","Acosur");

        }else
            instituto.getItems().addAll("Pirámide","Salesas");
    }
}