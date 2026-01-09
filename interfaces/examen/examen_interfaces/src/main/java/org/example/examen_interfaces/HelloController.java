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
import java.util.Properties;

import static org.example.examen_interfaces.EmpleadosTable.stat;


public class HelloController {
    private static final String url = "jdbc:mysql://localhost:3306/empresa";
    private static final String user = "root";
    private static final String clave = "1234";
    private static Connection con;
    private static ResultSet rs;

    static {
        try {
            con = DriverManager.getConnection(url, user, clave);
            Statement stat = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            rs = stat.executeQuery(
                    "select e.*,d.Departamento as nombre_dep from empresa.empleados e\n" +
                            "join empresa.departamentos d \n" +
                            "on e.`Departamento` = d.`Id`;"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    DatePicker fechanacimientodp;
    @FXML
    RadioButton Femeninocb;
    @FXML
     RadioButton Masculinocb;
    @FXML
    private TextField idtf;
    @FXML
     TextField nombretf;
    @FXML
     ComboBox Departamentocb;
    @FXML
     TextField Salariotf;
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
    private ObservableList<String> companies = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws SQLException {

        try (Statement tempStat = con.createStatement();
             ResultSet rsCompanies = tempStat.executeQuery("select Departamento from empresa.departamentos;")) {

            while (rsCompanies.next()) {
                companies.add(rsCompanies.getString("Departamento").trim());
            }
            Departamentocb.setItems(companies);
        }


        if (rs.next()) {
            loadPersonFromResultSet();
        }
    }

    public void next() throws SQLException {
        if (rs.next()) {
            loadPersonFromResultSet();
        }
    }

    public void previous() throws SQLException {
        if (rs.previous()) {
            loadPersonFromResultSet();
        }
    }

    public void first() throws SQLException {
        if (rs.first()) {
            loadPersonFromResultSet();
        }
    }

    public void last() throws SQLException {
        if (rs.last()) {
            loadPersonFromResultSet();
        }
    }
    private void loadPersonFromResultSet() throws SQLException {

        idtf.setText(String.valueOf(rs.getInt("Id")));
        nombretf.setText(rs.getString("Nombre"));

        Date dob = rs.getDate("FechaNac");
        if (dob != null) {
            fechanacimientodp.setValue(dob.toLocalDate());
        }

        // Gender
        Integer gender = rs.getInt("Sexo");
        if (gender == 1) {
            Femeninocb.setSelected(true);
        } else if (gender == 0) {
            Masculinocb.setSelected(true);
        }

       Salariotf.setText(String.valueOf(rs.getDouble("Salario")));

        // Company preselection
        String personCompany = rs.getString("nombre_dep").trim();
        if (companies.contains(personCompany)) {
            Departamentocb.getSelectionModel().select(personCompany);
        }
    }

    @FXML
    public void newempleado(){

        idtf.clear();
        idtf.setDisable(true);
        nombretf.clear();
        fechanacimientodp.setValue(null);
        Femeninocb.setSelected(false);
        Masculinocb.setSelected(false);
        Salariotf.clear();
        Departamentocb.setValue(null);
    }

    @FXML
    public  void addempleado(){

        int dep = Departamentocb.getSelectionModel().getSelectedIndex()+1;
        String nombre = nombretf.getText();
        LocalDate date = fechanacimientodp.getValue();
        int sex = 0;
        if (Femeninocb.isSelected()){
            sex = 1;
        }else if (Masculinocb.isSelected()){
            sex = 0;
        }


        String salarioTexto = Salariotf.getText();
        System.out.println(salarioTexto);

        if (salarioTexto == null || salarioTexto.trim().isEmpty()) {
            // Muestra una alerta al usuario o asigna un valor por defecto
            System.out.println("Error: El campo de salario está vacío");
            return; // Detiene la ejecución para que no intente el parseDouble
        }

        double salario = 0.0;
        try {
             salario = Double.parseDouble(salarioTexto.trim());
            // ... resto de tu lógica para guardar en la DB
        } catch (NumberFormatException e) {
            System.out.println("Error: El formato del número es incorrecto");
        }

        String sqlInsert = "INSERT INTO empresa.empleados (Nombre, FechaNac, Sexo, Departamento, Salario) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sqlInsert)) {
            pstmt.setString(1,nombre );
            pstmt.setDate(2,Date.valueOf(date));
            pstmt.setInt(3, sex);
            pstmt.setInt(4, dep);
            pstmt.setDouble(5,salario);

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("SUCCESS: Employee added to database!");
                loadPersonFromResultSet();
            }
            refreshResultSet();
        } catch (SQLException e) {
            System.err.println("SQL Error Code: " + e.getErrorCode());
            e.printStackTrace(); // This will tell you if the ENUM or FOREIGN KEY failed
        }
    }
    private void refreshResultSet() throws SQLException {
        // Re-execute the main query
        rs = stat.executeQuery(
                "select e.*,d.Departamento as nombre_dep from empresa.empleados e join empresa.departamentos d on e.`Departamento` = d.`Id`;"
        );

        rs.last();
        loadPersonFromResultSet();
    }

    @FXML
    public void updateempleado() {
        // 1. Obtener el ID del empleado (es imprescindible para el WHERE)
        String idTexto = idtf.getText();
        if (idTexto == null || idTexto.isEmpty()) {
            System.out.println("Error: No hay un ID seleccionado para actualizar");
            return;
        }
        int id = Integer.parseInt(idTexto);

        // 2. Obtener los nuevos valores de los campos
        String nombre = nombretf.getText();
        LocalDate date = fechanacimientodp.getValue();
        int dep = Departamentocb.getSelectionModel().getSelectedIndex() + 1;
        int sex = Femeninocb.isSelected() ? 1 : 0;

        double salario = 0.0;
        try {
            salario = Double.parseDouble(Salariotf.getText().trim());
        } catch (NumberFormatException e) {
            System.out.println("Error: Salario no válido");
            return;
        }

        // 3. Sentencia SQL UPDATE
        String sqlUpdate = "UPDATE empresa.empleados SET Nombre = ?, FechaNac = ?, Sexo = ?, Departamento = ?, Salario = ? WHERE Id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sqlUpdate)) {
            pstmt.setString(1, nombre);
            pstmt.setDate(2, Date.valueOf(date));
            pstmt.setInt(3, sex);
            pstmt.setInt(4, dep);
            pstmt.setDouble(5, salario);
            pstmt.setInt(6, id); // El ID va en el último '?' para el WHERE

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("SUCCESS: Empleado actualizado correctamente.");
            }
        } catch (SQLException e) {
            System.err.println("Error al actualizar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    public void deleteempleado() {
        // 1. Obtener el ID del campo de texto
        String idTexto = idtf.getText();

        if (idTexto == null || idTexto.isEmpty()) {
            System.out.println("Error: No hay ID para eliminar.");
            return;
        }

        // 2. Sentencia SQL DELETE
        String sqlDelete = "DELETE FROM empresa.empleados WHERE Id = ?";

        try (PreparedStatement pstmt = con.prepareStatement(sqlDelete)) {
            pstmt.setInt(1, Integer.parseInt(idTexto));

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("SUCCESS: Registro con ID " + idTexto + " eliminado.");

                // 3. Limpiar los campos de la interfaz
                newempleado();

                refreshResultSet();
            }
        } catch (SQLException e) {
            System.err.println("Error al eliminar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void mostrarAlerta(String titulo, String encabezado, String mensaje, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(encabezado);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }





}