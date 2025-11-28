module org.example.juegocalcular1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.juegocalcular1 to javafx.fxml;
    exports org.example.juegocalcular1;
}