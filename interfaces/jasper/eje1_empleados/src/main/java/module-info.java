module org.example.eje1_empleados {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.eje1_empleados to javafx.fxml;
    exports org.example.eje1_empleados;
}