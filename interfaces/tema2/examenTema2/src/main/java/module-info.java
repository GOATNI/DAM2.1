module org.example.examentema2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.examentema2 to javafx.fxml;
    exports org.example.examentema2;
}