package dumbledorearmy.hogwartsinstituteoftechnology;

public class ScheduleManager {
    public static String GetStyle(String className){
        return "background-color: " + GetColor(className) + "; color: white; font-weight: bold;";
    }
    public static String GetColor(String className){
        String color = "";
        switch (className){
            case "Math":
                color = "#d79888";
                break;
            case "Chinese":
                color = "#cbc462";
                break;
            case "CS":
                color = "#88a7d7";
                break;
            case "Physics":
                color = "#d07e5d";
                break;
            case "Chemistry":
                color = "#87cb80";
                break;
            case "MacroEconomics":
                color = "#84c8c3";
                break;
            case "MicroEconomics":
                color = "#b784c8";
                break;
            case "Philosophy":
                color = "#fc8c03";
                break;
            case "English":
                color = "#630000";
                break;
        }
        return color;
    }
}
