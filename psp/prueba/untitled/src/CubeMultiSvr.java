import java.net.ServerSocket;
import java.net.SocketTimeoutException;
import java.time.LocalDateTime;
public class CubeMultiSvr {
    final static String STOP = ".";
    public static void main(String[] args) {
        try {
            boolean[] stop = {false};
            // obtener el puerto de la linea de comando
            Integer port = Integer.parseInt(args[0]);
            // arrancar el server
            try (ServerSocket svr = new ServerSocket(port);) {
                //añadimos timeout para responder orden de parada
                svr.setSoTimeout(1000);
                System.out.println("Server running at " + LocalDateTime.now());
                System.out.println("Server port: " + svr.getLocalPort());
                // aceptar peticiones en bucle mientras
                // no llegue orden de parada
                while (!stop[0]) {
                // crear nuevo thread y
                    // asociarle el socket de cliente
                    try {
                        new Thread(new CubeMultiSvrThread(
                                svr.accept(), stop
                        )).start();

                    } catch (SocketTimeoutException e) {
// time out
                    }
                }
            }
            System.out.println("Server stopped");
        } catch (Exception e) {
            System.out.println("Port information missing");
        }
    }
}