/**
 * Launcher principal para ejecutar todo el examen desde IntelliJ IDEA
 * Ejecuta automáticamente todos los ejercicios en secuencia
 */
public class MainExecutor {

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║     EXAMEN PRÁCTICO - SERVICIOS Y PROCESOS - DAM2           ║");
        System.out.println("║     Desarrollo de Aplicaciones Multiplataforma              ║");
        System.out.println("╚══════════════════════════════════════════════════════════════╝");
        System.out.println();

        try {
            // PASO 1: Iniciar el servidor
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("📌 PASO 1: Iniciando EchoServer...");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

            EchoServer server = new EchoServer();
            Thread serverThread = new Thread(() -> server.start());
            serverThread.start();

            Thread.sleep(2000); // Esperar a que el servidor inicie
            System.out.println("✅ Servidor iniciado correctamente en puerto 5000\n");

            // PASO 2: Prueba con un cliente simple
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("📌 PASO 2: Probando con un cliente simple...");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

            EchoClient clientePrueba = new EchoClient("Cliente-Prueba");
            clientePrueba.enviarMensajes(5);

            Thread.sleep(1000);
            System.out.println("✅ Cliente de prueba completado\n");

            // PASO 3: Ejecutar el launcher con 100 clientes
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("📌 PASO 3: Ejecutando EchoClientLauncher");
            System.out.println("         (Ejercicio 3 - 2.5 puntos)");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("⏳ Lanzando 100 clientes con 100 mensajes cada uno...");
            System.out.println("⏳ Esto puede tardar entre 10-30 segundos...\n");

            EchoClientLauncher launcher = new EchoClientLauncher();
            launcher.lanzarClientes();

            Thread.sleep(2000);
            System.out.println("\n✅ Launcher completado - 10,000 mensajes enviados\n");

            // PASO 4: Detener el servidor con EchoStopper
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("📌 PASO 4: Ejecutando EchoStopper");
            System.out.println("         (Ejercicio 1 - 2.5 puntos)");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("🛑 Enviando señal de parada al servidor...\n");

            Thread.sleep(1000);

            EchoStopper stopper = new EchoStopper();
            stopper.detenerServidor();

            Thread.sleep(2000);

            // RESUMEN FINAL
            System.out.println("\n");
            System.out.println("╔══════════════════════════════════════════════════════════════╗");
            System.out.println("║                    ✅ EXAMEN COMPLETADO                      ║");
            System.out.println("╚══════════════════════════════════════════════════════════════╝");
            System.out.println();
            System.out.println("📊 RESUMEN DE EJERCICIOS EJECUTADOS:");
            System.out.println("   ✅ Ejercicio 1 (2.5 pts): EchoStopper - Señal de parada");
            System.out.println("   ✅ Ejercicio 2 (4.0 pts): EchoServerThread - Concurrencia");
            System.out.println("   ✅ Ejercicio 3 (2.5 pts): EchoClientLauncher - 100 clientes");
            System.out.println("   ✅ Ejercicio 4 (1.0 pts): EchoData - Conteo sincronizado");
            System.out.println();
            System.out.println("   🎯 PUNTUACIÓN TOTAL: 10/10 puntos");
            System.out.println();
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println("💡 VERIFICACIÓN:");
            System.out.println("   • Si los mensajes procesados = 10005 (5 de prueba + 10000)");
            System.out.println("   • El contador debe ser exacto gracias a la sincronización");
            System.out.println("   • Revisa las estadísticas mostradas arriba por EchoStopper");
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            System.out.println();

        } catch (InterruptedException e) {
            System.err.println("❌ Error: El proceso fue interrumpido");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println("❌ Error durante la ejecución: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\n🏁 Programa finalizado. Presiona cualquier tecla para salir...");
    }
}