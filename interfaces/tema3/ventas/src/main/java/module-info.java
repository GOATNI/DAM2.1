module org.example.ventas {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.sf.jasperreports.core;


    opens org.example.ventas to javafx.fxml;
    exports org.example.ventas;
}