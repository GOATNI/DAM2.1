module org.example.examen_interfaces {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.examen_interfaces to javafx.fxml;
    exports org.example.examen_interfaces;
}