package modelo;

public class Student implements Comparable<Student>{
    int id;
    String nombre;

    public Student (){

    }
    public Student(int id, String nombre) {
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
        return "Student{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    public int compareTo(Student o) {
        if (this.o.nombre > that.nombre)
    }
}

