package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "ViewStu", value = "/ViewStu")
public class ViewStu extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try{
            Connection con = Provider.GetConn();
            Statement stmt = con.createStatement();
            String x;
            ResultSet rs;
            x = "select * from students";
            rs = stmt.executeQuery(x);
            out.println("<table>\n" +
                    "        <th>\n" +
                    "            <td>First Name</td>\n" +
                    "            <td>Last Name</td>\n" +
                    "            <td>Email</td>\n" +
                    "            <td>Password</td>\n" +
                    "        </th>");
            while (rs.next()){
                out.println("<tr>\n" +
                        "            <td>" + rs.getString("Firstname") + "</td>\n" +
                        "            <td>" + rs.getString("Lastname") + "</td>\n" +
                        "            <td>" + rs.getString("email") + "</td>\n" +
                        "            <td>" + rs.getString("password") + "</td>\n" +
                        "        </tr>");
            }

            out.println("</table>");




        } catch (Exception exe) {
            System.out.println("Exception caught" + exe);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
