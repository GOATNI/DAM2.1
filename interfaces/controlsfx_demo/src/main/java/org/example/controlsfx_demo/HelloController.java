package org.example.controlsfx_demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.SearchableComboBox;

import java.sql.*;
import java.util.ArrayList;

public class HelloController {

    ArrayList<Capitales> capitales = new ArrayList<>();

    private static final String url = "jdbc:mysql://localhost:3306/capitales";
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
    private static final Statement stat;

    static {
        try {
            stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private TableView<Capitales> capitalesTableView;
    @FXML
    private TableColumn<Capitales,String> TAutonomia;
    @FXML
    private TableColumn<Capitales,String> TProvincias;
    @FXML
    private TableColumn<Capitales,Integer> TPoblacion;
    @FXML
    private SearchableComboBox<String> welcomeText;

    @FXML
    void initialize() throws SQLException {


            String sql = "SELECT Provincia, Autonomía, Población FROM capitales";
            ResultSet rs = stat.executeQuery(sql);

            ArrayList<Capitales> lista = new ArrayList<>();

            while (rs.next()) {
                Capitales c = new Capitales(
                        rs.getString("Provincia"),
                        rs.getString("Autonomía"),
                        rs.getInt("Población")
                );
                lista.add(c);
            }


            TProvincias.setCellValueFactory(new PropertyValueFactory<>("provincias"));
            TAutonomia.setCellValueFactory(new PropertyValueFactory<>("autonomia"));
            TPoblacion.setCellValueFactory(new PropertyValueFactory<>("poblacion"));


            ObservableList<Capitales> observableList = FXCollections.observableArrayList(lista);
            capitalesTableView.setItems(observableList);


            welcomeText.setItems(FXCollections.observableArrayList(
                    lista.stream().map(capitales1 -> capitales1.getProvincias()).toList()
            ));


    }
    @FXML
    void elegirsitio() throws SQLException {
        String provinciaSeleccionada = welcomeText.getValue();
        if (provinciaSeleccionada == null || provinciaSeleccionada.isEmpty()) {
            return;
        }

        String sql = "SELECT * FROM capitales WHERE Provincia = ?";
        ArrayList<Capitales> lista = new ArrayList<>();


        PreparedStatement pst = con.prepareStatement(sql);

        pst.setString(1, provinciaSeleccionada);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            Capitales c = new Capitales(
                    rs.getString("Provincia"),
                    rs.getString("Autonomía"),
                    rs.getInt("Población")
            );
            lista.add(c);
        }


        TProvincias.setCellValueFactory(new PropertyValueFactory<>("provincias"));
        TAutonomia.setCellValueFactory(new PropertyValueFactory<>("autonomia"));
        TPoblacion.setCellValueFactory(new PropertyValueFactory<>("poblacion"));


        ObservableList<Capitales> observableList = FXCollections.observableArrayList(lista);
        capitalesTableView.setItems(observableList);



    }

}