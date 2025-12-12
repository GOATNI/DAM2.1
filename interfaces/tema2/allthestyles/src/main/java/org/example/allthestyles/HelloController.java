package org.example.allthestyles;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

public class HelloController {
    // Declarar todos los elementos del FXML
    @FXML
    private Label welcomeText;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button mainButton;

    @FXML
    private CheckBox checkBox;

    @FXML
    private ChoiceBox<String> choiceBox;

    @FXML
    private ComboBox<String> comboBox;
    @FXML
    private TableView<Persona> tableView;

    @FXML
    private TableColumn<Persona, String> column1;

    @FXML
    private TableColumn<Persona, Integer> column2;
    @FXML
    private TextField textField;

    @FXML
    private Spinner<Integer> spinner;

    @FXML
    private Slider horizontalSlider;

    @FXML
    private ProgressBar progressBar;

    // Variable para contar clicks
    private int clickCount = 0;

    // Este método se ejecuta automáticamente al iniciar la aplicación
    @FXML
    public void initialize() {
        // Llenar el ChoiceBox con opciones
        choiceBox.getItems().addAll("Opción 1", "Opción 2", "Opción 3", "Opción 4");
        choiceBox.setValue("Opción 1");

        // Llenar el ComboBox con ciudades
        comboBox.getItems().addAll("Madrid", "Barcelona", "Valencia", "Sevilla", "Bilbao");
        comboBox.setPromptText("Elige una ciudad");

        // Inicializar TableView con datos
        ObservableList<Persona> personasList = FXCollections.observableArrayList(
                new Persona("Juan", 25),
                new Persona("María", 30),
                new Persona("Pedro", 22),
                new Persona("Ana", 28),
                new Persona("Luis", 35)
        );

        column1.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        column2.setCellValueFactory(new PropertyValueFactory<>("edad"));
        tableView.setItems(personasList);

        // Configurar el Spinner (números del 0 al 100, empieza en 18)
        SpinnerValueFactory<Integer> numeros =
                new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 100, 18);
        spinner.setValueFactory(numeros);

        // Configurar el TextField
        textField.setPromptText("Escribe tu nombre...");

        // Conectar el Slider con el ProgressBar
        // Cuando mueves el slider, la barra de progreso se llena
        horizontalSlider.valueProperty().addListener((obs, viejo, nuevo) -> {
            double porcentaje = nuevo.doubleValue() / 100;
            progressBar.setProgress(porcentaje);
        });
    }

    // Cuando haces click en el botón principal
    @FXML
    protected void onHelloButtonClick() {
        clickCount++;
        mainButton.setText("Clicks: " + clickCount);
        welcomeText.setText("¡Has hecho " + clickCount + " clicks!");
    }

    // Cuando activas el CheckBox
    @FXML
    protected void onCheckBoxClick() {
        if (checkBox.isSelected()) {
            welcomeText.setText("CheckBox activado ✓");
        } else {
            welcomeText.setText("CheckBox desactivado");
        }
    }

    // Cuando escribes en el TextField
    @FXML
    protected void onTextChanged() {
        String texto = textField.getText();
        if (!texto.isEmpty()) {
            welcomeText.setText("Hola, " + texto + "!");
        }
    }

    // MÉTODOS PARA CAMBIAR ESTILOS

    @FXML
    protected void onEstiloVacio() {
        rootPane.getStylesheets().clear();
        String css = getClass().getResource("estilo.css").toExternalForm();
        rootPane.getStylesheets().add(css);
        welcomeText.setText("Estilo: Sin estilo (por defecto)");
    }

    @FXML
    protected void onEstilo1() {
        rootPane.getStylesheets().clear();
        String css = getClass().getResource("estilo1.css").toExternalForm();
        rootPane.getStylesheets().add(css);
        welcomeText.setText("Estilo: Tema Claro 🌞");
    }

    @FXML
    protected void onEstilo2() {
        rootPane.getStylesheets().clear();
        String css = getClass().getResource("estilo2.css").toExternalForm();
        rootPane.getStylesheets().add(css);
        welcomeText.setText("Estilo: Tema Oscuro 🌙");
    }

    @FXML
    protected void onEstilo3() {
        rootPane.getStylesheets().clear();
        String css = getClass().getResource("estilo3.css").toExternalForm();
        rootPane.getStylesheets().add(css);
        welcomeText.setText("Estilo: Tema Océano 🌊");
    }
}