module org.example.controlsfx_demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens org.example.controlsfx_demo to javafx.fxml;
    exports org.example.controlsfx_demo;
}