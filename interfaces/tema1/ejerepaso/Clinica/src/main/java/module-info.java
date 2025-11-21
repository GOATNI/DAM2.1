module com.clinica.clinica {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.clinica.clinica to javafx.fxml;
    exports com.clinica.clinica;
}