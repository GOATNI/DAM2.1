package calculadora;
@FunctionalInterface
interface Calculator {
    int calculate(int a, int b);
}


public class calculos {
    public static void main(String[] args) {


        Calculator suma = (a, b) -> a + b;
        Calculator restar = (a, b) -> a - b;
        Calculator mult = (a, b) -> a * b;

        Calculator divison = (a, b) -> {
            if (b == 0) {
                System.out.println("no se puede divivir entre 0");
                return 0;
            }
            return (a/b);
        };
        int a = 60;
        int b = 0;
        System.out.println(suma.calculate(a,b));
        System.out.println(restar.calculate(a,b));
        System.out.println(mult.calculate(a,b));
        System.out.println(divison.calculate(a,b));

    }
}