package org.example.eje1_empleados;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private ComboBox<String> autonomiaComboBox;

    @FXML
    private TableView<Empleado> empleadosTableView;

    @FXML
    private TableColumn<Empleado, String> nombreColumn;

    @FXML
    private TableColumn<Empleado, LocalDate> fechaNacColumn;

    @FXML
    private TableColumn<Empleado, String> sexoColumn;

    @FXML
    private TableColumn<Empleado, Double> salarioColumn;

    @FXML
    private TableColumn<Empleado, String> departamentoColumn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configurar las columnas de la tabla
        nombreColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        fechaNacColumn.setCellValueFactory(new PropertyValueFactory<>("fechaNac"));
        sexoColumn.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        salarioColumn.setCellValueFactory(new PropertyValueFactory<>("salario"));
        departamentoColumn.setCellValueFactory(new PropertyValueFactory<>("departamento"));

        // Cargar las autonomías en el ComboBox
        List<String> autonomias = DatabaseConnection.getAutonomias();
        ObservableList<String> items = FXCollections.observableArrayList(autonomias);
        autonomiaComboBox.setItems(items);

        // Cargar todos los empleados por defecto
        cargarTodosLosEmpleados();
    }

    private void cargarTodosLosEmpleados() {
        List<Empleado> empleados = DatabaseConnection.getTodosLosEmpleados();
        ObservableList<Empleado> items = FXCollections.observableArrayList(empleados);
        empleadosTableView.setItems(items);
    }

    @FXML
    protected void onBuscarButtonClick() {
        String autonomiaSeleccionada = autonomiaComboBox.getValue();
        
        if (autonomiaSeleccionada == null || autonomiaSeleccionada.isEmpty()) {
            UIUtils.mostrarAdvertencia("Advertencia", "Por favor selecciona una autonomía");
            return;
        }
        
        generarReporte(autonomiaSeleccionada);
    }

    @FXML
    protected void onGenerarReporteClick() {
        String autonomiaSeleccionada = autonomiaComboBox.getValue();
        
        if (autonomiaSeleccionada == null || autonomiaSeleccionada.isEmpty()) {
            UIUtils.mostrarAdvertencia("Advertencia", "Por favor selecciona una autonomía");
            return;
        }
        
        generarReporte(autonomiaSeleccionada);
    }

    private void generarReporte(String autonomia) {
        try {
            // Ruta del archivo JRXML
            String reportePath = Config.REPORTS_PATH + Config.EMPLEADOS_REPORT;
            File reportFile = new File(reportePath);
            
            if (!reportFile.exists()) {
                UIUtils.mostrarError("Error", "Archivo de reporte no encontrado en: " + reportePath);
                return;
            }
            
            // Compilar el reporte
            JasperReport jasperReport = JasperCompileManager.compileReport(reportePath);
            
            // Obtener conexión
            Connection conn = DatabaseConnection.getConnection();
            
            // Llenar el reporte con los parámetros
            java.util.Map<String, Object> parameters = new java.util.HashMap<>();
            parameters.put("autonomia", autonomia);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, conn);
            
            // Mostrar el reporte
            JasperViewer viewer = new JasperViewer(jasperPrint, false);
            viewer.setVisible(true);
            
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.mostrarError("Error", "Error al generar el reporte: " + e.getMessage());
        }
    }
}

