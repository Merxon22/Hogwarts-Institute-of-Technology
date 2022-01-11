package dumbledorearmy.hogwartsinstituteoftechnology;

import java.sql.*;

public class Provider {
    public static Connection GetConn(){
        final String DB_URL = "jdbc:mysql://localhost:3306/hit";
        final String USER = "root";
        final String PASS = "root";
        Connection conn = null;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            if(!conn.isClosed())
                System.out.println("Successfully connected to MySQL server");

            return conn;
        }catch(Exception e){
            System.out.println("Connection failed!");
            System.out.println(e);
            return null;
        }
    }

//    public static void main(String[] args) {
//        System.out.println("Hi");
//        Provider.GetConn();
//    }


}
