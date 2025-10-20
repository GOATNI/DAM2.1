package org.example.tabla;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    private static final String url = "jdbc:mysql://localhost:3306/data";
    private static final String user = "root";
    private static final String clave = "1234";
    private  static ResultSet rs = null;
    private static final Connection con;

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
    private Button ventana2;
    @FXML
    private TextField Localidadtf;




    @FXML
    protected void onHelloButtonClick() throws SQLException {

        Ventana2 v = new Ventana2();
        v.abrirVentana(filtrar());
        try {
            v.abrirventana();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ArrayList<Empleado> filtrar() throws SQLException {
        ArrayList<Empleado> emplead = new ArrayList<>();
        for (Empleado i:empleadosget()) {
            if (i.getLocalidad().equalsIgnoreCase(Localidadtf.getText().toString())){
                emplead.add(i);
            }
        }
        return emplead;
    }


    private ArrayList<Empleado> empleadosget() throws SQLException {
        String sql = "select * from data.empl" +
                "" +
                "eados";
        List<Empleado> empleados = new ArrayList<>();
        rs = stat.executeQuery(sql);
        while (rs.next()){

            Empleado p = new Empleado(rs.getString("Nombre"),rs.getString("Apellidos"),
                    rs.getString("Localidad"),rs.getString("Salario"));
            empleados.add(p);

        }
        return (ArrayList<Empleado>) empleados;
    }

}