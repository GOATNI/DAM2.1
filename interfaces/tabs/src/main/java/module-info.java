module org.example.tabs {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.tabs to javafx.fxml;
    exports org.example.tabs;
}