module org.example.validationfx {
    requires javafx.controls;
    requires javafx.fxml;

    requires net.synedra.validatorfx;

    opens org.example.validationfx to javafx.fxml;
    exports org.example.validationfx;
}