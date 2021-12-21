package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;



@WebServlet(name = "AdminViewAccounts", value = "/AdminViewAccounts")
public class AdminViewAccounts extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try{
            Connection con = Provider.GetConn();
            Statement stmt = con.createStatement();
            String x = "select * from teachers";

            ResultSet rs = stmt.executeQuery(x);

            //咱们左边放老师，右边放学生

            out.println("<a href = \"AddStudentAcc.jsp\"><button type=\"button\">Add Students</button></a>");
            out.println("<a href = \"AddTeacherAcc.jsp\"><button type=\"button\">Add Teachers</button></a>");
            out.println("<a href = \"DelStu.jsp\"><button type=\"button\">Delete Students</button></a>");
            out.println("<a href = \"DelTea.jsp\"><button type=\"button\">Delete Teachers</button></a>");


            out.println("<table>\n" +
                    "        <th>\n" +
                    "            <td>Account</td>\n" +
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
            x = "select * from students";
            rs = stmt.executeQuery(x);
            out.println("<table>\n" +
                    "        <th>\n" +
                    "            <td>Account</td>\n" +
                    "            <td>Password</td>\n" +
                    "        </th>");
            while (rs.next()){
                out.println("<tr>\n" +
                        "            <td>" + rs.getString("Firstname") + "</td>\n" +
                        "            <td>" + rs.getString("Lastname") + "</td>\n" +
                        "            <td>" + rs.getString("email") + "</td>\n" +
                        "            <td>" + rs.getString("password") + "</td>\n" +
                        "            <td>" + rs.getString("grade") + "</td>\n" +
                        "            <td>" + rs.getString("class") + "</td>\n" +
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
