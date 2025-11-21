package org.example.tabla;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class Ventana2Controller {
    @FXML
    private TableView<Empleado> employeesTableView;
    @FXML
    private TableColumn<Empleado, String> nameColumn;
    @FXML
    private TableColumn<Empleado, String> surnameColumn;
    @FXML
    private TableColumn<Empleado, String> localityColumn;
    @FXML
    private TableColumn<Empleado, Double> salaryColumn;

    public void setEmployees(ArrayList<Empleado> employees) {
        ObservableList<Empleado> employeeObservableList = FXCollections.observableArrayList(employees);
        employeesTableView.setItems(employeeObservableList);
    }

    @FXML
    void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("apellido"));
        localityColumn.setCellValueFactory(new PropertyValueFactory<>("localidad"));
        salaryColumn.setCellValueFactory(new PropertyValueFactory<>("salario"));
    }
}
