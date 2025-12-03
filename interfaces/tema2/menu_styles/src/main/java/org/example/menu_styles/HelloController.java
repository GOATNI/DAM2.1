package org.example.menu_styles;

import javafx.fxml.FXML;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class HelloController {

    @FXML private MenuBar menuBar;
    @FXML private MenuItem estlo1;
    @FXML private MenuItem estlo2;
    @FXML private MenuItem noestlo;


    @FXML
    private void initialize() {

    }

    @FXML
    private void aplicarEstilo1() {
        cambiarEstilo("estilos.css");
    }

    @FXML
    private void aplicarEstilo2() {
        cambiarEstilo("estilo2.css");
    }

    @FXML
    private void quitarEstilo() {
        cambiarEstilo("no_style.css");
    }

    private void cambiarEstilo(String cssFile) {
        menuBar.getScene().getStylesheets().clear();
        menuBar.getScene().getStylesheets().add(
                getClass().getResource(cssFile).toExternalForm()
        );
    }
}
