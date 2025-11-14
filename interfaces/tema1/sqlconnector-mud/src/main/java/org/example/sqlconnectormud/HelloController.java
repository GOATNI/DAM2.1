package org.example.sqlconnectormud;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.*;




public class HelloController {
    private static final String url = "jdbc:mysql://localhost:3306/data";
    private static final String user = "root";
    private static final String clave = "1234";
    private  static ResultSet rs = null;
    private static final  Connection con;

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
    void initialize() throws SQLException {
        String sql = "select * from data.empleados";
             rs = stat.executeQuery(sql);
        if (rs.next()) {
            Nombre.setText(rs.getString("Nombre"));
            Apellidos.setText(rs.getString("Apellidos"));
            Localidad.setText(rs.getString("Localidad"));
            Salario.setText(rs.getString("Salario"));
        }
        guardar.setDisable(true);

    }
    @FXML
    void next() throws SQLException {
        if (rs.next()) {
            Nombre.setText(rs.getString("Nombre"));
            Apellidos.setText(rs.getString("Apellidos"));
            Localidad.setText(rs.getString("Localidad"));
            Salario.setText(rs.getString("Salario"));
        }else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error de Registro");
            errorAlert.setHeaderText("¡Error!");
            errorAlert.setContentText("No hay mas datos");
            errorAlert.showAndWait();
        }
    }
    @FXML
    void previous() throws SQLException{
        if (rs.previous()) {
            Nombre.setText(rs.getString("Nombre"));
            Apellidos.setText(rs.getString("Apellidos"));
            Localidad.setText(rs.getString("Localidad"));
            Salario.setText(rs.getString("Salario"));
        }else {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error de Registro");
            errorAlert.setHeaderText("¡Error!");
            errorAlert.setContentText("No hay mas datos");
            errorAlert.showAndWait();
        }
    }
    @FXML
    void last () throws SQLException {

        if (rs.last()) {
            Nombre.setText(rs.getString("Nombre"));
            Apellidos.setText(rs.getString("Apellidos"));
            Localidad.setText(rs.getString("Localidad"));
            Salario.setText(rs.getString("Salario"));
        }
    }
    @FXML
    void first() throws SQLException{
        if (rs.first()) {
            Nombre.setText(rs.getString("Nombre"));
            Apellidos.setText(rs.getString("Apellidos"));
            Localidad.setText(rs.getString("Localidad"));
            Salario.setText(rs.getString("Salario"));
        }
    }
    private void insertarEmpleado(String nombre, String apellidos, String localidad, String salario) throws SQLException {
        String sql = "INSERT INTO empleados (Nombre, Apellidos, Localidad, Salario) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, nombre);
            ps.setString(2, apellidos);
            ps.setString(3, localidad);
            ps.setString(4, salario);
            ps.executeUpdate();
        }
    }

    @FXML
    void resgister(){
        Nombre.setText("");
        Apellidos.setText("");
        Salario.setText("");
        Localidad.setText("");
        guardar.setDisable(false);
    }

    @FXML
    void  save() throws SQLException {
       insertarEmpleado(Nombre.getText(), Apellidos.getText(), Localidad.getText(),Salario.getText());
       initialize();
    }


}