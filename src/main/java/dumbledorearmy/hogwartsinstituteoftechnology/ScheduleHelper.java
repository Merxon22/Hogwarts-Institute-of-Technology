package dumbledorearmy.hogwartsinstituteoftechnology;

import java.util.HashMap;
import java.util.Map;

public class ScheduleHelper {
    public static HashMap<String, Integer> mapWeek = new HashMap<>();
    public static HashMap<String, Integer> mapClass = new HashMap<>();
    public static void initialize(){
        mapWeek.put("Monday", 0);
        mapWeek.put("Tuesday", 1);
        mapWeek.put("Wednesday", 2);
        mapWeek.put("Thursday", 3);
        mapWeek.put("Friday", 4);
        mapClass.put("8:30-9:25", 0);
        mapClass.put("9:30-10:25", 1);
        mapClass.put("10:30-11:25", 2);
        mapClass.put("11:30-12:25", 3);
        mapClass.put("13:15-14:10", 4);
        mapClass.put("14:15-15:10", 5);
        mapClass.put("15:15-16:10", 6);
    }

    public static HashMap<String, Integer> getMapClass() {
        initialize();
        return mapClass;
    }

    public static HashMap<String, Integer> getMapWeek() {
        initialize();
        return mapWeek;
    }

    public static void main(String[] args) {
        initialize();
        System.out.println(mapWeek.get("Monday"));
        System.out.println(mapClass.get("8:30-9:25"));
    }
}
