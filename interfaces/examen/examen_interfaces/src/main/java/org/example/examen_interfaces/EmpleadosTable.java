package org.example.examen_interfaces;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;

public class EmpleadosTable {
    private static final String url = "jdbc:mysql://localhost:3306/empresa";
    private static final String user = "root";
    private static final String clave = "1234";
    private  static ResultSet rs = null;
    private static final Connection con;

    static {
        try {
            con = DriverManager.getConnection(url, user, clave);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static final Statement stat;

    static {
        try {
            stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private TableView<Empleado> piezasTableView;
    @FXML
    private TableColumn<Empleado,String> TNombre;
    @FXML
    private TableColumn<Empleado, Double> TSalario;
    @FXML
    private TableColumn<Empleado,Integer> TDepartamento;
    @FXML
    public void initialize(){

        String sql = "SELECT * from empleados";
        ArrayList<Empleado> piezas = new ArrayList<>();

        try {
            ResultSet rs = stat.executeQuery(sql);



            TNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            TSalario.setCellValueFactory(new PropertyValueFactory<>("Salario"));
            TDepartamento.setCellValueFactory(new PropertyValueFactory<>("Departamento"));
            ObservableList<Empleado> observableList = FXCollections.observableList(piezas);
            piezasTableView.setItems(observableList);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
