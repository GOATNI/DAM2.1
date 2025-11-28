import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.*;

public class Serverside {
    private final static String STOP = ".";
    private Map<String,String> imagens = new HashMap<>();


    public void main(String[] args) {

        imagens.put("Imagen1","imagen1.png");

        try {
// obtener el puerto de la linea de comando
            Integer port = Integer.parseInt(args[0]);
// arrancar el server
            try (ServerSocket svr = new ServerSocket(port);) {
                System.out.println("Server running at " + LocalDateTime.now());
                System.out.println("Server port: " + svr.getLocalPort());

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

                        if (in.hasNextInt()) {
                            int num = in.nextInt();
                            in.nextLine();

                            if (num==1){
                                imagens.values().forEach(System.out::println);
                            } else if (num==2) {
                                out.println("el nombre de la imagen");
                                if (in.hasNext()){
                                    String nombreimagen = in.next();
                                    if (imagens.keySet().contains(nombreimagen)){
                                        out.println(imagens.containsKey(nombreimagen));
                                    }

                                }
                            }

                        } else {
                            String s = in.nextLine();
                            if (s.equals(STOP)) {
                                break;
                            } else {
                                System.out.println("Bad input received.\u201D"
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

    private HashMap<String,String> getimages() {

        return (HashMap<String, String>) imagens;
    }
}
