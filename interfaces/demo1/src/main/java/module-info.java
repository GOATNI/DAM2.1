module com.helping.demo1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.helping.demo1 to javafx.fxml;
    exports com.helping.demo1;
}