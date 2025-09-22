public class start {
    public static void main(String[] args) {

        Contenedor<String> stringContenedor = new Contenedor<>("Hola mundo");

        Contenedor<Integer> IntegerContenedor = new Contenedor<>(68);

        imprimr(stringContenedor);
        imprimr(IntegerContenedor);
        imprimirNumerodouble(IntegerContenedor);
        imprimirConmultiplicacion(IntegerContenedor,9);



    }

    private static void imprimirConmultiplicacion(Contenedor<Integer> c,int multiplicador) {
        System.out.println(c.getObjeto().intValue()*multiplicador);
    }

    private static void imprimirNumerodouble(Contenedor<Integer> c) {
        System.out.println(c.getObjeto().doubleValue()*2);
    }

    private static void imprimr(Contenedor<?> C) {
        System.out.println(C.getObjeto());
    }
}
class Contenedor<T>{
    private T objeto;

    public Contenedor() {
    }

    public Contenedor(T objeto) {
        this.objeto = objeto;
    }

    public T getObjeto() {
        return objeto;
    }

    public void setObjeto(T objeto) {
        this.objeto = objeto;
    }
}