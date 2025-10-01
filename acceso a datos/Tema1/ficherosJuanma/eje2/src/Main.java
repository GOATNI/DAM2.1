import java.io.*;


public class Main {
    public static void main(String[] args) {
        String sourcefile = "texto";
        String destination = "texto.txt";
        copyFlie(sourcefile,destination);
        
    }

    private static void copyFlie(String sourcefile, String destination) {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(sourcefile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(destination));
            String line;
            while ((line = reader.readLine()) != null){
                writer.write(line);
                writer.newLine();
            }
            writer.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}