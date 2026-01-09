package com.helping.biblioteca;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class GenerosController {

    @FXML
    private TableView<Genero> generosTable;
    @FXML
    private TableColumn<Genero, Integer> idColumn;
    @FXML
    private TableColumn<Genero, String> nombreColumn;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        loadGeneros();
    }

    private void loadGeneros() {
        ObservableList<Genero> generos = FXCollections.observableArrayList();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM genero")) {

            while (rs.next()) {
                generos.add(new Genero(rs.getInt("Id"), rs.getString("Nombre")));
            }
            generosTable.setItems(generos);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Database Error");
            alert.setContentText("Could not load genres: " + e.getMessage());
            alert.showAndWait();
        }
    }
}
