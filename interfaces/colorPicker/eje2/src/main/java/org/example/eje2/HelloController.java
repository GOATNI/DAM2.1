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
    private Spinner spinner;
    @FXML
    private ColorPicker Color_fondo;
    @FXML
    private ColorPicker Color_Texto;

    @FXML
    void initialize(){
        SpinnerValueFactory<Integer> factory = new
                SpinnerValueFactory.IntegerSpinnerValueFactory(0, 18, 12, 2);
        spinner.setValueFactory(factory);
    }
    @FXML
    protected  void change_fondo(){
        texto.setBackground(new Background(new BackgroundFill(Color_fondo.getValue(),null,null)));
    }
    @FXML
    protected void change_color_texto(){
        texto.setTextFill(Color_Texto.getValue());
    }
    @FXML
    protected void change_size(){
        String tamaño = spinner.getValue().toString();
        double tamaño1 = Double.parseDouble(tamaño);

        texto.setFont(Font.font((int) tamaño1));
    }

}