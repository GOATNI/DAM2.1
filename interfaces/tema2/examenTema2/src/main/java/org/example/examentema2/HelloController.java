package org.example.examentema2;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class HelloController {
    @FXML
    private ToggleButton btnF;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private ToggleButton btnAM;
    @FXML
    private ToggleButton btnPV;
    @FXML
    private ToggleButton btnN;
    @FXML
    private RadioButton rbF;
    @FXML
    private RadioButton rbAM;
    @FXML
    private RadioButton rbPV;
    @FXML
    private RadioButton rbN;
    @FXML
    private Slider slider;
    @FXML
    private ProgressIndicator progressIndicator;
    @FXML
    private ProgressBar progressBar;
    @FXML
    private ImageView imageView;

    @FXML
    private void pulsarbtnF(){
        progressBar.setProgress(1.0);
    }
    @FXML
    private void pulsarbtnAM(){
        progressBar.setProgress(0.75);
    }
    @FXML
    private void pulsarbtnPV(){
        progressBar.setProgress(0.5);
    }
    @FXML
    private void pulsarbtnN(){
        progressBar.setProgress(0.25);
    }

    @FXML
    private void radioselectedF(){
        if (rbF.isSelected()){
            progressIndicator.setProgress(1);
        }
    }
    @FXML
    private void radioselectedAM(){
        if (rbAM.isSelected()){
            progressIndicator.setProgress(0.75);
        }
    }
    @FXML
    private void radioselectedPV(){
        if (rbPV.isSelected()){
            progressIndicator.setProgress(0.5);
        }
    }
    @FXML
    private void radioselectedN(){
        if (rbN.isSelected()){
            progressIndicator.setProgress(0.25);
        }
    }

    @FXML
    private void Slider(){
        if (slider.getValue()<=33.3){
            imageView.setImage(new Image("nivel1.jpg"));
        } else if (slider.getValue()>33.3&&slider.getValue()<66.6) {
            imageView.setImage(new Image("nivel2.jpg"));
        } else if (slider.getValue()>=66.6) {
            imageView.setImage(new Image("nivel3.jpg"));

        }
    }

    @FXML
    protected void onEstiloVacio() {
        rootPane.getStylesheets().clear();
        String css = getClass().getResource("estilo.css").toExternalForm();
        rootPane.getStylesheets().add(css);

    }

    @FXML
    protected void onEstilo1() {
        rootPane.getStylesheets().clear();
        String css = getClass().getResource("estilo1.css").toExternalForm();
        rootPane.getStylesheets().add(css);

    }

    @FXML
    protected void onEstilo2() {
        rootPane.getStylesheets().clear();
        String css = getClass().getResource("estilo2.css").toExternalForm();
        rootPane.getStylesheets().add(css);
    }
    @FXML
    private void ayudar() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(new URI("ayuda.chm"));
    }



}