package org.example.tabla;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    private Button filterButton;
    @FXML
    private TextField localityTextField;

    @FXML
    protected void onFilterButtonClick() {
        try {
            ArrayList<Empleado> filteredEmployees = filterEmployeesByLocality();
            Ventana2 ventana2 = new Ventana2();
            ventana2.openWindow(filteredEmployees);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            showAlert("Error", "No se pudo abrir la ventana de empleados filtrados.");
        }
    }

    private ArrayList<Empleado> filterEmployeesByLocality() throws SQLException {
        ArrayList<Empleado> filteredEmployees = new ArrayList<>();
        String locality = localityTextField.getText();
        if (locality == null || locality.trim().isEmpty()) {
            return getAllEmployeesFromDatabase();
        }

        String sql = "SELECT * FROM empleados WHERE Localidad = ?";
        try (Connection con = getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, locality);
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    filteredEmployees.add(new Empleado(
                            rs.getString("Nombre"),
                            rs.getString("Apellidos"),
                            rs.getString("Localidad"),
                            rs.getDouble("Salario")
                    ));
                }
            }
        }
        return filteredEmployees;
    }

    private ArrayList<Empleado> getAllEmployeesFromDatabase() throws SQLException {
        ArrayList<Empleado> allEmployees = new ArrayList<>();
        String sql = "SELECT * FROM empleados";
        try (Connection con = getConnection();
             Statement stat = con.createStatement();
             ResultSet rs = stat.executeQuery(sql)) {
            while (rs.next()) {
                allEmployees.add(new Empleado(
                        rs.getString("Nombre"),
                        rs.getString("Apellidos"),
                        rs.getString("Localidad"),
                        rs.getDouble("Salario")
                ));
            }
        }
        return allEmployees;
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}