module com.helping.empresaconsultaexamenjasper {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires net.sf.jasperreports.core;


    opens com.helping.empresaconsultaexamenjasper to javafx.fxml;
    exports com.helping.empresaconsultaexamenjasper;
}