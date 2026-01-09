package com.helping.biblioteca;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HelloController {
    @FXML
    private Label idLabel;
    @FXML
    private Label tituloLabel;
    @FXML
    private Label autorLabel;
    @FXML
    private Label isbnLabel;
    @FXML
    private Label paginasLabel;
    @FXML
    private Label generoLabel;
    @FXML
    private Label disponibleLabel;
    @FXML
    private Button prevButton;
    @FXML
    private Button nextButton;

    private List<Libro> libros = new ArrayList<>();
    private int currentIndex = 0;

    @FXML
    public void initialize() {
        loadLibros();
        showLibro(currentIndex);
    }

    private void loadLibros() {
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM libros")) {

            while (rs.next()) {
                libros.add(new Libro(
                        rs.getInt("Id"),
                        rs.getString("Titulo"),
                        rs.getString("Autor"),
                        rs.getString("ISBN"),
                        rs.getInt("Paginas"),
                        rs.getString("Genero"),
                        rs.getBoolean("Disponible")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Database Error", "Could not load books: " + e.getMessage());
        }
    }

    private void showLibro(int index) {
        if (index >= 0 && index < libros.size()) {
            Libro libro = libros.get(index);
            idLabel.setText(String.valueOf(libro.getId()));
            tituloLabel.setText(libro.getTitulo());
            autorLabel.setText(libro.getAutor());
            isbnLabel.setText(libro.getIsbn());
            paginasLabel.setText(String.valueOf(libro.getPaginas()));
            generoLabel.setText(libro.getGenero());
            disponibleLabel.setText(libro.isDisponible() ? "Sí" : "No");
            
            prevButton.setDisable(index == 0);
            nextButton.setDisable(index == libros.size() - 1);
        } else {
             // Clear labels if no books
            idLabel.setText("");
            tituloLabel.setText("No books found");
            autorLabel.setText("");
            isbnLabel.setText("");
            paginasLabel.setText("");
            generoLabel.setText("");
            disponibleLabel.setText("");
            prevButton.setDisable(true);
            nextButton.setDisable(true);
        }
    }

    @FXML
    protected void onPrevButtonClick() {
        if (currentIndex > 0) {
            currentIndex--;
            showLibro(currentIndex);
        }
    }

    @FXML
    protected void onNextButtonClick() {
        if (currentIndex < libros.size() - 1) {
            currentIndex++;
            showLibro(currentIndex);
        }
    }

    @FXML
    protected void onShowGenerosClick() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("generos-view.fxml"));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle("Listado de Géneros");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not open genres view.");
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
