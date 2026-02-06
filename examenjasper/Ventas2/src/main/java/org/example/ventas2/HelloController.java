package org.example.ventas2;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

import java.io.InputStream;
import java.sql.*;

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
    private Button btnagrupado;
    @FXML
    private Button btnventatotal;
    @FXML
    private Button btnMostrar;


    @FXML
    public void onMostrarButtonClick2() {
        try {
            // Quitamos "src/main/resources/"
            generarInformeDepartamento("informes/ejercicio8.jasper");
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onMostrarButtonClick() {
        try {
            generarInformeDepartamento("informes/ejercicio8grouped.jasper");
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void onMostrarButtonClick3() {
        try {
            generarInformeDepartamento("informes/ejercicio8ventatotal.jasper");
        } catch (JRException e) {
            e.printStackTrace();
        }
    }





//    public void generarInformeDepartamento(String ruta) throws JRException {
//        InputStream reportStream = getClass().getClassLoader().getResourceAsStream(ruta);
//
//        if (reportStream == null) {
//            throw new RuntimeException("No se encontró el archivo: " + ruta);
//        }
//
//        JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, null, con);
//        JasperViewer viewer = new JasperViewer(jasperPrint, false);
//        viewer.setTitle("Informe de Ventas por Categoría");
//        viewer.setVisible(true);
//
////        // Lógica para generar el informe JasperReports para el departamento seleccionado
////        JasperPrint jasperPrint = JasperFillManager.fillReport(getClass().getResourceAsStream(ruta),
////                null,con);
////        JasperViewer viewer = new JasperViewer(jasperPrint, false);
////        viewer.setTitle("Informe de Ventas por Categoría");
////        viewer.setVisible(true);
//    }

public void generarInformeDepartamento(String ruta) throws JRException {
    // Añadimos una "/" al principio para que busque desde la raíz del recurso
    InputStream reportStream = getClass().getResourceAsStream("/" + ruta);

    if (reportStream == null) {
        throw new RuntimeException("Error: No se pudo encontrar el recurso en: /" + ruta);
    }

    JasperPrint jasperPrint = JasperFillManager.fillReport(reportStream, null, con);

    // Importante: JasperViewer es Swing. En JavaFX a veces da problemas de enfoque,
    // pero para un examen o aplicación básica funciona bien con (false).
    JasperViewer viewer = new JasperViewer(jasperPrint, false);
    viewer.setTitle("Informe de Ventas");
    viewer.setVisible(true);
}



}