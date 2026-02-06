module org.example.ventas2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.sf.jasperreports.core;


    opens org.example.ventas2 to javafx.fxml;
    exports org.example.ventas2;

}