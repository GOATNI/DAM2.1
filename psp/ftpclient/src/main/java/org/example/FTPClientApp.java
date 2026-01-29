package org.example;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.net.SocketException;
import java.util.Scanner;

/**
 * Cliente FTP simplificado.
 *
 * - Host / usuario / contraseña hardcodeados en el código.
 * - Ruta local de subida hardcodeada en la constante HARDCODED_UPLOAD_PATH.
 * - Al pulsar "Iniciar" se conecta, lista el directorio y ofrece:
 *     1) Descargar archivo
 *     2) Subir archivo (usa ruta hardcodeada)
 *     3) Desconectar (volver al menú principal)
 */
public class FTPClientApp {

    // CONFIGURACIÓN HARDCODEADA (modifica aquí)
    private static final String HOST = "test.rebex.net";
    private static final int PORT = 21;
    private static final String USER = "demo";
    private static final String PASS = "password";
    // Ruta local que se usará siempre al subir (hardcodeada)
    private static final String UPLOAD_PATH = "./archivo_a_subir.txt";

    private FTPClient ftpClient = new FTPClient();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        FTPClientApp app = new FTPClientApp();
        app.run();
    }

    private void run() {
        System.out.println("=== CLIENTE FTP BÁSICO (HARDCODE) ===");

        while (true) {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1) Iniciar (conectar y listar)");
            System.out.println("0) Salir");
            System.out.print("Elige una opción: ");
            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    iniciarSesionYMenuAcciones();
                    break;
                case "0":
                    System.out.println("Saliendo...");
                    // Asegurar desconexión si queda alguna
                    if (ftpClient.isConnected()) {
                        desconectar();
                    }
                    return;
                default:
                    System.out.println("[ERROR] Opción no válida.");
            }
        }
    }

    private void iniciarSesionYMenuAcciones() {
        boolean conectado = conectarHardcoded();
        if (!conectado) {
            System.out.println("[ERROR] No se pudo conectar. Volviendo al menú principal.");
            return;
        }

        // Después de conectar, listar directorio
        listarDirectorio();

        // Submenú de acciones mientras esté conectado
        while (ftpClient.isConnected()) {
            System.out.println("\n--- Acciones ---");
            System.out.println("1) Descargar archivo del servidor");
            System.out.println("2) Subir archivo (" + UPLOAD_PATH + ")");
            System.out.println("3) Desconectar y volver al menú principal");
            System.out.print("Elige una opción: ");
            String opt = scanner.nextLine().trim();

            switch (opt) {
                case "1":
                    descargarArchivoInteractive();
                    break;
                case "2":
                    subirHardcoded();
                    break;
                case "3":
                    desconectar();
                    return;
                default:
                    System.out.println("[ERROR] Opción no válida.");
            }
        }
    }

    /**
     * Conecta usando los datos hardcodeados.
     */
    private boolean conectarHardcoded() {
        try {
            System.out.printf("[INFO] Conectando a %s:%d ...%n", HOST, PORT);
            ftpClient.connect(HOST, PORT);

            int reply = ftpClient.getReplyCode();
            System.out.println("[DEBUG] Respuesta servidor: " + ftpClient.getReplyString().trim());
            if (!FTPReply.isPositiveCompletion(reply)) {
                ftpClient.disconnect();
                System.out.println("[ERROR] El servidor rechazó la conexión. Código: " + reply);
                return false;
            }

            System.out.println("[INFO] Autenticando con usuario hardcodeado...");
            boolean loginOk = ftpClient.login(USER, PASS);
            System.out.println("[DEBUG] Respuesta servidor (login): " + ftpClient.getReplyString().trim());
            if (!loginOk) {
                ftpClient.logout();
                ftpClient.disconnect();
                System.out.println("[ERROR] Autenticación fallida. Comprueba los datos hardcodeados.");
                return false;
            }

            ftpClient.enterLocalPassiveMode();
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

            System.out.println("[OK] Conectado y autenticado correctamente.");
            System.out.println("[INFO] Directorio actual: " + ftpClient.printWorkingDirectory());
            return true;
        } catch (SocketException e) {
            System.out.println("[ERROR] Error de red: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("[ERROR] Error I/O: " + e.getMessage());
        }
        return false;
    }

    private void listarDirectorio() {
        try {
            System.out.println("\n[INFO] Solicitando listado del directorio: " + ftpClient.printWorkingDirectory());
            FTPFile[] files = ftpClient.listFiles();

            if (files == null || files.length == 0) {
                System.out.println("[INFO] Directorio vacío o sin permisos para listar.");
                return;
            }

            System.out.printf("%-40s %-8s %12s%n", "NOMBRE", "TIPO", "TAMAÑO");
            System.out.println("--------------------------------------------------------------------");
            for (FTPFile f : files) {
                String tipo = f.isDirectory() ? "<DIR>" : "<FILE>";
                System.out.printf("%-40s %-8s %12d%n", f.getName(), tipo, f.getSize());
            }
            System.out.println("--------------------------------------------------------------------");
            System.out.println("[OK] Listado completado.");
        } catch (IOException e) {
            System.out.println("[ERROR] Error al listar directorio: " + e.getMessage());
        }
    }

    private void descargarArchivoInteractive() {
        try {
            System.out.print("Nombre del archivo remoto a descargar: ");
            String nombreRemoto = scanner.nextLine().trim();
            if (nombreRemoto.isEmpty()) {
                System.out.println("[ERROR] Nombre remoto vacío.");
                return;
            }

            System.out.print("Ruta local de destino (enter = usar mismo nombre): ");
            String rutaLocal = scanner.nextLine().trim();
            if (rutaLocal.isEmpty()) {
                rutaLocal = "./" + nombreRemoto;
            }

            if (descargarArchivo(nombreRemoto, rutaLocal)) {
                System.out.println("[OK] Descarga completada: " + rutaLocal);
            } else {
                System.out.println("[ERROR] Descarga fallida.");
            }
        } catch (Exception e) {
            System.out.println("[ERROR] Excepción durante descarga: " + e.getMessage());
        }
    }

    private boolean descargarArchivo(String nombreRemoto, String rutaLocal) {
        try (OutputStream output = new FileOutputStream(rutaLocal)) {
            System.out.println("[INFO] Iniciando descarga de: " + nombreRemoto + " -> " + rutaLocal);
            boolean success = ftpClient.retrieveFile(nombreRemoto, output);
            System.out.println("[DEBUG] Respuesta servidor (retrieveFile): " + ftpClient.getReplyString().trim());
            return success;
        } catch (IOException e) {
            System.out.println("[ERROR] Error al descargar archivo: " + e.getMessage());
            return false;
        }
    }

    /**
     * Sube el archivo usando la ruta hardcodeada HARDCODED_UPLOAD_PATH.
     * Pide únicamente el nombre de destino remoto (se puede dejar vacío para usar el mismo nombre).
     */
    private void subirHardcoded() {
        File archivo = new File(UPLOAD_PATH);
        if (!archivo.exists() || !archivo.isFile()) {
            System.out.println("[ERROR] El archivo local hardcodeado no existe: " + UPLOAD_PATH);
            return;
        }

        System.out.print("Nombre de destino en el servidor (enter = usar '" + archivo.getName() + "'): ");
        String nombreRemoto = scanner.nextLine().trim();
        if (nombreRemoto.isEmpty()) {
            nombreRemoto = archivo.getName();
        }

        if (subirArchivo(UPLOAD_PATH, nombreRemoto)) {
            System.out.println("[OK] Subida completada: " + nombreRemoto);
        } else {
            System.out.println("[ERROR] Subida fallida.");
        }
    }

    private boolean subirArchivo(String rutaLocal, String nombreRemoto) {
        try (InputStream input = new FileInputStream(rutaLocal)) {
            System.out.println("[INFO] Iniciando subida de: " + rutaLocal + " -> " + nombreRemoto);
            boolean success = ftpClient.storeFile(nombreRemoto, input);
            System.out.println("[DEBUG] Respuesta servidor (storeFile): " + ftpClient.getReplyString().trim());
            return success;
        } catch (IOException e) {
            System.out.println("[ERROR] Error al subir archivo: " + e.getMessage());
            return false;
        }
    }

    private void desconectar() {
        try {
            if (ftpClient.isConnected()) {
                try {
                    ftpClient.logout();
                    System.out.println("[INFO] Logout realizado.");
                } catch (IOException e) {
                    System.out.println("[WARN] Error durante logout: " + e.getMessage());
                }
                try {
                    ftpClient.disconnect();
                    System.out.println("[OK] Desconectado del servidor FTP.");
                } catch (IOException e) {
                    System.out.println("[ERROR] Error al desconectar: " + e.getMessage());
                }
            } else {
                System.out.println("[INFO] No había conexión activa.");
            }
        } finally {
            // Asegurar estado limpio
            ftpClient = new FTPClient();
        }
    }
}