module org.example.rgb {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.rgb to javafx.fxml;
    exports org.example.rgb;
}