package modelo;

public class Teacher {
    int id;
    String nombre;

    public Teacher() {
    }

    public Teacher(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
