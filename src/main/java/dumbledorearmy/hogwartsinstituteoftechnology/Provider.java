package dumbledorearmy.hogwartsinstituteoftechnology;

import java.sql.*;

public class Provider {
    static Connection GetConn(){
        final String DB_URL = "jdbc:mysql://db4free.net:3306/hituniversity12";
        final String USER = "hituniversity12";
        final String PASS = "G2011g1019";
        Connection conn = null;

        try{
            Class.forName("com.mysql.jdbc.Driver");
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


}
