package org.example.eje1;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;



public class HelloController {
    @FXML
    private ColorPicker colorFondo;
    @FXML
    private ColorPicker colorTexto;
    @FXML
    private Button btn;
    @FXML
    private TextField tf;
    @FXML
    private Label lb;

    @FXML
    protected void cambiarColorFondo(){
        btn.setBackground(new Background(new BackgroundFill(colorFondo.getValue(),null,null)));
        tf.setBackground(new Background(new BackgroundFill(colorFondo.getValue(),null,null)));
        lb.setBackground(new Background(new BackgroundFill(colorFondo.getValue(),null,null)));
    }
    @FXML
    protected void cambiarColorTexto(){

        btn.setTextFill(colorTexto.getValue());
        lb.setTextFill(colorTexto.getValue());
    }


}