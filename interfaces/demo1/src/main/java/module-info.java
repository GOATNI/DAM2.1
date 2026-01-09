module com.helping.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.graphics;


    opens com.helping.demo1 to javafx.fxml;
    opens com.helping.demo1.modelo to javafx.base;
    exports com.helping.demo1;
}