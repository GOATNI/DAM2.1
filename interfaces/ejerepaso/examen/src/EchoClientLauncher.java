import java.util.ArrayList;
import java.util.List;

/**
 * Ejercicio 3 - 2.5 puntos
 * Clase que lanza 100 instancias de EchoClient
 * Cada instancia envía 100 mensajes al servidor
 * Total esperado: 10,000 mensajes
 */
public class EchoClientLauncher {
    private static final int NUM_CLIENTES = 100;
    private static final int MENSAJES_POR_CLIENTE = 100;

    public void lanzarClientes() {
        System.out.println("=== EchoClientLauncher ===");
        System.out.println("Lanzando " + NUM_CLIENTES + " clientes...");
        System.out.println("Cada cliente enviará " + MENSAJES_POR_CLIENTE + " mensajes");
        System.out.println("Total esperado: " + (NUM_CLIENTES * MENSAJES_POR_CLIENTE) + " mensajes\n");

        List<Thread> threads = new ArrayList<>();
        long startTime = System.currentTimeMillis();

        // Crear y lanzar threads para cada cliente
        for (int i = 1; i <= NUM_CLIENTES; i++) {
            final String clientId = "Cliente-" + i;

            Thread thread = new Thread(() -> {
                EchoClient client = new EchoClient(clientId);
                client.enviarMensajes(MENSAJES_POR_CLIENTE);
            });

            threads.add(thread);
            thread.start();

            // Pequeña pausa cada 10 clientes para no saturar el sistema
            if (i % 10 == 0) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        // Esperar a que todos los threads terminen
        System.out.println("\nEsperando a que todos los clientes completen...");
        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                System.err.println("Thread interrumpido: " + e.getMessage());
            }
        }

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("\n=== Proceso completado ===");
        System.out.println("Tiempo total: " + duration + " ms (" + (duration/1000.0) + " segundos)");
        System.out.println("Todos los clientes han terminado de enviar mensajes");
        System.out.println("\nAhora puedes ejecutar EchoStopper para ver las estadísticas del servidor");
    }

    public static void main(String[] args) {
        System.out.println("Asegúrate de que EchoServer esté ejecutándose antes de continuar");
        System.out.println("Presiona Enter para continuar...");

        try {
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }

        EchoClientLauncher launcher = new EchoClientLauncher();
        launcher.lanzarClientes();
    }
}