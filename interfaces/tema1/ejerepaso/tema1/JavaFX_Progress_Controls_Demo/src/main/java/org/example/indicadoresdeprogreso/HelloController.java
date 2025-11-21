package org.example.indicadoresdeprogreso;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    @FXML
    private Label progressBarLabel;
    @FXML
    private Label sliderLabel;
    @FXML
    private Label indicatorLabel;

    @FXML
    private ProgressBar progressBar;

    @FXML
    private ProgressIndicator progressIndicator;

    @FXML
    private Slider progressSlider;

    @FXML
    private Button progressBarDecrementButton;

    @FXML
    private Button progressBarIncrementButton;
    @FXML
    private Button sliderDecrementButton;
    @FXML
    private Button sliderIncrementButton;
    @FXML
    private Button indicatorDecrementButton;
    @FXML
    private Button indicatorIncrementButton;

    @FXML
    void initialize() {
        // Initialize labels with current progress
        progressBarLabel.setText(String.format("%.0f%%", progressBar.getProgress() * 100));
        indicatorLabel.setText(String.format("%.0f%%", progressIndicator.getProgress() * 100));
        sliderLabel.setText(String.format("%.0f", progressSlider.getValue()));

        // Add listeners to update labels when values change
        progressBar.progressProperty().addListener((obs, oldVal, newVal) ->
                progressBarLabel.setText(String.format("%.0f%%", newVal.doubleValue() * 100)));
        progressIndicator.progressProperty().addListener((obs, oldVal, newVal) ->
                indicatorLabel.setText(String.format("%.0f%%", newVal.doubleValue() * 100)));
        progressSlider.valueProperty().addListener((obs, oldVal, newVal) ->
                sliderLabel.setText(String.format("%.0f", newVal.doubleValue())));
    }

    @FXML
    protected void incrementProgressBar() {
        if (progressBar.getProgress() < 1.0) {
            progressBar.setProgress(progressBar.getProgress() + 0.1); // Increment by 10%
        } else {
            progressBar.setProgress(1.0);
        }
    }

    @FXML
    protected void decrementProgressBar() {
        if (progressBar.getProgress() > 0.0) {
            progressBar.setProgress(progressBar.getProgress() - 0.1); // Decrement by 10%
        } else {
            progressBar.setProgress(0.0);
        }
    }

    @FXML
    protected void incrementProgressIndicator() {
        if (progressIndicator.getProgress() < 1.0) {
            progressIndicator.setProgress(progressIndicator.getProgress() + 0.1); // Increment by 10%
        } else {
            progressIndicator.setProgress(1.0);
        }
    }

    @FXML
    protected void decrementProgressIndicator() {
        if (progressIndicator.getProgress() > 0.0) {
            progressIndicator.setProgress(progressIndicator.getProgress() - 0.1); // Decrement by 10%
        } else {
            progressIndicator.setProgress(0.0);
        }
    }

    @FXML
    protected void incrementSlider() {
        progressSlider.increment();
    }

    @FXML
    protected void decrementSlider() {
        progressSlider.decrement();
    }
}