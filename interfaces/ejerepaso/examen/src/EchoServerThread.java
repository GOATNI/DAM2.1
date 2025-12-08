import java.io.*;
import java.net.*;

/**
 * Thread para atender peticiones de clientes de forma concurrente
 * Ejercicio 2 - 4 puntos
 */
public class EchoServerThread extends Thread {
    private Socket clientSocket;
    private EchoData echoData;
    private EchoServer server;

    public EchoServerThread(Socket socket, EchoData echoData, EchoServer server) {
        this.clientSocket = socket;
        this.echoData = echoData;
        this.server = server;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String mensaje;

            while ((mensaje = in.readLine()) != null) {
                // Si recibe "*", es señal de parada
                if (mensaje.equals("*")) {
                    System.out.println("Señal de parada recibida");
                    out.println(echoData.toString());
                    server.stop();
                    break;
                }

                // Hacer eco del mensaje
                out.println(mensaje);

                // Registrar el mensaje en EchoData de forma sincronizada
                echoData.addMessage(mensaje);
            }

        } catch (IOException e) {
            System.err.println("Error en thread del cliente: " + e.getMessage());
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Error al cerrar socket: " + e.getMessage());
            }
        }
    }
}