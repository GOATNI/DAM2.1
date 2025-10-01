import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String FILEPATH = "texto";
        int linecount = countlines(FILEPATH);
        System.out.println(linecount);
    }

    private static int countlines(String filepath) {
        int lines = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filepath));
            while (reader.readLine() != null){
                lines++;
            }
            return lines;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}