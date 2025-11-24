module org.example.interfaceformulario {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.interfaceformulario to javafx.fxml;
    exports org.example.interfaceformulario;
}