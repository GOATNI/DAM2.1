import java.io.PrintWriter;
import java.net.Socket;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
public class CubeCli {
    private final static String STOP = ".";
    public static void main(String[] args) {
        try {
//obtener los datos de la conexión
// al servidor de la línea de comandos
            String host = args[0];
            int port = Integer.parseInt(args[1]);
// solicitamos conexión al servidor
// abrimos los stream de entrada y salida
            try(Socket cli = new Socket(host, port);
                PrintWriter out = new PrintWriter(cli.getOutputStream(), true);
                Scanner in = new Scanner(cli.getInputStream());
                Scanner sc = new Scanner(System.in)
            ){
// mostrar información de la conexión
                System.out.println("Connected to "
                        + cli.getInetAddress() + " : " + cli.getPort());
                System.out.println("Enter number (double)");
// validar la entrada del usuario
                while(!sc.hasNextDouble()) {
                    String s = sc.nextLine();
// un punto es la señal para que el servidor se pare
                    if(s.equals(STOP)) {
                        out.println(STOP);
                        System.out.println("Server ordered to stop");
                        System.out.println("Disconnected");
                        return;
                    } else {
// si no pedir un número para calcular el cubo
                        System.out.println("Enter number (double)");
                    }
                }
// recoger el número
                double number = sc.nextDouble();
                sc.nextLine();
// formatear el número para enviarlo al servidor
// Scanner trabaja con el local de la máquina
                NumberFormat nf = NumberFormat.getInstance(new Locale("es", "ES"));
// enviar al servidor el número correctamente formateado
                out.println(nf.format(number));
// recoger la información devuelta por el servidor
                Double cube = in.nextDouble();
// mostrar el resultado por consola y terminar
                System.out.println("Cube of " + number + " is " + cube);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Disconnected");
        } catch (Exception e) {
            System.out.println("Missing server info");
        }
    }
}