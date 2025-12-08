import java.io.*;
import java.net.*;

/**
 * Cliente que envía mensajes al servidor EchoServer
 */
public class EchoClient {
    private static final String HOST = "localhost";
    private static final int PORT = 5000;
    private String clientId;

    public EchoClient(String clientId) {
        this.clientId = clientId;
    }

    public void enviarMensajes(int cantidad) {
        try (
                Socket socket = new Socket(HOST, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))
        ) {
            for (int i = 1; i <= cantidad; i++) {
                String mensaje = "Mensaje " + i + " desde " + clientId;
                out.println(mensaje);

                // Leer respuesta del servidor
                String respuesta = in.readLine();

                // Opcional: mostrar solo algunos mensajes para no saturar la consola
                if (i % 20 == 0) {
                    System.out.println(clientId + " - Enviado mensaje " + i + "/" + cantidad);
                }
            }

            System.out.println(clientId + " completó el envío de " + cantidad + " mensajes");

        } catch (IOException e) {
            System.err.println(clientId + " - Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        EchoClient client = new EchoClient("Cliente-Test");
        client.enviarMensajes(10);
    }
}