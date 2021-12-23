package dumbledorearmy.hogwartsinstituteoftechnology;
import java.util.HashMap;
import java.util.Set;

public class CalculateDays { //HIT is a labor camp, so it doesn't have holidays
    public static HashMap<Integer, Integer> map = new HashMap<>();
    public static void initialize(){
        map.put(1, 31);
        map.put(2, 28);
        map.put(3, 31);
        map.put(4, 30);
        map.put(5, 31);
        map.put(6, 30);
        map.put(7, 31);
        map.put(8, 31);
        map.put(9, 30);
        map.put(10, 31);
        map.put(11, 30);
        map.put(12, 31);
    }

    public static int calculateTotal
            (Set<Integer> weekdays, int sY, int sM, int sD, int eY, int eM, int eD, int startWeekDay){

        int days = 0;
        int currY = sY;
        int totalM;
        if (sY == eY){
            totalM = eM - sM - 2;
        }
        else{
            totalM = eM + 12 - sM - 2;
        }

        boolean flag = false;
        days += map.get(sM++ % 12) - sD;
        if (sM == 0) sM = 12;
        //先统计天数，然后根据日期加呗

        if (sY % 4 == 0 && sY % 100 != 0){
            map.put(2, 29);
            flag = true;
            //明年更成普通
        }
        while (totalM > 0){
            //遇到12之后更新map
            days += map.get(sM++ % 12);
            if (sM == 0){
                sM = 12;

            }
            if (sM == 1){
                if (flag){
                    map.put(2, 28);
                }
                else if (eY % 4 == 0 && eY % 100 != 0){
                    map.put(2, 29);
                }
            }
            totalM--;
        }
        days += eD;

        int ans = 0;
        for (int i = 0; i < days; i++){
            if (weekdays.contains(startWeekDay)){
                ans++;
            }
            startWeekDay = (startWeekDay + 1) % 7;
            if (startWeekDay == 0){
                startWeekDay = 7;
            }
        }
        return ans;



    }
}
