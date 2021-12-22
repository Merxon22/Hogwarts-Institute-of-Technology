package dumbledorearmy.hogwartsinstituteoftechnology;

import java.sql.Connection;
import java.sql.Statement;

public class InitialPwGenerator {
    //为了安全性，初始密码生成为8位数字大小写字母随机组合的东西
    public static String pwd = "";
    public static String choices = "1234567890qwertyuiopasdfghjklzxcvbnmQWERTYUIOPLKJHGFSAZXCVBNM";

//    public static void main(String[] args) {
//        System.out.println(choices.length());
//    }

    public static String generate(){
        if (pwd.length() == 8){
            String fin = pwd;
            pwd = "";
            return fin;
        }
        int x = (int)(Math.random() * 61);
        //System.out.println(x);
        pwd = pwd + choices.substring(x, x + 1);
        return generate();
    }

//    public static void main(String[] args) {
//        System.out.println(generate());
//    }
}
