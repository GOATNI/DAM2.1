package org.example.newlistaproductos;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.newlistaproductos.modelo.Producto;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML private TextField nameTextField;
    @FXML private TextField unitsTextField;
    @FXML private TextField priceTextField;
    @FXML private TableView<Producto> productsTableView;
    @FXML private TableColumn<Producto, String> nameColumn;
    @FXML private TableColumn<Producto, Integer> unitsColumn;
    @FXML private TableColumn<Producto, Double> priceColumn;
    @FXML private TextField totalTextField;

    // Lista observable que mantiene los productos
    private final ObservableList<Producto> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configuración de columnas
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        unitsColumn.setCellValueFactory(new PropertyValueFactory<>("unidades"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("precio"));

        // Asignar la lista a la tabla
        productsTableView.setItems(observableList);

        // Escuchar cambios en la lista y actualizar el total automáticamente
        observableList.addListener((javafx.collections.ListChangeListener<Producto>) change -> updateTotal());
    }

    @FXML
    protected void addProduct() {
        try {
            String nombre = nameTextField.getText().trim();
            int unidades = Integer.parseInt(unitsTextField.getText().trim());
            double precio = Double.parseDouble(priceTextField.getText().trim().replace(",", "."));

            if (nombre.isEmpty()) {
                showAlert("Error", "El nombre no puede estar vacío.");
                return;
            }

            observableList.add(new Producto(nombre, unidades, precio));

            // Limpiar campos después de agregar
            nameTextField.clear();
unitsTextField.clear();
priceTextField.clear();

        } catch (NumberFormatException e) {
            showAlert("Error de formato", "Ingrese valores numéricos válidos para unidades y precio.");
        }
    }

    // Método para actualizar el campo total
    private void updateTotal() {
        double total = observableList.stream()
                .mapToDouble(p -> p.getPrecio() * p.getUnidades())
                .sum();
        totalTextField.setText(String.format("%.2f", total));
    }

    // Método auxiliar para mostrar alertas
    private void showAlert(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
} 