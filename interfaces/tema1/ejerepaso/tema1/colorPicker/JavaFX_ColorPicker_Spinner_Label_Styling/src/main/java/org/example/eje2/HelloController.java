package org.example.eje2;

import javafx.fxml.FXML;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.text.Font;

public class HelloController {
    @FXML
    private Label texto;
    @FXML
    private Spinner<Integer> spinner;
    @FXML
    private ColorPicker colorFondo;
    @FXML
    private ColorPicker colorTexto;

    @FXML
    void initialize(){
        SpinnerValueFactory<Integer> factory = new
                SpinnerValueFactory.IntegerSpinnerValueFactory(0, 18, 12, 2);
        spinner.setValueFactory(factory);
    }
    @FXML
    protected  void changeFondo(){
        texto.setBackground(new Background(new BackgroundFill(colorFondo.getValue(),null,null)));
    }
    @FXML
    protected void changeColorTexto(){
        texto.setTextFill(colorTexto.getValue());
    }
    @FXML
    protected void changeSize(){
        texto.setFont(Font.font(spinner.getValue()));
    }

}