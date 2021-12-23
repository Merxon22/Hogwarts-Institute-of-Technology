package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "ViewClass", value = "/ViewClass")
public class ViewClass extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");

            Connection con = Provider.GetConn();

            Statement stmt1 = con.createStatement();
            ResultSet rp = stmt1.executeQuery("select * from Classinfo");

            out.println("<th>\n" +
                    "            <td>Subject</td>\n" +
                    "            <td>Teacher</td>\n" +
                    "            <td>Time</td>\n" +
                    "            <td>Description</td>\n" +
                    "        </th>\n");



            while (rp.next()){
                out.println("<tr>\n" +
                        "            <td>" + rp.getString("Subject") + "</td>\n" +
                        "            <td>" + rp.getString("teacher") + "</td>\n" +
                        "            <td>" + rp.getString("Time") + "</td>\n" +
                        "            <td>" + rp.getString("Des") + "</td>\n" +
                        "        </tr>");
            }

            con.close();
        } catch(Exception exe){System.out.println("Exception caught"+exe);}
    }



}


