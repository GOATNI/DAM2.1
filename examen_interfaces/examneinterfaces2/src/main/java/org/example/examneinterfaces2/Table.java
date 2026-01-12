package org.example.examneinterfaces2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.PerspectiveCamera;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;



public class Table {

    private static final String url = "jdbc:mysql://localhost:3306/productos";
    private static final String user = "root";
    private static final String clave = "1234";

    private static final ResultSet rs;


    private static Statement stat;

    static {
        try {
            Connection con = DriverManager.getConnection(url, user, clave);
            Statement stat = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            rs = stat.executeQuery(
                    "select p.Producto,p.Precio,p.Estado,m.Marca,p.categoria from productos p\n" +
                            "join marcas m\n" +
                            "on p.Marca = m.id"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<String> Categoria = FXCollections.observableArrayList();

    @FXML
    TableView<producto> productoTableView;
    @FXML
    TableColumn<producto,String> nombretc;
    @FXML
    TableColumn<producto,String> categoriatc;
    @FXML
    TableColumn<producto,String> marcatc;
    @FXML
    TableColumn<producto,String> Estadotc;
    @FXML
    TableColumn<producto,Double> preciotc;
    @FXML
    public void initialize() throws SQLException {
        try (Connection con = DriverManager.getConnection(url, user, clave);
                Statement tempStat = con.createStatement();
             ResultSet rsCompanies = tempStat.executeQuery("select categoria from categorias")) {

            while (rsCompanies.next()) {
                Categoria.add(rsCompanies.getString("categoria"));
            }
        }


        ArrayList<producto> productos = new ArrayList<>();

        // 3. Fill list from database
        while (rs.next()) {
            String Estado;

            if (rs.getInt("Estado") == 1 ){
                Estado = "Recondicionado";
            }else
                Estado = "Nuevo";
            producto pro = new producto(
                    rs.getString("Producto"),
                    rs.getDouble("Precio"),
                   Categoria.get( rs.getInt("Categoria")-1),
                    rs.getString("Marca"),
                    Estado
            );
            productos.add(pro);
        }
        nombretc.setCellValueFactory(new PropertyValueFactory<>("producto"));
        preciotc.setCellValueFactory(new PropertyValueFactory<>("precio"));
        categoriatc.setCellValueFactory(new PropertyValueFactory<>("Categoria"));
        marcatc.setCellValueFactory(new PropertyValueFactory<>("Marca"));
        Estadotc.setCellValueFactory(new PropertyValueFactory<>("Estado"));

        // 4. Put data into TableView
        productoTableView.getItems().setAll(productos);
    }

}
