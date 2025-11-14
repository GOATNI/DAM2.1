module org.example.eje4 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.eje4 to javafx.fxml;
    exports org.example.eje4;
}