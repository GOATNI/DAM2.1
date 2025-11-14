module org.example.eje1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.eje1 to javafx.fxml;
    exports org.example.eje1;
}