package org.example;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.OutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Servidor HTTP simple.
 * Rutas:
 *  - /     -> página de bienvenida (HTML)
 *  - /hora -> fecha y hora actual (HTML)
 *  - /info -> información básica del sistema (HTML)
 *
 * Ejecución: java SimpleHttpServer [puerto]
 * Si no se pasa puerto usa 8080.
 */
public class SimpleHttpServer {

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if (args.length > 0) {
            try { port = Integer.parseInt(args[0]); } catch (NumberFormatException ignored) {}
        }

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        // Un solo contexto en "/" y hacemos routing simple por path
        server.createContext("/", new RootHandler());
        // Usar un executor para poder atender concurrentemente
        server.setExecutor(java.util.concurrent.Executors.newCachedThreadPool());
        server.start();

        System.out.println("[OK] Servidor HTTP simple iniciado en el puerto " + port);
        System.out.println("[INFO] Rutas disponibles: /  /hora  /info");
    }

    static class RootHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();
            String client = exchange.getRemoteAddress().toString();

            // Solo aceptamos GET
            if (!"GET".equalsIgnoreCase(method)) {
                String body = "Método no permitido\n";
                exchange.getResponseHeaders().set("Content-Type", "text/plain; charset=UTF-8");
                exchange.sendResponseHeaders(405, body.getBytes().length);
                try (OutputStream os = exchange.getResponseBody()) { os.write(body.getBytes()); }
                System.out.println("[WARN] " + method + " " + path + " -> 405 desde " + client);
                return;
            }

            String responseHtml;
            int status = 200;

            switch (path) {
                case "/":
                case "":
                    responseHtml = welcomePage();
                    break;
                case "/hora":
                    responseHtml = horaPage();
                    break;
                case "/info":
                    responseHtml = infoPage();
                    break;
                default:
                    status = 404;
                    responseHtml = notFoundPage(path);
            }

            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            byte[] bytes = responseHtml.getBytes("UTF-8");
            exchange.sendResponseHeaders(status, bytes.length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(bytes);
            }
            System.out.println((status==200 ? "[INFO] " : "[WARN] ") + "GET " + path + " -> " + status + " desde " + client);
        }

        private String welcomePage() {
            return "<!doctype html><html><head><meta charset='utf-8'><title>Bienvenido</title></head>"
                    + "<body><h1>Servidor HTTP Simple</h1>"
                    + "<p>Rutas disponibles:</p><ul>"
                    + "<li><a href=\"/hora\">/hora</a> - Hora actual</li>"
                    + "<li><a href=\"/info\">/info</a> - Información del sistema</li>"
                    + "</ul></body></html>";
        }

        private String horaPage() {
            LocalDateTime ahora = LocalDateTime.now();
            String f = ahora.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            return "<!doctype html><html><head><meta charset='utf-8'><title>Hora</title></head>"
                    + "<body><h1>Hora del sistema</h1><p style='font-size:1.4em;'>" + f + "</p>"
                    + "<p><a href='/'>← Volver</a></p></body></html>";
        }

        private String infoPage() {
            String host = "desconocido";
            try { host = java.net.InetAddress.getLocalHost().getHostName(); } catch (Exception ignored) {}
            String os = System.getProperty("os.name");
            String osVer = System.getProperty("os.version");
            String arch = System.getProperty("os.arch");
            return "<!doctype html><html><head><meta charset='utf-8'><title>Info</title></head>"
                    + "<body><h1>Información del sistema</h1><ul>"
                    + "<li>Nombre del equipo: " + host + "</li>"
                    + "<li>Sistema operativo: " + os + " (v" + osVer + ")</li>"
                    + "<li>Arquitectura: " + arch + "</li>"
                    + "</ul><p><a href='/'>← Volver</a></p></body></html>";
        }

        private String notFoundPage(String path) {
            return "<!doctype html><html><head><meta charset='utf-8'><title>404</title></head>"
                    + "<body><h1>404 - No encontrado</h1><p>La ruta '" + path + "' no existe.</p>"
                    + "<p><a href='/'>← Volver</a></p></body></html>";
        }
    }
}