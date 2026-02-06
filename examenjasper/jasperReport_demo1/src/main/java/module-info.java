module org.example.jasperreport_demo1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.jasperreport_demo1 to javafx.fxml;
    exports org.example.jasperreport_demo1;
}