package org.example.enenuevo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;

public class HelloController {
    private static final String url = "jdbc:mysql://localhost:3306/data";
    private static final String user = "root";
    private static final String clave = "1234";

    private ObservableList<Empleado> empleadosList = FXCollections.observableArrayList();

    @FXML
    private TextField Nombre;
    @FXML
    private TextField Apellidos;
    @FXML
    private TextField Localidad;
    @FXML
    private TextField Salario;
    @FXML
    private Button right;
    @FXML
    private Button left;
    @FXML
    private Button last;
    @FXML
    private Button first;
    @FXML
    private Button guardar;
    @FXML
    private Button NuevoRegistro;
    @FXML
    private TableView<Empleado> table;
    @FXML
    private TableColumn<Empleado, String> tname;
    @FXML
    private TableColumn<Empleado, String> tapellido;
    @FXML
    private TableColumn<Empleado, String> tLocalidad;
    @FXML
    private TableColumn<Empleado, String> tSalario;
    @FXML
    private Button añade;

    @FXML
    void initialize() {
        tname.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tapellido.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        tLocalidad.setCellValueFactory(new PropertyValueFactory<>("localidad"));
        tSalario.setCellValueFactory(new PropertyValueFactory<>("salario"));

        loadEmpleadosFromDatabase();

        guardar.setDisable(true);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, clave);
    }

    private void loadEmpleadosFromDatabase() {
        empleadosList.clear();
        String sql = "SELECT * FROM empleados";
        try (Connection con = getConnection();
             Statement stat = con.createStatement();
             ResultSet rs = stat.executeQuery(sql)) {

            while (rs.next()) {
                empleadosList.add(new Empleado(
                        rs.getString("Nombre"),
                        rs.getString("Apellidos"),
                        rs.getString("Localidad"),
                        rs.getString("Salario")
                ));
            }
            table.setItems(empleadosList);
        } catch (SQLException e) {
            showAlert("Error de Base de Datos", "No se pudieron cargar los datos de la base de datos.");
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    void añadee() {
        String nombre = Nombre.getText();
        String apellidos = Apellidos.getText();
        String localidad = Localidad.getText();
        String salario = Salario.getText();

        if (nombre.isEmpty() || apellidos.isEmpty() || localidad.isEmpty() || salario.isEmpty()) {
            showAlert("Error de Datos", "Todos los campos deben estar llenos para añadir un empleado.");
            return;
        }

        Empleado nuevoEmpleado = new Empleado(nombre, apellidos, localidad, salario);
        empleadosList.add(nuevoEmpleado);
        table.getSelectionModel().select(nuevoEmpleado);

        Nombre.clear();
        Apellidos.clear();
        Localidad.clear();
        Salario.clear();
    }

    @FXML
    void resgister() {
        Nombre.clear();
        Apellidos.clear();
        Localidad.clear();
        Salario.clear();
        guardar.setDisable(false);
    }

    @FXML
    void save() {
        String nombre = Nombre.getText();
        String apellidos = Apellidos.getText();
        String localidad = Localidad.getText();
        String salario = Salario.getText();

        if (nombre.isEmpty() || apellidos.isEmpty() || localidad.isEmpty() || salario.isEmpty()) {
            showAlert("Error de Datos", "Todos los campos deben estar llenos para guardar un empleado.");
            return;
        }

        String sql = "INSERT INTO empleados (Nombre, Apellidos, Localidad, Salario) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setString(3, localidad);
            ps.setString(4, salario);
            ps.executeUpdate();
            loadEmpleadosFromDatabase(); // Recarga la tabla para mostrar el nuevo registro
            guardar.setDisable(true);
        } catch (SQLException e) {
            showAlert("Error de Base de Datos", "No se pudo insertar el nuevo registro.");
            e.printStackTrace();
        }
    }


}

