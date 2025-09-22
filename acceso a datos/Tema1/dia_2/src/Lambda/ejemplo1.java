package Lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ejemplo1 {
    public static void main(String[] args) {
        List<String> lsit = Arrays.asList("perro","gato","Elefante","conejo","mariposa");

        Collections.sort(lsit,(str1,str2)-> str1.length() - str2.length());
        System.out.println(lsit);

    }
}
