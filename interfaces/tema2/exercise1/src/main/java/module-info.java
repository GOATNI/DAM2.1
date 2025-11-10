module org.example.exercise1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.exercise1 to javafx.fxml;
    exports org.example.exercise1;
}