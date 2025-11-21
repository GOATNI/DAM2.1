module org.example.eje3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.eje3 to javafx.fxml;
    exports org.example.eje3;
}