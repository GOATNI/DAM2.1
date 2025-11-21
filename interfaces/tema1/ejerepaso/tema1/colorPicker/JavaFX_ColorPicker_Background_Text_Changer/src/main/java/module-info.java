module org.example.eje1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.eje1 to javafx.fxml;
    exports org.example.eje1;
}