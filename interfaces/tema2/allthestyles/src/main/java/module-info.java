module org.example.allthestyles {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.allthestyles to javafx.fxml;
    exports org.example.allthestyles;
}