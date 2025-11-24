module org.example.boostrap {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens org.example.boostrap to javafx.fxml;
    exports org.example.boostrap;
}