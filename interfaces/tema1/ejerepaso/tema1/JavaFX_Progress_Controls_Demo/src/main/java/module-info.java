module org.example.indicadoresdeprogreso {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.indicadoresdeprogreso to javafx.fxml;
    exports org.example.indicadoresdeprogreso;
}