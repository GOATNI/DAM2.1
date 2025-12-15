module org.example.allthestyles {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.allthestyles to javafx.fxml;
    exports org.example.allthestyles;
}