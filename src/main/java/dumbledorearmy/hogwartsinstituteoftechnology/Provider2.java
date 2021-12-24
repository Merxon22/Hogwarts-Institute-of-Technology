package dumbledorearmy.hogwartsinstituteoftechnology;

import java.sql.Connection;
import java.sql.DriverManager;

public class Provider2 {
    static Connection GetConn(){
        final String DB_URL = "jdbc:mysql://remotemysql.com:3306/cwE0Bj38Hg";
        final String USER = "cwE0Bj38Hg";
        final String PASS = "RmfHam4gf1";
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
