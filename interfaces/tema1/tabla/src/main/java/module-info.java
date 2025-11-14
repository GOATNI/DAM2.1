module org.example.tabla {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.tabla to javafx.fxml;
    exports org.example.tabla;
}