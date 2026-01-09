module com.helping.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.helping.biblioteca to javafx.fxml;
    exports com.helping.biblioteca;
}