package org.example.tabs;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Tab persona;
    @FXML
    private Tab coche;
    @FXML
    private Tab monovolumen;
    @FXML
    private TextField nombre;
    @FXML
    private TextField apellidos;
    @FXML
     private CheckBox habilitarcoche;
    @FXML
    private CheckBox hablilirmonovolumen;

    @FXML
    void initialize(){
        coche.setDisable(true);
        monovolumen.setDisable(true);
    }
    @FXML
    protected void hablitarco(){
        if (habilitarcoche.isSelected()){
            coche.setDisable(false);
        }else
            coche.setDisable(true);




    }
        @FXML
    protected  void habilitarmo(){
        if (hablilirmonovolumen.isSelected()){
            monovolumen.setDisable(false);
        }else
            monovolumen.setDisable(true);
        }

}