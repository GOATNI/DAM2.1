module org.example.imccalculo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.imccalculo to javafx.fxml;
    exports org.example.imccalculo;
}