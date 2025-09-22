package ejercicios_repaso_dia1.eje7.eje9.modelo;

public class Estudiante {
    String nombre;
    int id;
    String apellido;
    String curso;

    public Estudiante() {
    }

    public Estudiante(String nombre, int id, String apellido, String curso) {
        this.nombre = nombre;
        this.id = id;
        this.apellido = apellido;
        this.curso = curso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "Estudiante{" +
                "nombre='" + nombre + '\'' +
                ", id=" + id +
                ", apellido='" + apellido + '\'' +
                ", curso='" + curso + '\'' +
                '}';
    }
}
