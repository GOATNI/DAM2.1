module org.example.examneinterfaces2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.examneinterfaces2 to javafx.fxml;
    exports org.example.examneinterfaces2;
}