module org.example.newslidertable {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.newslidertable to javafx.fxml;
    exports org.example.newslidertable;
}