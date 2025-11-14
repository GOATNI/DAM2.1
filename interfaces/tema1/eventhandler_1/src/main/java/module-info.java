module org.example.eventhandler_1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.eventhandler_1 to javafx.fxml;
    exports org.example.eventhandler_1;
}