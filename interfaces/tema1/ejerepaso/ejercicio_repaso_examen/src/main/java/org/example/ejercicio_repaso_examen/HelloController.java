package org.example.ejercicio_repaso_examen;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class HelloController {
    private static final String url = "jdbc:mysql://localhost:3306/piezas";
    private static final String user = "root";
    private static final String clave = "1234";
    private  static ResultSet rs = null;
    private static final Connection con;

    static {
        try {
            con = DriverManager.getConnection(url, user, clave);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static final Statement stat;

    static {
        try {
            stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @FXML
    private TextField Idtf;
    @FXML
    private TextField Nombretf;
    @FXML
    private TextField Preciotf;
    @FXML
    private  Button añadirbtn;
    @FXML
    private Button showbtn;

    @FXML
    protected void show() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Tableviews.class.getResource("tableviews.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("tabla!");
        stage.setScene(scene);
        stage.show();
        


    }
    @FXML
    private  void añadir() {
        if (!Idtf.getText().isEmpty() && !Nombretf.getText().isEmpty() && !Preciotf.getText().isEmpty()) {
            Piezas pieza = new Piezas(Integer.parseInt(Idtf.getText().toString()), Float.parseFloat(Preciotf.getText().toString()), Nombretf.getText().toString());
            String sql = "INSERT INTO piezas (Id, Precio, Nombre) VALUES (?, ?, ?)";

            try (PreparedStatement pstmt = con.prepareStatement(sql)) {

                pstmt.setInt(1, pieza.getId());
                pstmt.setFloat(2, pieza.getPrecio());
                pstmt.setString(3, pieza.getNombre());


                int rowsAffected = pstmt.executeUpdate();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }


        }
    }
}