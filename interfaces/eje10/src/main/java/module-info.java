module org.example.eje10 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;


    opens org.example.eje10 to javafx.fxml;
    exports org.example.eje10;
}