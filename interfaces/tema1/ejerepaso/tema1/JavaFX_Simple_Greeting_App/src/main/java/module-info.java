module org.example.saludo {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.saludo to javafx.fxml;
    exports org.example.saludo;
}