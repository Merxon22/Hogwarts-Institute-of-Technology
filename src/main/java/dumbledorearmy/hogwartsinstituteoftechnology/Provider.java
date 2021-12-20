package dumbledorearmy.hogwartsinstituteoftechnology;

import java.sql.*;

public class Provider {
    static Connection GetConn(){
        final String DB_URL = "jdbc:mysql://sql6.freemysqlhosting.net:3306/sql6459397";
        final String USER = "sql6459397";
        final String PASS = "nUmDxVi8LQ";
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
