package org.example.newslidertable;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class HelloController {

    @FXML private Slider sliderMin;
    @FXML private Slider sliderMax;

    @FXML private TableView<Provincia> tabla;
    @FXML private TableColumn<Provincia, String> colProvincia;
    @FXML private TableColumn<Provincia, String> colAutonomia;
    @FXML private TableColumn<Provincia, Number> colPoblacion;

    private ObservableList<Provincia> datos;

    @FXML
    public void initialize() {

        datos = FXCollections.observableArrayList(
                new Provincia("Alicante", "Comunidad Valenciana", 1880000),
                new Provincia("Málaga", "Andalucía", 1695000),
                new Provincia("Sevilla", "Andalucía", 1947000),
                new Provincia("Valencia", "Comunidad Valenciana", 2589000),
                new Provincia("Ávila", "Castilla y León", 158000),
                new Provincia("Almería", "Andalucía", 731000)
        );

        FilteredList<Provincia> filtrados = new FilteredList<>(datos);

        colProvincia.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getNombre()));
        colAutonomia.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getAutonomia()));
        colPoblacion.setCellValueFactory(c -> new SimpleIntegerProperty(c.getValue().getPoblacion()));

        tabla.setItems(filtrados);

        int min = datos.stream().mapToInt(Provincia::getPoblacion).min().getAsInt();
        int max = datos.stream().mapToInt(Provincia::getPoblacion).max().getAsInt();

        sliderMin.setMin(min);
        sliderMin.setMax(max);
        sliderMax.setMin(min);
        sliderMax.setMax(max);

        sliderMin.setValue(min);
        sliderMax.setValue(max);

        Runnable actualizar = () -> filtrados.setPredicate(
                p -> p.getPoblacion() >= sliderMin.getValue() &&
                        p.getPoblacion() <= sliderMax.getValue()
        );

        sliderMin.valueProperty().addListener((o, ov, nv) -> actualizar.run());
        sliderMax.valueProperty().addListener((o, ov, nv) -> actualizar.run());
    }
}
