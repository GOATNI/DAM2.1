package org.example.eje1;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    @FXML
    private ComboBox<String> styleComboBox;

    @FXML
    private CheckBox mainCheckBox;

    @FXML
    private RadioButton mainRadioButton;

    @FXML
    private ToggleButton mainToggleButton;

    @FXML
    private Slider mainSlider;

    @FXML
    private ProgressIndicator mainProgressIndicator;

    @FXML
    private ProgressBar mainProgressBar;

    @FXML
    protected void initialize() {
        // Los valores del Slider varían entre 0 y 100
        mainSlider.setMin(0);
        mainSlider.setMax(100);

        mainSlider.setValue(50);



        mainProgressIndicator.setMinSize(0,1);


        mainProgressIndicator.setProgress(0.75);

        mainProgressBar.setMinSize(0,1);
        // Establecer un valor inicial para demostración (por ejemplo, 0.25)
        mainProgressBar.setProgress(0.25);
    }
}
