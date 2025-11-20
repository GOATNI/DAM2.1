import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Scanner;
public class CubeSvr {
    private final static String STOP = ".";
    public static void main(String[] args) {
        try {
// obtener el puerto de la linea de comando
            Integer port = Integer.parseInt(args[0]);
// arrancar el server
            try (ServerSocket svr = new ServerSocket(port);) {
                System.out.println("Server running at " + LocalDateTime.now());
                System.out.println("Server port: " + svr.getLocalPort());
// aceptar peticiones en bucle
                while (true) {
// abrir socket de cliente
// y los stream de entrada y salida con el cliente
// acceptará un cliente a la vez
// al incluir los objetos en el try se cerrarán al
// finalizar el bloque
                    try (Socket cli = svr.accept();
                         PrintWriter out
                                 = new PrintWriter(cli.getOutputStream(), true);
                         Scanner in
                                 = new Scanner(cli.getInputStream());

                    ) {
// leemos el número del canal de entrada
                        if (in.hasNextDouble()) {
                            double num = in.nextDouble();
                            in.nextLine();
// calculamos el cubo
                            double cube = num * num * num;
// formateamos el número ya que Scanner
// trabaja con la configuración regional
//de la máquina
                            NumberFormat nf
                                    = NumberFormat.getInstance(new Locale("es", "ES"));
// escribimos el resultado
// en el canal de salida
                            out.println(nf.format(cube));
                        } else {
                            String s = in.nextLine();
                            if (s.equals(STOP)) {
                                break;
                            } else {
                                System.out.println("Bad input received."
                                        + cli.getInetAddress());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("Server stopped");
        } catch (Exception e) {
            System.out.println("Port information missing");
        }
    }
}