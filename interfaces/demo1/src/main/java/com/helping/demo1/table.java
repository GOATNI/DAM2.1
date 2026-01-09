package com.helping.demo1;

import com.helping.demo1.modelo.empleado;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;

public class table {

    private static final String url = "jdbc:mysql://localhost:3306/javafx_exam";
    private static final String user = "root";
    private static final String clave = "1234";

    private static final ResultSet rs;

    static {
        try {
            Connection con = DriverManager.getConnection(url, user, clave);
            Statement stat = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            rs = stat.executeQuery(
                    "SELECT e.full_name, e.email, e.phone, e.date_of_birth FROM employee e "
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @FXML
    private TableView<empleado> piezasTableView;
    @FXML
    private TableColumn<empleado,String> TNombre;
    @FXML
    private TableColumn<empleado, String> Tgmail;
    @FXML
    private TableColumn<empleado,String> Tphone;
    @FXML
    private  TableColumn<empleado,Date> Tdatebirth;

    @FXML
    public void initialize() throws SQLException {


        ArrayList<empleado> empleados = new ArrayList<>();

        // 3. Fill list from database
        while (rs.next()) {
            empleado emp = new empleado(
                    rs.getString("full_name"),
                    rs.getString("email"),
                    rs.getString("phone"),
                    rs.getDate("date_of_birth")
            );
            empleados.add(emp);
        }
        TNombre.setCellValueFactory(new PropertyValueFactory<>("nombreEntero"));
        Tgmail.setCellValueFactory(new PropertyValueFactory<>("gmail"));
        Tphone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        Tdatebirth.setCellValueFactory(new PropertyValueFactory<>("dateofbirth"));

        // 4. Put data into TableView
        piezasTableView.getItems().setAll(empleados);
    }


}
