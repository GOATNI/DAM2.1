package org.example.examen_interfaces;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Properties;


public class HelloController {
    private static final String url = "jdbc:mysql://localhost:3306/empresa";
    private static final String user = "root";
    private static final String clave = "1234";

    private ObservableList<Empleado> empleadosList = FXCollections.observableArrayList();
    private ObservableList<Departamento> departamentoObservableList = FXCollections.observableArrayList();


    @FXML
    private DatePicker fechanacimientodp;
    @FXML
    private RadioButton Femeninocb;
    @FXML
    private RadioButton Masculinocb;
    @FXML
    private TextField idtf;
    @FXML
    private TextField nombretf;
    @FXML
    private ComboBox Departamentocb;
    @FXML
    private TextField Salariotf;
    @FXML
    private Menu menuGestión;
    @FXML
    private Menu Departamentomenu;
    @FXML
    private Menu Listadosmenu;
    @FXML
    private MenuItem PrimerRegistro;
    @FXML
    private MenuItem RegistroAnterior;
    @FXML
    private MenuItem RegistroSiguiente;
    @FXML
    private MenuItem UltimoRegistro;
    @FXML
    private MenuItem NuevoEmpleado;
    @FXML
    private MenuItem GuardarEmpleado;
    @FXML
    private MenuItem EmpladosTable;


    @FXML
    public void listados() throws IOException {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("EmpleadosTable.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            Stage stage = new Stage();
            stage.setTitle("Hello!");
            stage.setScene(scene);
            stage.show();

    }
    @FXML
    void initialize() {
        loadEmpleadosFromDatabase();
        Empleado empleado = empleadosList.get(0);
        idtf.setText(String.valueOf(empleado.id));
        nombretf.setText(String.valueOf(empleado.Nombre));
        Salariotf.setText(String.valueOf(empleado.Salario));

    }

    private Object getdepartamento(Empleado empleado) {
        int iddepart= empleado.departamento;
        String deprtementindex ;
        for (Departamento i : departamentoObservableList){
            if (i.id == iddepart){
                return i.departamento;

            }
        }
        return null;
    }


    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, clave);
    }

    private void loadDepartamentos(){
        departamentoObservableList.clear();
        String sql = "Select * FROM departamentos";
        try (Connection con = getConnection();
             Statement stat = con.createStatement();
             ResultSet rs = stat.executeQuery(sql)) {

            while (rs.next()) {
                departamentoObservableList.add(new Departamento(
                        rs.getInt("Id"),
                        rs.getString("Departamento")
                ));
            }

        } catch (SQLException e) {
            showAlert("Error de Base de Datos", "No se pudieron cargar los datos de la base de datos.");
            e.printStackTrace();
        }

    }

    private void loadEmpleadosFromDatabase() {
        empleadosList.clear();
        String sql = "SELECT * FROM empleados";
        try (Connection con = getConnection();
             Statement stat = con.createStatement();
             ResultSet rs = stat.executeQuery(sql)) {

            while (rs.next()) {
                empleadosList.add(new Empleado(
                        rs.getInt("Id"),
                        rs.getString("Nombre"),
                        rs.getDate("FechaNac"),
                        rs.getInt("Sexo"),
                        rs.getInt("Departamento"),
                        rs.getDouble("Salario")
                ));
            }

        } catch (SQLException e) {
            showAlert("Error de Base de Datos", "No se pudieron cargar los datos de la base de datos.");
            e.printStackTrace();
        }
    }

    private void showAlert(String errorDeBaseDeDatos, String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(errorDeBaseDeDatos);
        alert.setHeaderText(null);
        alert.setContentText(s);
        alert.showAndWait();
    }
    private void displayEmpleado(Empleado empleado) {
        if (empleado != null) {
            idtf.setText(String.valueOf(empleado.id));
            nombretf.setText(String.valueOf(empleado.Nombre));
            Salariotf.setText(String.valueOf(empleado.Salario));
        } else {

        }
    }
    private void showEmpleado(int index) {
        if (!empleadosList.isEmpty() && index >= 0 && index < empleadosList.size()) {
            currentIndex = index;
            displayEmpleado(empleadosList.get(currentIndex));
        }
        updateNavigationButtons();
    }
    private int currentIndex = -1;

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
        PrimerRegistro.setDisable(currentIndex <= 0);
        RegistroAnterior.setDisable(currentIndex <= 0);
        RegistroSiguiente.setDisable(currentIndex >= empleadosList.size() - 1);
        UltimoRegistro.setDisable(currentIndex >= empleadosList.size() - 1);
    }
    @FXML
    private void clearFields() {
        nombretf.clear();
        idtf.clear();
        Salariotf.clear();
        fechanacimientodp.setValue(LocalDate.now());
        Femeninocb.equals(false);
        Femeninocb.equals(false);
        ObservableList<String> nombredepartamentos = FXCollections.observableArrayList();
        for (Departamento i: departamentoObservableList){
            nombredepartamentos.add(i.departamento);
        }
        Departamentocb.getItems().clear();

    }
    @FXML
    void saveEmployee() {
        String nombre = nombretf.getText();
        String id = idtf.getText();
        String salarioStr = Salariotf.getText();
        int Sexo = 0;
        if (Femeninocb.isSelected()){
             Sexo = 1;
        } else if (Masculinocb.isSelected()) {
             Sexo = 0;
        }

        ObservableList<String> nombredepartamentos = FXCollections.observableArrayList();
        for (Departamento i: departamentoObservableList){
            nombredepartamentos.add(i.departamento);
        }
        String item  = String.valueOf(Departamentocb.getItems().add(nombredepartamentos));

        if (nombre.isEmpty() || id.isEmpty() || salarioStr.isEmpty() || !Femeninocb.isSelected()||!Masculinocb.isSelected()) {
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

        String sql = "INSERT INTO empleados (Id, Nombre, FechaNac, Sexo,Departamento,Salario) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection con = getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, Integer.parseInt(id));
            ps.setString(2, nombre);
            ps.setString(3, LocalDateTime.now().toString());
            ps.setInt(4, Sexo);
            ps.setInt(5,Integer.parseInt(item));
            ps.setDouble(6,salario);
            ps.executeUpdate();
            loadEmpleadosFromDatabase(); // Recarga la tabla para mostrar el nuevo registro
            showAlert("Éxito", "Empleado guardado correctamente.");
        } catch (SQLException e) {
            showAlert("Error de Base de Datos", "No se pudo insertar el nuevo registro.");
            e.printStackTrace();
        }
    }

}