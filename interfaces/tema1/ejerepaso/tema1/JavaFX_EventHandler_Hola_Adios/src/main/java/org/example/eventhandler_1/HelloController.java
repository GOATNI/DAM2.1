package org.example.eventhandler_1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label welcomeText;
    @FXML
    private Button greetButton;
    @FXML
    private Button farewellButton;

    @FXML
    void initialize(){
        greetButton.setOnAction(e -> welcomeText.setText("Hola"));
        farewellButton.setOnAction(e-> welcomeText.setText("Adios"));
    }
}