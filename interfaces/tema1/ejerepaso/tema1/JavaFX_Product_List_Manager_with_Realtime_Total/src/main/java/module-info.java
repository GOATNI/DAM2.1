module org.example.newlistaproductos {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.example.newlistaproductos to javafx.fxml;
    opens org.example.newlistaproductos.modelo to javafx.base;

    exports org.example.newlistaproductos;
}
