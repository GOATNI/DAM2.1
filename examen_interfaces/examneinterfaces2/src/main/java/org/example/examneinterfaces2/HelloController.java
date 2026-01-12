package org.example.examneinterfaces2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class HelloController {
    @FXML
    TextField idtf;
    @FXML
    TextField nombretf;
    @FXML
    TextField Preciotf;
    @FXML
    ComboBox<String> categoriacb;
    @FXML
    ComboBox<String> Marcacb;
    @FXML
    RadioButton nuevorb;
    @FXML
    RadioButton recondicionadorb;
    @FXML
    CheckBox wifick;
    @FXML
    CheckBox bluetoothck;
    @FXML
    CheckBox NFcck;
    @FXML
    CheckBox g5;

    private static final String url = "jdbc:mysql://localhost:3306/productos";
    private static final String user = "root";
    private static final String clave = "1234";

    private static Connection con;
    private static Statement stat;
    private static ResultSet rs;

    static {
        try {
            con = DriverManager.getConnection(url, user, clave);
            stat = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );
            rs = stat.executeQuery(
                    "select p.Id,p.Producto,p.Precio,p.Wifi,p.Bluetooth,p.NFC,p.`5G`,p.Estado,m.Marca,p.categoria from productos p\n" +
                            "join marcas m\n" +
                            "on p.Marca = m.id\n"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private ObservableList<String> Categoria = FXCollections.observableArrayList();
    private ObservableList<String> Marca = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws SQLException {

        try (Statement tempStat = con.createStatement();
             ResultSet rsCompanies = tempStat.executeQuery("select categoria from categorias")) {

            while (rsCompanies.next()) {
                Categoria.add(rsCompanies.getString("categoria"));
            }
            categoriacb.setItems(Categoria);
        }
        try (Statement tempStat = con.createStatement();
             ResultSet rsCompanies = tempStat.executeQuery("select  marca from marcas")) {

            while (rsCompanies.next()) {
                Marca.add(rsCompanies.getString("marca"));
            }
            Marcacb.setItems(Marca);
        }


        if (rs.next()) {
            loadPersonFromResultSet();
        }
    }

    private void loadPersonFromResultSet() throws SQLException {
        idtf.setText(String.valueOf(rs.getInt("Id")));
        nombretf.setText(rs.getString("Producto"));
        Preciotf.setText(String.valueOf(rs.getDouble("Precio")));

        // Gender
        int estado = rs.getInt("Estado");
        if (estado == 0) {
            nuevorb.setSelected(true);
        } else if (estado == 1) {
            recondicionadorb.setSelected(true);
        }

        int wifi = rs.getInt("Wifi");
        int bluetooth = rs.getInt("Bluetooth");
        int nfc = rs.getInt("NFC");
        int fiveg = rs.getInt("5G");
        if (wifi == 1){
            wifick.setSelected(true);
        }else
            wifick.setSelected(false);

        if (bluetooth== 1){
            bluetoothck.setSelected(true);
        }else
            bluetoothck.setSelected(false);


        if (nfc == 1){
            NFcck.setSelected(true);
        }else
            NFcck.setSelected(false);


        if (fiveg == 1){
            g5.setSelected(true);
        }else
            g5.setSelected(false);

        // Company preselection
        String productomarca = rs.getString("m.Marca");
        if (Marca.contains(productomarca)) {
            Marcacb.setValue(productomarca);
        }

        categoriacb.setValue(Categoria.get(rs.getInt("p.categoria")-1));
    }
    public void next() throws SQLException {
        if (rs.next()) {
            loadPersonFromResultSet();
        }
    }

    public void previous() throws SQLException {
        if (rs.previous()) {
            loadPersonFromResultSet();
        }
    }

    public void first() throws SQLException {
        if (rs.first()) {
            loadPersonFromResultSet();
        }
    }

    public void last() throws SQLException {
        if (rs.last()) {
            loadPersonFromResultSet();
        }
    }

    public void clear(){
        idtf.clear();
        idtf.setDisable(true);
        nombretf.clear();
        Preciotf.clear();
        categoriacb.setValue(null);
        Marcacb.setValue(null);
        wifick.setSelected(false);
        NFcck.setSelected(false);
        bluetoothck.setSelected(false);
        nuevorb.setSelected(false);
        recondicionadorb.setSelected(false);
        g5.setSelected(false);
    }
    @FXML
    public void listados() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("table.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }



    @FXML
    public void add() {

        // 1. Validation: Check if a company is selected
        int selectedIdx = categoriacb.getSelectionModel().getSelectedIndex();
        int CategoriaId = selectedIdx + 1;
        int selectedIdx2 = Marcacb.getSelectionModel().getSelectedIndex();
        int MarcaId = selectedIdx + 1;

        // 2. Collect and FIX casing for ENUM
        String fullname = nombretf.getText();
        double precio = Double.parseDouble(Preciotf.getText()) ;
        int Estado=0; // Default with Capital O
        if (recondicionadorb.isSelected()) Estado = 1; // Capital F
        int wifi=0;
        if (wifick.isSelected()) wifi = 1;
        int bluetooth=0;
        if (bluetoothck.isSelected()) bluetooth = 1;
        int nfc=0;
        if (NFcck.isSelected()) nfc = 1;
        int g51=0;
        if (g5.isSelected()) g51=1;// Capital M

        // 3. SQL Insert
        String sqlInsert = "INSERT INTO productos (Producto, Precio,Categoria,Marca,Wifi,Bluetooth,NFC,5G,Estado) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sqlInsert)) {
            pstmt.setString(1, fullname);
            pstmt.setDouble(2, precio);
            pstmt.setInt(3,CategoriaId);
            pstmt.setInt(4,MarcaId);
            pstmt.setInt(5,wifi);
            pstmt.setInt(6,bluetooth);
            pstmt.setInt(7,nfc);
            pstmt.setInt(8,g51);
            pstmt.setInt(9,Estado);


            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                System.out.println("SUCCESS: Employee added to database!");
                refreshResultSet();
            }
        } catch (SQLException e) {
            System.err.println("SQL Error Code: " + e.getErrorCode());
            e.printStackTrace(); // This will tell you if the ENUM or FOREIGN KEY failed
        }
    }

    private void refreshResultSet() throws SQLException {
        // Re-execute the main query
        rs = stat.executeQuery(

                "select p.Id,p.Producto,p.Precio,p.Wifi,p.Bluetooth,p.NFC,p.`5G`,p.Estado,m.Marca,p.categoria from productos p\n" +
                        "join marcas m\n" +
                        "on p.Marca = m.id\n"
        );

        rs.last();
        loadPersonFromResultSet();
    }



}