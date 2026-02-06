package org.example.jasper_chart_empleado;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
            rs = stat.executeQuery(
                    "select d.Departamento  from departamentos d "
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private Button Buscar;
    @FXML
    private ComboBox<String> departamentoComboBox;

    @FXML
    public void initialize() {
        // Inicializar el ComboBox con departamentos de ejemplo
        try {
            while (rs.next()) {
                String departamento = rs.getString("Departamento");
                departamentoComboBox.getItems().add(departamento);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void generarInforme() {
        String departamentoSeleccionado = departamentoComboBox.getValue();
        System.out.println("Generando informe para el departamento: " + departamentoSeleccionado);
        String rutaInforme = "/informes/empleados_jasper_reports.jasper";
        try {
            generarInformeDepartamento(rutaInforme, departamentoSeleccionado);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }

    }

    public void generarInformeDepartamento(String ruta,String departamento) throws JRException {
        System.out.println("Generando informe para el departamento: " + departamento);
        Map <String, Object> parametros = new java.util.HashMap<>();
        parametros.put("localidad", departamento);
        // Lógica para generar el informe JasperReports para el departamento seleccionado
        JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream(ruta),
               parametros,con);
        JasperViewer viewer = new JasperViewer(jasperPrint, false);
        viewer.setTitle("Informe de Empleados por Departamento"+departamento);
        viewer.setVisible(true);
    }
}