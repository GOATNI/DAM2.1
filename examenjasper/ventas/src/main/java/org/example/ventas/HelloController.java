package org.example.ventas;

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
    private static final String url = "jdbc:mysql://localhost:3306/venta";
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
                    "select v.Categoría  from ventas v "
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private ComboBox<String> categoriascb;
    @FXML
    private Button btnMostrar;

    @FXML
    public void  initialize() {
        try {
            while (rs.next()) {
                String categoria = rs.getString("Categoría");
                if (!categoriascb.getItems().contains(categoria)) {
                    categoriascb.getItems().add(categoria);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    protected void onMostrarButtonClick() {
        String categoriaSeleccionada = categoriascb.getValue();
        String rutaInforme = "/informes/ventas_report_jasper.jasper";
        try {
            generarInformeDepartamento(rutaInforme, categoriaSeleccionada);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }

    }

    public void generarInformeDepartamento(String ruta,String categoria) throws JRException {
        System.out.println("Generando informe para el departamento: " + categoria);
        Map<String, Object> parametros = new java.util.HashMap<>();
        parametros.put("categoria", categoria);
        // Lógica para generar el informe JasperReports para el departamento seleccionado
        JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream(ruta),
                parametros,con);
        JasperViewer viewer = new JasperViewer(jasperPrint, false);
        viewer.setTitle("Informe de Empleados por Departamento"+categoria);
        viewer.setVisible(true);
    }
}
