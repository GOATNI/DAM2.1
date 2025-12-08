import java.io.*;
import java.net.*;

/**
 * Ejercicio 1 - 2.5 puntos
 * Clase que se conecta al servidor EchoServer mediante socket TCP
 * y envía una señal de parada (carácter "*")
 */
public class EchoStopper {
    private static final String HOST = "localhost";
    private static final int PORT = 5000;

    public void detenerServidor() {
        System.out.println("Conectando a EchoServer para enviar señal de parada...");

        try (
                Socket socket = new Socket(HOST, PORT);
                PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))
        ) {
            // Enviar señal de parada
            out.println("*");
            System.out.println("Señal de parada '*' enviada");

            // Recibir datos de uso del servidor
            String respuesta = in.readLine();
            System.out.println("\nDatos de uso recibidos del servidor:");
            System.out.println(respuesta);

        } catch (UnknownHostException e) {
            System.err.println("No se pudo encontrar el host: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de I/O: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Esperar un poco para que el usuario pueda iniciar el servidor primero
        System.out.println("EchoStopper iniciado");
        System.out.println("Asegúrate de que EchoServer esté ejecutándose...");

        try {
            Thread.sleep(2000); // Esperar 2 segundos
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        EchoStopper stopper = new EchoStopper();
        stopper.detenerServidor();
    }
}