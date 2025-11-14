module org.example.sliderrgb {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.sliderrgb to javafx.fxml;
    exports org.example.sliderrgb;
}