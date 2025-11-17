module org.example.ejercicio_repaso_examen {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.ejercicio_repaso_examen to javafx.fxml;
    exports org.example.ejercicio_repaso_examen;
}