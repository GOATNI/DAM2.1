module org.example.eje2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.eje2 to javafx.fxml;
    exports org.example.eje2;
}