module org.example.repaso {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.repaso to javafx.fxml;
    exports org.example.repaso;
}