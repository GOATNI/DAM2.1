package org.example.tabla;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Ventana2 {
    @FXML
    private TableView<Empleado> tabla;
    @FXML
    private TableColumn<Empleado,String> Nombre;
    @FXML
    private TableColumn<Empleado,String> Apellido;
    @FXML
    private TableColumn<Empleado,String> Localidad;
    @FXML
    private TableColumn<Empleado,String> Salario;


    void abrirventana() throws IOException {

        FXMLLoader fxmlLoader1 = new FXMLLoader(Ventana2.class.getResource("ventana-2.fxml"));
        Scene scene1 = new Scene(fxmlLoader1.load(),320,240);
        Stage stage2 = new Stage();
        stage2.setTitle("Nueva ventana");
        stage2.setScene(scene1);
        stage2.show();
    }
    public void abrirVentana(ArrayList<Empleado> empleados){
        for (Empleado i : empleados){
            Nombre.setCellValueFactory(new PropertyValueFactory<>(i.getNombre()));
            Apellido.setCellValueFactory(new PropertyValueFactory<>(i.getApellido()));
            Localidad.setCellValueFactory(new PropertyValueFactory<>(i.getLocalidad()));
            Salario.setCellValueFactory(new PropertyValueFactory<>(i.getSalario() ));
        }
    }
}
