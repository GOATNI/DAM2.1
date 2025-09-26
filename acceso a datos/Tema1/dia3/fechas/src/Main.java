import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Locale;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //date
        Calendar c = Calendar.getInstance();
        c.set(2022,Calendar.JANUARY,19);

        LocalDate date = LocalDate.of(2022, Month.OCTOBER,19);

        //horas

        LocalTime time = LocalTime.of(10,0);

        LocalDateTime fechaHora = LocalDateTime.of(date,time);

        LocalDateTime haceunasemanaquediafue = fechaHora.minusDays(7);

        String foramateandoDate = haceunasemanaquediafue.format(DateTimeFormatter.BASIC_ISO_DATE);




    }
}