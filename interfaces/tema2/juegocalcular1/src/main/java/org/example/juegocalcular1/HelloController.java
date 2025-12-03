package org.example.juegocalcular1;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private Button btn1;
    @FXML
    private Button btn2;
    @FXML
    private Button btn5;
    @FXML
    private Button btn7;
    @FXML
    private Button btn10;
    @FXML
    private Button btn15;
    @FXML
    private Button btn20;
    @FXML
    private Button btn25;
    @FXML
    private Button btn50;
    @FXML
    private Button btn60;
    @FXML
    private Button btn75;
    @FXML
    private Button btn100;
    @FXML
    private TextField label_numeroA_Adivinar;
    @FXML
    private TextField minumerotf;
    Integer minumero = 0;
    Integer numero = (int) (Math.random() * 200) + 1;

    @FXML
    private Button newgamebutton;
    @FXML
    void initialize(){
        label_numeroA_Adivinar.setText(numero.toString());
    }
    @FXML
    void pulsarbn2(){
        int numero = Integer.parseInt(btn2.getText());
        subirnumero(numero);
        btn2.setDisable(true);
    }
    @FXML
    void pulsarbn5(){
        int numero = Integer.parseInt(btn5.getText());
        subirnumero(numero);
        btn5.setDisable(true);
    }
    @FXML
    void pulsarbn7(){
        int numero = Integer.parseInt(btn7.getText());
        subirnumero(numero);
        btn7.setDisable(true);
    }
    @FXML
    void pulsarbn1(){
        int numero = Integer.parseInt(btn1.getText());
        subirnumero(numero);
        btn1.setDisable(true);
    }
    @FXML
    void pulsarbn10(){
        int numero = Integer.parseInt(btn10.getText());
        subirnumero(numero);
        btn10.setDisable(true);
    }
    @FXML
    void pulsarbn15(){
        int numero = Integer.parseInt(btn15.getText());
        subirnumero(numero);
        btn15.setDisable(true);
    }
    @FXML
    void pulsarbn20(){
        int numero = Integer.parseInt(btn20.getText());
        subirnumero(numero);
        btn20.setDisable(true);
    }
    @FXML
    void pulsarbn25(){
        int numero = Integer.parseInt(btn25.getText());
        subirnumero(numero);
        btn25.setDisable(true);
    }
    @FXML
    void pulsarbn50(){
        int numero = Integer.parseInt(btn50.getText());
        subirnumero(numero);
        btn50.setDisable(true);
    }
    @FXML
    void pulsarbn60(){
        int numero = Integer.parseInt(btn60.getText());
        subirnumero(numero);
        btn60.setDisable(true);
    }
    @FXML
    void pulsarbn75(){
        int numero = Integer.parseInt(btn75.getText());
        subirnumero(numero);
        btn75.setDisable(true);
    }
    @FXML
    void pulsarbn100(){
        int numero = Integer.parseInt(btn100.getText());
        subirnumero(numero);
        btn100.setDisable(true);
    }
    @FXML
    void enableAllButtons() {
        btn1.setDisable(false);
        btn2.setDisable(false);
        btn5.setDisable(false);
        btn7.setDisable(false);
        btn10.setDisable(false);
        btn15.setDisable(false);
        btn20.setDisable(false);
        btn25.setDisable(false);
        btn50.setDisable(false);
        btn60.setDisable(false);
        btn75.setDisable(false);
        btn100.setDisable(false);
    }

    @FXML
    void newGame() {
        // Resetear valores
        minumero = 0;
        numero = (int) (Math.random() * 200) + 1;
        label_numeroA_Adivinar.setText(numero.toString());
        minumerotf.setText("0");
        enableAllButtons();
    }





    private void subirnumero(int i) {
        minumero += i;
        minumerotf.setText(minumero.toString());
        // Verifica si has adivinado el número
        if (minumero.equals(numero)) {
            // Mostrar alerta de victoria
            javafx.scene.control. Alert alert = new javafx.scene.control.Alert(javafx. scene.control.Alert.AlertType.INFORMATION);
            alert. setTitle("¡Has Ganado!");
            alert.setHeaderText(null);
            alert. setContentText("¡Felicidades! Has acertado el número. Comenzando un nuevo juego...");
            alert.showAndWait();

            // Inicia un nuevo juego
            newGame();
        }
    }

}