package dumbledorearmy.hogwartsinstituteoftechnology;

import java.sql.*;

public class LocalConn {
    static Connection GetConn(){
        final String DB_URL = "jdbc:mysql://localhost/hit";
        final String USER = "root";
        final String PASS = "asdfgvcxz1";
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
