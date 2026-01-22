package org.example;

import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ftp.FTPSClient;

import javax.net.ssl.X509TrustManager;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        FTPSClient ftp = new FTPSClient("TLS", true);
        try {
            ftp.setTrustManager(new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] xcs,
                                               String string) {
                }

                public void checkServerTrusted(X509Certificate[] xcs,
                                               String string) {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return
                            null;
                }
            });

            ftp.connect("demo.wftpserver.com");
            if (!FTPReply.isPositiveCompletion(ftp.getReplyCode())) {
                ftp.disconnect();
                System.out.println("No se pudo conectar al servidor");
                return;
            }

            ftp.login("demo", "demo");
            ftp.execPBSZ(0);
            ftp.execPROT("P"); // protección de datos
            ftp.enterLocalPassiveMode();

           listDirectory(ftp);
            Scanner sc = new Scanner(System.in);
            System.out.println("¿Qué fichero quieres descargar?");
            String file = sc.nextLine().trim();
            if (!file.isEmpty()) {
                try (FileOutputStream fos = new FileOutputStream("F:/Users/username/Downloads/" + file)) {
                    ftp.retrieveFile(file, fos);
                    System.out.println("Hecho");
                }
            }
            ftp.logout();
            ftp.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void listDirectory(FTPSClient ftp) throws IOException {
        FTPFile[] files = ftp.listFiles();
        for (FTPFile file : files) {
            System.out.println(file.getName());
        }
    }

}