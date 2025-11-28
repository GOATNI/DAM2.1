import java.io.PrintWriter;
import java.net.Socket;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Scanner;
public class clienteside {
    private final static String STOP = ".";
    public static void main(String[] args) {
        try {

            String host = args[0];
            int port = Integer.parseInt(args[1]);


            try(Socket cli = new Socket(host, port);
                PrintWriter out = new PrintWriter(cli.getOutputStream(), true);
                Scanner in = new Scanner(cli.getInputStream());
                Scanner sc = new Scanner(System.in)
            ){

                System.out.println("Connected to "
                        + cli.getInetAddress() + " : " + cli.getPort());
                System.out.println("select one option" +
                        " \n1 listar imagenes  " +
                        "\n2 descargar la imagen solicitada " +
                        "\n3 salir pulsa a el punto para salir (.)");

                while(!sc.hasNextInt()) {
                    String s = sc.nextLine();

                    if(s.equals(STOP)) {
                        out.println(STOP);
                        System.out.println("Server ordered to stop");
                        System.out.println("Disconnected");
                        return;
                    } else {

                        System.out.println("Enter the number");
                    }
                }

                int number = sc.nextInt();
                sc.nextLine();

                String cube = in.next();
                // mostrar el resultado por consola y terminar
                System.out.println("las imagenes disponibles son " + cube);
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Disconnected");
        } catch (Exception e) {
            System.out.println("Missing server info");
        }
    }
}
