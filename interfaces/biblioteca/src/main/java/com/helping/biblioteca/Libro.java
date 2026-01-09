package com.helping.biblioteca;

public class Libro {
    private int id;
    private String titulo;
    private String autor;
    private String isbn;
    private int paginas;
    private String genero;
    private boolean disponible;

    public Libro(int id, String titulo, String autor, String isbn, int paginas, String genero, boolean disponible) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.isbn = isbn;
        this.paginas = paginas;
        this.genero = genero;
        this.disponible = disponible;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getAutor() { return autor; }
    public String getIsbn() { return isbn; }
    public int getPaginas() { return paginas; }
    public String getGenero() { return genero; }
    public boolean isDisponible() { return disponible; }
}
