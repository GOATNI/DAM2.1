module org.example.alerta {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.alerta to javafx.fxml;
    exports org.example.alerta;
}