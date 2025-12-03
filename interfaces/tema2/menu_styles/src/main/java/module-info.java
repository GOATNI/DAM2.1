module org.example.menu_styles {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.menu_styles to javafx.fxml;
    exports org.example.menu_styles;
}