package dumbledorearmy.hogwartsinstituteoftechnology;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Dates {
    public static String getDate(){ //return date
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static String getWeek(){ //return weekday
        Calendar calendar = Calendar.getInstance();
        Date date = calendar.getTime();
        return new SimpleDateFormat("EEEE", Locale.ENGLISH).format(date.getTime());
    }


    public static void main(String[] args) {
        System.out.println(getDate());
        System.out.println(getWeek());
    }



}
