package org.example;

import java.util.Scanner;
public class Main {
    private static void testHttpClient() {
// Añadimos el sitio de confianza
        SampleHttpClient.TrustedSites.add("https://iesch.org");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter the address of the web resource");
            String address = scanner.nextLine();
// Llamada al HttpClient
            System.out.println(SampleHttpClient.get(address));
            System.out.println("Press ENTER to continue...");
            scanner.nextLine();
// Limpiar consola (no es estándar en Java, se simula)
            clearConsole();
        }
    }

    private static void clearConsole() {
        try {
            if (System.getProperty("os.name").toLowerCase().contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls")
                        .inheritIO()
                        .start()
                        .waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
// Ignorar errores
        }
    }
    public static void main(String[] args) {
        testHttpClient();
    }
}