package eje4;

public class Persona {
    String nombbre;
    int Edad;

    public Persona(String nombbre, int edad) {
        this.nombbre = nombbre;
        Edad = edad;
    }

    public String getNombbre() {
        return nombbre;
    }

    public void setNombbre(String nombbre) {
        this.nombbre = nombbre;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombbre='" + nombbre + '\'' +
                ", Edad=" + Edad +
                '}';
    }
}
