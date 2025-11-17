package org.example.ejercicio_repaso_examen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;



public class Tableviews {
    private static final String url = "jdbc:mysql://localhost:3306/piezas";
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
    private TableView<Piezas> piezasTableView;
    @FXML
    private TableColumn<Piezas,String> TNombre;
    @FXML
    private TableColumn<Piezas, Float> TPrecio;
    @FXML
    private TableColumn<Piezas,Integer> Tid;

    @FXML
    public void initialize(){

        String sql = "SELECT * from piezas";
        ArrayList<Piezas> piezas = new ArrayList<>();

        try {
            ResultSet rs = stat.executeQuery(sql);

            while (rs.next()){
                Piezas piezas1 = new Piezas(
                        rs.getInt("Id"),
                        rs.getFloat("Precio"),
                        rs.getString("Nombre")
                );
                piezas.add(piezas1);
            }

            Tid.setCellValueFactory(new PropertyValueFactory<>("Id"));
            TNombre.setCellValueFactory(new PropertyValueFactory<>("Nombre"));
            TPrecio.setCellValueFactory(new PropertyValueFactory<>("Precio"));
            ObservableList<Piezas> observableList = FXCollections.observableList(piezas);
            piezasTableView.setItems(observableList);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
