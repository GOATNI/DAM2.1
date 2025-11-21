package org.example.eje3;

import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.format.DateTimeFormatter;
import java.util.Date;

public class HelloController {
    @FXML
    private DatePicker datePicker;

    @FXML
   private TextField formattedDateTextField;
    private final DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    @FXML
    protected void initialize(){
        datePicker.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                formattedDateTextField.setText(newValue.format(formateador));
            }
        });
    }
}