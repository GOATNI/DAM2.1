module org.example.enenuevo {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.enenuevo to javafx.fxml;
    exports org.example.enenuevo;
}