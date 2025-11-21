package org.example.tabla;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Ventana2 {

    public void openWindow(ArrayList<Empleado> employees) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ventana2-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Ventana2Controller controller = fxmlLoader.getController();
        controller.setEmployees(employees);
        Stage stage = new Stage();
        stage.setTitle("Empleados Filtrados");
        stage.setScene(scene);
        stage.show();
    }
}
