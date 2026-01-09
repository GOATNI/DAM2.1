CREATE DATABASE IF NOT EXISTS biblioteca;
USE biblioteca;

CREATE TABLE IF NOT EXISTS genero (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS libros (
    Id INT AUTO_INCREMENT PRIMARY KEY,
    Titulo VARCHAR(255) NOT NULL,
    Autor VARCHAR(255) NOT NULL,
    ISBN VARCHAR(20),
    Paginas INT,
    Genero VARCHAR(255), -- Or INT if Foreign Key
    Disponible TINYINT DEFAULT 1
);

-- Insert sample data
INSERT INTO genero (Nombre) VALUES ('Ficción'), ('No Ficción'), ('Ciencia'), ('Historia');

INSERT INTO libros (Titulo, Autor, ISBN, Paginas, Genero, Disponible) VALUES
('El Quijote', 'Miguel de Cervantes', '123456789', 863, 'Ficción', 1),
('Breve historia del tiempo', 'Stephen Hawking', '987654321', 256, 'Ciencia', 1);
