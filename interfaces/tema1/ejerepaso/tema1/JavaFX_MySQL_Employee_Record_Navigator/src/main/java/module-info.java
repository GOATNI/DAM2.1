module org.example.sqlconnectormud {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.sqlconnectormud to javafx.fxml;
    exports org.example.sqlconnectormud;
}