package org.example.rgb;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.paint.Color;


public class HelloController {
    @FXML
    private TextField welcomeText;
    @FXML
     private ComboBox Rojo;
    @FXML
    private ComboBox Verde;
    @FXML
    private ComboBox Azul;

    @FXML
     protected void initialize(){
        Rojo.setValue(0);
        Verde.setValue(0);
        Azul.setValue(0);

        for (int i = 0; i <= 255; i++) {
            Rojo.getItems().add(i);
            Verde.getItems().add(i);
            Azul.getItems().add(i);
        }
    }
    @FXML
    protected void colorear(){
        welcomeText.setBackground(new Background(new BackgroundFill(Color.rgb((int)Rojo.getValue(), (int)Verde.getValue(), (int)Azul.getValue()),null,null)));

    }


}