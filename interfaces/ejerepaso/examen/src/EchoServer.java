import java.io.*;
import java.net.*;

/**
 * Servidor Echo que recibe mensajes y los devuelve al cliente
 */
public class EchoServer {
    private static final int PORT = 5000;
    private ServerSocket serverSocket;
    private EchoData echoData;
    private volatile boolean running;

    public EchoServer() {
        this.echoData = new EchoData();
        this.running = true;
    }

    public void start() {
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("EchoServer iniciado en puerto " + PORT);

            while (running) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    System.out.println("Cliente conectado: " + clientSocket.getInetAddress());

                    // Crear un thread para cada cliente
                    EchoServerThread thread = new EchoServerThread(clientSocket, echoData, this);
                    thread.start();

                } catch (SocketException e) {
                    if (!running) {
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        } finally {
            stop();
        }
    }

    public void stop() {
        running = false;
        try {
            if (serverSocket != null && !serverSocket.isClosed()) {
                serverSocket.close();
            }
            echoData.setEndTime();
            System.out.println("\nServidor detenido.");
            System.out.println(echoData);
        } catch (IOException e) {
            System.err.println("Error al cerrar servidor: " + e.getMessage());
        }
    }

    public EchoData getEchoData() {
        return echoData;
    }

    public static void main(String[] args) {
        EchoServer server = new EchoServer();
        server.start();
    }
}