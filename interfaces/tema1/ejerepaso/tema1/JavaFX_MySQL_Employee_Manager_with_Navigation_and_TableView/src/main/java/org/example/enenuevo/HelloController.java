package org.example.enenuevo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
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

    private ObservableList<Empleado> empleadosList = FXCollections.observableArrayList();
    private int currentIndex = -1;

    @FXML
    private TextField nombreTextField;
    @FXML
    private TextField apellidosTextField;
    @FXML
    private TextField localidadTextField;
    @FXML
    private TextField salarioTextField;
    @FXML
    private Button nextButton;
    @FXML
    private Button previousButton;
    @FXML
    private Button lastButton;
    @FXML
    private Button firstButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button newRecordButton;
    @FXML
    private TableView<Empleado> table;
    @FXML
    private TableColumn<Empleado, String> nombreColumn;
    @FXML
    private TableColumn<Empleado, String> apellidoColumn;
    @FXML
    private TableColumn<Empleado, String> localidadColumn;
    @FXML
    private TableColumn<Empleado, Double> salarioColumn;
    @FXML
    private Button addButton;

    @FXML
    void initialize() {
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        apellidoColumn.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        localidadColumn.setCellValueFactory(new PropertyValueFactory<>("localidad"));
        salarioColumn.setCellValueFactory(new PropertyValueFactory<>("salario"));

        loadEmpleadosFromDatabase();
        updateNavigationButtons();
        showEmpleado(0); // Show the first employee

        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                currentIndex = empleadosList.indexOf(newSelection);
                displayEmpleado(newSelection);
                updateNavigationButtons();
            }
        });

        saveButton.setDisable(true);
    }

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
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
                        rs.getDouble("Salario")
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
    void addEmployee() {
        String nombre = nombreTextField.getText();
        String apellidos = apellidosTextField.getText();
        String localidad = localidadTextField.getText();
        String salarioStr = salarioTextField.getText();

        if (nombre.isEmpty() || apellidos.isEmpty() || localidad.isEmpty() || salarioStr.isEmpty()) {
            showAlert("Error de Datos", "Todos los campos deben estar llenos para añadir un empleado.");
            return;
        }

        double salario;
        try {
            salario = Double.parseDouble(salarioStr);
        } catch (NumberFormatException e) {
            showAlert("Error de Formato", "El salario debe ser un número válido.");
            return;
        }

        Empleado nuevoEmpleado = new Empleado(nombre, apellidos, localidad, salario);
        empleadosList.add(nuevoEmpleado);
        table.getSelectionModel().select(nuevoEmpleado);

        clearFields();
    }

    @FXML
    void newEmployeeRecord() {
        clearFields();
        saveButton.setDisable(false);
    }

    @FXML
    void saveEmployee() {
        String nombre = nombreTextField.getText();
        String apellidos = apellidosTextField.getText();
        String localidad = localidadTextField.getText();
        String salarioStr = salarioTextField.getText();

        if (nombre.isEmpty() || apellidos.isEmpty() || localidad.isEmpty() || salarioStr.isEmpty()) {
            showAlert("Error de Datos", "Todos los campos deben estar llenos para guardar un empleado.");
            return;
        }

        double salario;
        try {
            salario = Double.parseDouble(salarioStr);
        } catch (NumberFormatException e) {
            showAlert("Error de Formato", "El salario debe ser un número válido.");
            return;
        }

        String sql = "INSERT INTO empleados (Nombre, Apellidos, Localidad, Salario) VALUES (?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setString(3, localidad);
            ps.setDouble(4, salario);
            ps.executeUpdate();
            loadEmpleadosFromDatabase(); // Recarga la tabla para mostrar el nuevo registro
            saveButton.setDisable(true);
            showAlert("Éxito", "Empleado guardado correctamente.");
        } catch (SQLException e) {
            showAlert("Error de Base de Datos", "No se pudo insertar el nuevo registro.");
            e.printStackTrace();
        }
    }

    private void clearFields() {
        nombreTextField.clear();
        apellidosTextField.clear();
        localidadTextField.clear();
        salarioTextField.clear();
    }

    private void displayEmpleado(Empleado empleado) {
        if (empleado != null) {
            nombreTextField.setText(empleado.getNombre());
            apellidosTextField.setText(empleado.getApellidos());
            localidadTextField.setText(empleado.getLocalidad());
            salarioTextField.setText(String.valueOf(empleado.getSalario()));
        } else {
            clearFields();
        }
    }

    private void showEmpleado(int index) {
        if (!empleadosList.isEmpty() && index >= 0 && index < empleadosList.size()) {
            currentIndex = index;
            displayEmpleado(empleadosList.get(currentIndex));
            table.getSelectionModel().select(currentIndex);
        }
        updateNavigationButtons();
    }

    @FXML
    private void onFirstButtonClick() {
        showEmpleado(0);
    }

    @FXML
    private void onPreviousButtonClick() {
        if (currentIndex > 0) {
            showEmpleado(currentIndex - 1);
        }
    }

    @FXML
    private void onNextButtonClick() {
        if (currentIndex < empleadosList.size() - 1) {
            showEmpleado(currentIndex + 1);
        }
    }

    @FXML
    private void onLastButtonClick() {
        showEmpleado(empleadosList.size() - 1);
    }

    private void updateNavigationButtons() {
        firstButton.setDisable(currentIndex <= 0);
        previousButton.setDisable(currentIndex <= 0);
        nextButton.setDisable(currentIndex >= empleadosList.size() - 1);
        lastButton.setDisable(currentIndex >= empleadosList.size() - 1);
    }
}

