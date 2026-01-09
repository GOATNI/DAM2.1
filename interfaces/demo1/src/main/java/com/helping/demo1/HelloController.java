package com.helping.demo1;

import com.helping.demo1.modelo.empleado;
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

public class HelloController {

    private static final String url = "jdbc:mysql://localhost:3306/javafx_exam";
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
                    "SELECT e.employee_id, e.full_name, e.email, e.phone, e.date_of_birth, " +
                            "e.gender, e.knows_java, e.knows_python, e.knows_sql, e.is_full_time, " +
                            "c.company_name " +
                            "FROM employee e " +
                            "JOIN company c ON e.company_id = c.company_id"
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    private TextField idField;
    @FXML
    private TextField nameField;
    @FXML
    private TextField gmailField;
    @FXML
    private TextField phoneField;
    @FXML
    private DatePicker birthDatePicker;
    @FXML
    private ComboBox<String> companyComboBox;
    @FXML
    private ToggleGroup genderGroup;
    @FXML
    private RadioButton maleRadio;
    @FXML
    private RadioButton femaleRadio;
    @FXML
    private RadioButton otherRadio;
    @FXML
    private CheckBox knowsJavaCheck;
    @FXML
    private CheckBox knowsPythonCheck;
    @FXML
    private CheckBox knowsSqlCheck;
    @FXML
    private CheckBox fullTimeCheck;

    private ObservableList<String> companies = FXCollections.observableArrayList();

    @FXML
    public void initialize() throws SQLException {

        try (Statement tempStat = con.createStatement();
             ResultSet rsCompanies = tempStat.executeQuery("SELECT company_name FROM company")) {

            while (rsCompanies.next()) {
                companies.add(rsCompanies.getString("company_name"));
            }
            companyComboBox.setItems(companies);
        }


        if (rs.next()) {
            loadPersonFromResultSet();
        }
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

    private void loadPersonFromResultSet() throws SQLException {
        idField.setText(String.valueOf(rs.getInt("employee_id")));
        nameField.setText(rs.getString("full_name"));
        gmailField.setText(rs.getString("email"));
        phoneField.setText(rs.getString("phone"));
        Date dob = rs.getDate("date_of_birth");
        if (dob != null) {
            birthDatePicker.setValue(dob.toLocalDate());
        }

        // Gender
        String gender = rs.getString("gender");
        if ("male".equalsIgnoreCase(gender)) {
            maleRadio.setSelected(true);
        } else if ("female".equalsIgnoreCase(gender)) {
            femaleRadio.setSelected(true);
        } else {
            otherRadio.setSelected(true);
        }

        // Skills / checkboxes
        knowsJavaCheck.setSelected(rs.getBoolean("knows_java"));
        knowsPythonCheck.setSelected(rs.getBoolean("knows_python"));
        knowsSqlCheck.setSelected(rs.getBoolean("knows_sql"));
        fullTimeCheck.setSelected(rs.getBoolean("is_full_time"));

        // Company preselection
        String personCompany = rs.getString("company_name");
        if (companies.contains(personCompany)) {
            companyComboBox.setValue(personCompany);
        }
    }
    @FXML
    public void clear() {
        idField.setDisable(true);
        // Clear TextFields
        idField.clear();
        nameField.clear();
        gmailField.clear();
        phoneField.clear();

        // Reset DatePicker
        birthDatePicker.setValue(null);

        // Reset ComboBox
        companyComboBox.setValue(null);

        // Unselect RadioButtons (using the ToggleGroup)
        maleRadio.setSelected(false);
        femaleRadio.setSelected(false);
        otherRadio.setSelected(false);

        // Uncheck CheckBoxes
        knowsJavaCheck.setSelected(false);
        knowsPythonCheck.setSelected(false);
        knowsSqlCheck.setSelected(false);
        fullTimeCheck.setSelected(false);
    }

    @FXML
    public void listados() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("table-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Stage stage = new Stage();
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }
    @FXML
    public void add() {

        // 1. Validation: Check if a company is selected
        int selectedIdx = companyComboBox.getSelectionModel().getSelectedIndex();
        int companyId = selectedIdx + 1;

        // 2. Collect and FIX casing for ENUM
        String fullname = nameField.getText();
        String email = gmailField.getText();
        String phone = phoneField.getText();
        LocalDate date = birthDatePicker.getValue();

        String gender = "Other"; // Default with Capital O
        if (femaleRadio.isSelected()) gender = "Female"; // Capital F
        else if (maleRadio.isSelected()) gender = "Male";   // Capital M

        // 3. SQL Insert
        String sqlInsert = "INSERT INTO employee (full_name, email, phone, date_of_birth, " +
                "gender, knows_java, knows_python, knows_sql, is_full_time, company_id) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = con.prepareStatement(sqlInsert)) {
            pstmt.setString(1, fullname);
            pstmt.setString(2, email);
            pstmt.setString(3, phone);

            // Date check
           pstmt.setDate(4,Date.valueOf(date));

            pstmt.setString(5, gender);
            pstmt.setBoolean(6, knowsJavaCheck.isSelected());
            pstmt.setBoolean(7, knowsPythonCheck.isSelected());
            pstmt.setBoolean(8, knowsSqlCheck.isSelected());
            pstmt.setBoolean(9, fullTimeCheck.isSelected());
            pstmt.setInt(10, companyId);

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
                "SELECT e.employee_id, e.full_name, e.email, e.phone, e.date_of_birth, " +
                        "e.gender, e.knows_java, e.knows_python, e.knows_sql, e.is_full_time, " +
                        "c.company_name " +
                        "FROM employee e " +
                        "JOIN company c ON e.company_id = c.company_id"
        );

        rs.last();
        loadPersonFromResultSet();
    }
}

