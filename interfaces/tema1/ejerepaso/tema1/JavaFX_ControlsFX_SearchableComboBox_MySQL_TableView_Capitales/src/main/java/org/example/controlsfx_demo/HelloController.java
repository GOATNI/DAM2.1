package org.example.controlsfx_demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.controlsfx.control.SearchableComboBox;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class HelloController {

    private static String DB_URL;
    private static String DB_USER;
    private static String DB_PASSWORD;

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/db.properties"));
            DB_URL = properties.getProperty("db.url");
            DB_USER = properties.getProperty("db.user");
            DB_PASSWORD = properties.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading database properties.");
        }
    }

    @FXML
    private TableView<Capitales> capitalesTableView;
    @FXML
    private TableColumn<Capitales, String> autonomiaColumn;
    @FXML
    private TableColumn<Capitales, String> provinciasColumn;
    @FXML
    private TableColumn<Capitales, Integer> poblacionColumn;
    @FXML
    private SearchableComboBox<String> provinciaSearchComboBox;

    @FXML
    void initialize() {
        autonomiaColumn.setCellValueFactory(new PropertyValueFactory<>("autonomia"));
        provinciasColumn.setCellValueFactory(new PropertyValueFactory<>("provincias"));
        poblacionColumn.setCellValueFactory(new PropertyValueFactory<>("poblacion"));

        try {
            loadCapitalesData();
            provinciaSearchComboBox.setItems(FXCollections.observableArrayList(
                    capitalesTableView.getItems().stream().map(Capitales::getProvincias).toList()
            ));
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error loading capitales data on initialization.");
        }
    }

    @FXML
    void filterByProvincia() {
        String provinciaSeleccionada = provinciaSearchComboBox.getValue();
        if (provinciaSeleccionada == null || provinciaSeleccionada.isEmpty()) {
            try {
                loadCapitalesData(); // Reload all data if no province is selected
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Error reloading all capitales data.");
            }
            return;
        }

        String sql = "SELECT Provincia, Autonomía, Población FROM capitales WHERE Provincia = ?";
        ObservableList<Capitales> filteredList = FXCollections.observableArrayList();

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, provinciaSeleccionada);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    Capitales c = new Capitales(
                            rs.getString("Provincia"),
                            rs.getString("Autonomía"),
                            rs.getInt("Población")
                    );
                    filteredList.add(c);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error filtering capitales data by province.");
        }
        capitalesTableView.setItems(filteredList);
    }

    private void loadCapitalesData() throws SQLException {
        String sql = "SELECT Provincia, Autonomía, Población FROM capitales";
        ObservableList<Capitales> data = FXCollections.observableArrayList();

        try (Connection con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             Statement stat = con.createStatement();
             ResultSet rs = stat.executeQuery(sql)) {

            while (rs.next()) {
                Capitales c = new Capitales(
                        rs.getString("Provincia"),
                        rs.getString("Autonomía"),
                        rs.getInt("Población")
                );
                data.add(c);
            }
        }
        capitalesTableView.setItems(data);
    }
}