package com.helping.empresaconsultaexamenjasper;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.sql.*;
import java.util.Map;

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
            /*
            SELECT
                e.nombre,
                e.id,
                l.nombre AS localidad,
                e.departamento,
                e.salario,
                (e.salario * 0.85) AS Salarioneto
            FROM empleados e
            INNER JOIN localidades l ON l.id = e.localidad_id
            WHERE e.salario BETWEEN
                  CAST($P{salariomin} AS DECIMAL(8,2))
              AND CAST($P{salariomax} AS DECIMAL(8,2))
             */
            rs = stat.executeQuery("SELECT * FROM empleados");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private TextField salariomaxtf;
    @FXML
    private TextField salariomintf;
    @FXML
    private Button btnMostrar;
    @FXML
    protected void onMostrarButtonClick() throws JRException {
        double salariomin = Double.parseDouble(salariomintf.getText());
        double salariomax = Double.parseDouble(salariomaxtf.getText());

        String rutaInforme = "/informes/empresa2.jasper";
        generarInformeDepartamento(rutaInforme, salariomin, salariomax);

    }

    private void generarInformeDepartamento(String rutaInforme, double salariomin, double salariomax) throws JRException {
        Map<String, Object> parametros = new java.util.HashMap<>();
        parametros.put("salariomin", salariomin);
        parametros.put("salariomax", salariomax);
            JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream(rutaInforme),parametros,con);
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setTitle("Informe de Empleados por Departamento");
            viewer.setVisible(true);

    }




}
