package org.example.eje1;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;



public class HelloController {
    @FXML
    private ColorPicker Color_fondo;
    @FXML
    private ColorPicker Color_texto;
    @FXML
    private Button btn;
    @FXML
    private TextField tf;
    @FXML
    private Label lb;

    @FXML
    protected void CambiarColorFondo(){
        btn.setBackground(new Background(new BackgroundFill(Color_fondo.getValue(),null,null)));
        tf.setBackground(new Background(new BackgroundFill(Color_fondo.getValue(),null,null)));
        lb.setBackground(new Background(new BackgroundFill(Color_fondo.getValue(),null,null)));
    }
    @FXML
    protected void CambiarColorTexto(){

        btn.setTextFill(Color_texto.getValue());
        lb.setTextFill(Color_texto.getValue());
    }


}