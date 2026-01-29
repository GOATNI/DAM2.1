package org.example;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Properties;
import java.util.Scanner;


public class SmtpClientApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("¡");

        System.out.print("Servidor SMTP (ej: smtp.gmail.com): ");
        String host = sc.nextLine().trim();

        System.out.print("Puerto (enter = 587): ");
        String portInput = sc.nextLine().trim();
        String port = portInput.isEmpty() ? "587" : portInput;

        System.out.print("Usuario (email emisor): ");
        String user = sc.nextLine().trim();

        System.out.print("Contraseña: ");
        String pass = sc.nextLine();

        System.out.print("Correo destinatario: ");
        String to = sc.nextLine().trim();

        System.out.print("Asunto: ");
        String subject = sc.nextLine();

        System.out.print("Cuerpo (una línea): ");
        String body = sc.nextLine();

        System.out.print("Adjuntar archivo? (s/n): ");
        String attachOpt = sc.nextLine().trim().toLowerCase();

        String attachmentPath = null;
        if (attachOpt.equals("s") || attachOpt.equals("si")) {
            System.out.print("Ruta del archivo a adjuntar: ");
            attachmentPath = sc.nextLine().trim();
            File f = new File(attachmentPath);
            if (!f.exists() || !f.isFile()) {
                System.out.println("[WARN] Archivo no encontrado. Se enviará sin adjunto.");
                attachmentPath = null;
            }
        }

        sc.close();

        // Propiedades básicas (STARTTLS)
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        });

        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(user));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            msg.setSubject(subject);

            if (attachmentPath == null) {
                msg.setText(body);
            } else {
                MimeBodyPart textPart = new MimeBodyPart();
                textPart.setText(body);

                MimeBodyPart filePart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachmentPath);
                filePart.setDataHandler(new DataHandler(source));
                filePart.setFileName(new File(attachmentPath).getName());

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(textPart);
                multipart.addBodyPart(filePart);

                msg.setContent(multipart);
            }

            Transport.send(msg);
            System.out.println("[OK] Correo enviado correctamente.");
        } catch (AuthenticationFailedException e) {
            System.out.println("[ERROR] Autenticación fallida: " + e.getMessage());
        } catch (MessagingException e) {
            System.out.println("[ERROR] Error al enviar correo: " + e.getMessage());
        }
    }
}