package com.helping.biblioteca;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DbInitializer {
    public static void initialize() {
        // Connect to MySQL server (without specifying database) to create it if needed
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "1234"; // Using the password found in DatabaseConnection

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {
            
            // Create Database
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS biblioteca");
            stmt.executeUpdate("USE biblioteca");

            // Create Genero table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS genero (" +
                    "Id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "Nombre VARCHAR(255) NOT NULL)");

            // Create Libros table
            stmt.executeUpdate("CREATE TABLE IF NOT EXISTS libros (" +
                    "Id INT AUTO_INCREMENT PRIMARY KEY, " +
                    "Titulo VARCHAR(255) NOT NULL, " +
                    "Autor VARCHAR(255) NOT NULL, " +
                    "ISBN VARCHAR(20), " +
                    "Paginas INT, " +
                    "Genero VARCHAR(255), " +
                    "Disponible TINYINT DEFAULT 1)");

            // Check if data exists, if not insert sample data
            if (stmt.executeQuery("SELECT COUNT(*) FROM genero").next() && stmt.getResultSet().getInt(1) == 0) {
                stmt.executeUpdate("INSERT INTO genero (Nombre) VALUES ('Ficción'), ('No Ficción'), ('Ciencia'), ('Historia')");
            }

            if (stmt.executeQuery("SELECT COUNT(*) FROM libros").next() && stmt.getResultSet().getInt(1) == 0) {
                stmt.executeUpdate("INSERT INTO libros (Titulo, Autor, ISBN, Paginas, Genero, Disponible) VALUES " +
                        "('El Quijote', 'Miguel de Cervantes', '123456789', 863, 'Ficción', 1), " +
                        "('Breve historia del tiempo', 'Stephen Hawking', '987654321', 256, 'Ciencia', 1)");
            }
            
            System.out.println("Database initialized successfully.");

        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error initializing database: " + e.getMessage());
        }
    }
}
