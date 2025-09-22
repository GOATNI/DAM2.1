package org.example.indicadoresdeprogreso;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    @FXML
    private Label progressbarText;
    @FXML
    private Label SliderText;
    @FXML
    private Label IndicatorText;

    @FXML
    private ProgressBar progressbar;

    @FXML
    private ProgressIndicator indicator;

    @FXML
    private Slider sliderProgress;

    @FXML
    private Button progresbarButtonDecrement;

    @FXML
    private Button progresbarButtonIecrement;
    @FXML
    private Button SliderButtonDecrement;
    @FXML
    private Button SliderButtonIecrement;
    @FXML
    private Button IndicatorButtonDecrement;
    @FXML
    private Button IndicatorButtonIecrement;

    @FXML
    protected  void incrementr_progresbar(){
        if (progressbar.getProgress()<100) {
            double valorProgresBar = progressbar.getProgress();
            progressbar.setProgress(valorProgresBar + 0.2);
            progressbarText.setText(String.valueOf(valorProgresBar));
        }else
            progressbar.setProgress(1);
    }
    @FXML
    protected  void decrementr_progresbar(){
        if (progressbar.getProgress()>0) {
            double valorProgresBar = progressbar.getProgress();
            progressbar.setProgress(valorProgresBar - 0.2);
        }else
            progressbar.setProgress(0);
    }
    @FXML
    protected  void incrementr_Indicator(){
        if (indicator.getProgress()<100) {
            double valorProgresBar = indicator.getProgress();
            indicator.setProgress(valorProgresBar + 0.2);
        }else
            indicator.setProgress(1);
    }
    @FXML
    protected  void decrementr_Indicator(){
        if (indicator.getProgress()>0) {
            double valorProgresBar = indicator.getProgress();
            indicator.setProgress(valorProgresBar - 0.2);
        }else
            indicator.setProgress(0);
    }
    @FXML
    protected void IncrementarSlider(){

        sliderProgress.increment();
    }
    @FXML
    protected void DecrementSlider(){
        sliderProgress.decrement();
    }
}