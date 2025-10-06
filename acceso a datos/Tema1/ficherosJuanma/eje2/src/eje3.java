import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class eje3 {
    public static void main(String[] args) {
        //abrimos archivo lectore
        File archivoOrigen = new File("texto.txt");

        try {
            FileInputStream entradaorigen  = new FileInputStream(archivoOrigen);
            File archivoDestino = new File("destinoCifrado.txt");
            FileOutputStream salidaDestino = new FileOutputStream(archivoDestino);
            //crear clave
            String clave = "k;[/YqX@K,2wm,Y(";
            SecretKeySpec claveCifrado = new SecretKeySpec(clave.getBytes(StandardCharsets.UTF_8),"AES");

            //Iniciar el cifrado
            Cipher cifrador = Cipher.getInstance("AES");
            cifrador.init(Cipher.ENCRYPT_MODE,claveCifrado);
            //Cifrar
            //primero leemos el archivo
            byte[] buffer = new byte[1024];
            int bytelineas;
            while ((bytelineas = entradaorigen.read(buffer))!=-1){
                byte[] byteCifrados = cifrador.update(buffer,0,bytelineas);
                //Escribimos en el fichero
                salidaDestino.write(byteCifrados);

            }
            byte[] byteCifrados = cifrador.doFinal();
            entradaorigen.close();
            salidaDestino.close();

            System.out.println("fichero cifrado");




            //abrimos el archivo encryptafo

            FileInputStream entradacifrado = new FileInputStream(archivoDestino);
            FileOutputStream salidadecifrada = new FileOutputStream(new File("text2.txt"));

            //Iniciar el cifrado
            Cipher decifrado = Cipher.getInstance("AES");
            cifrador.init(Cipher.DECRYPT_MODE,claveCifrado);

            while ((bytelineas = entradacifrado.read(buffer))!=-1){
                byte[] bytedeCifrado = decifrado.update(buffer,0,bytelineas);
                //Escribimos en el fichero
                salidadecifrada.write(bytedeCifrado);
            }
            byte[] bytedeCifrado = decifrado.doFinal();
            entradacifrado.close();
            salidadecifrada.close();


        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IOException |
                 IllegalBlockSizeException | BadPaddingException e) {
            throw new RuntimeException(e);
        }
    }
}
