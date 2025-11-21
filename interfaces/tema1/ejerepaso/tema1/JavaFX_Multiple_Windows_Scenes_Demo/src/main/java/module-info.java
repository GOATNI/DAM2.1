module org.example.projecto_varias_ventanas {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.projecto_varias_ventanas to javafx.fxml;
    exports org.example.projecto_varias_ventanas;
}