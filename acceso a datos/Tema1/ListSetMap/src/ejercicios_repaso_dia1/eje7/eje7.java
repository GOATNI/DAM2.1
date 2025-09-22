package ejercicios_repaso_dia1.eje7;

import java.util.HashMap;
import java.util.Map;

public class eje7 {
    public static void main(String[] args) {
        Map<String,String> paisesCapitales = new HashMap<>();
        paisesCapitales.put("España","Madrid");
        paisesCapitales.put("Francia","Paris");

        for (Map.Entry<String,String> entry: paisesCapitales.entrySet()){
            System.out.println(entry);
        }
    }
}
