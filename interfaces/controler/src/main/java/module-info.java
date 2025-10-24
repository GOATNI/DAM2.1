module org.example.controler {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.controler to javafx.fxml;
    exports org.example.controler;
}