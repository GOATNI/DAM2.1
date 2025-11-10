module org.example.ejercicio_diferentes_estilos {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.ejercicio_diferentes_estilos to javafx.fxml;
    exports org.example.ejercicio_diferentes_estilos;
}