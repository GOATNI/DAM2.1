package org.example.eje3;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.format.DateTimeFormatter;
import java.util.Date;

public class HelloController {
    @FXML
    private DatePicker welcomeText;

    @FXML
   private TextField tf;
    DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @FXML
    protected void initialize(){

    }


    @FXML
    protected void text_vacio(){
        initialize();
        tf.setText(welcomeText.getValue().format(formateador));
    }

}