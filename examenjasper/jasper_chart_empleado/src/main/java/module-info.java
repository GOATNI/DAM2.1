module org.example.jasper_chart_empleado {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.sf.jasperreports.core;


    opens org.example.jasper_chart_empleado to javafx.fxml;
    exports org.example.jasper_chart_empleado;
}