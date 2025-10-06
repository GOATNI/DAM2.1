module org.example.fotosize {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.fotosize to javafx.fxml;
    exports org.example.fotosize;
}