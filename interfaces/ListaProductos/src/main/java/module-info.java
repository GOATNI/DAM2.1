module org.example.listaproductos {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.listaproductos to javafx.fxml;
    exports org.example.listaproductos;
}