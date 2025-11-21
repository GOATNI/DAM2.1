module com.clinica.propuesta {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.clinica.propuesta to javafx.fxml;
    exports com.clinica.propuesta;
}