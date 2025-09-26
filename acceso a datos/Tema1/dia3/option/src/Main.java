import java.util.Optional;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Optional<Double> resultado = calcularmedia(5d,6d,7d,8d,9d,40d,10d);

        if(resultado.isPresent()) {
            Double resultadomedia = resultado.get();
            System.out.println(resultadomedia);
        }else System.out.println("no hay resultado");
    }

    private static Optional<Double> calcularmedia(Double ...notas) {
        if (notas.length==0){
            return Optional.empty();
        }else {
            double sum = 0;
            for (Double nota : notas) sum += nota;
            return Optional.of(sum/notas.length) ;
        }
    }
}