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

    @FXML private TextField nombreTf;
    @FXML private TextField unidadesTf;
    @FXML private TextField precioTf;
    @FXML private TableView<Producto> tablaProductos;
    @FXML private TableColumn<Producto, String> tcNombre;
    @FXML private TableColumn<Producto, Integer> tcUnidades;
    @FXML private TableColumn<Producto, Double> tcPrecio;
    @FXML private TextField totalTf;

    // Lista observable que mantiene los productos
    private final ObservableList<Producto> observableList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configuración de columnas
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcUnidades.setCellValueFactory(new PropertyValueFactory<>("unidades"));
        tcPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));

        // Asignar la lista a la tabla
        tablaProductos.setItems(observableList);

        // Escuchar cambios en la lista y actualizar el total automáticamente
        observableList.addListener((javafx.collections.ListChangeListener<Producto>) change -> actualizarTotal());
    }

    @FXML
    protected void agregar() {
        try {
            String nombre = nombreTf.getText().trim();
            int unidades = Integer.parseInt(unidadesTf.getText().trim());
            double precio = Double.parseDouble(precioTf.getText().trim().replace(",", "."));

            if (nombre.isEmpty()) {
                mostrarAlerta("Error", "El nombre no puede estar vacío.");
                return;
            }

            observableList.add(new Producto(nombre, unidades, precio));

            // Limpiar campos después de agregar
            nombreTf.clear();
            unidadesTf.clear();
            precioTf.clear();

        } catch (NumberFormatException e) {
            mostrarAlerta("Error de formato", "Ingrese valores numéricos válidos para unidades y precio.");
        }
    }

    // Método para actualizar el campo total
    private void actualizarTotal() {
        double total = observableList.stream()
                .mapToDouble(p -> p.getPrecio() * p.getUnidades())
                .sum();
        totalTf.setText(String.format("%.2f", total));
    }

    // Método auxiliar para mostrar alertas
    private void mostrarAlerta(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}

 