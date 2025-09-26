import java.io.*;
import java.util.Properties;

public class properties {
    public static void main(String[] args) {
        FileReader reader;
        try {
            reader = new FileReader("src/config.properties.properties");
            Properties properties = new Properties();
            properties.load(reader);
            String nombre = properties.getProperty("nombre");
            System.out.println(nombre);
            properties.setProperty("nota","notable");
            properties.store(new BufferedWriter(new FileWriter("src/config.properties.properties"))," ");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
