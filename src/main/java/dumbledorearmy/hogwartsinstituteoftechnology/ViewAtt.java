package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "ViewAtt", value = "/ViewAtt")
public class ViewAtt extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            String classNow = request.getParameter("clasx");
            Connection con = Provider.GetConn();

            Statement stmt = con.createStatement();
            Statement stmt1 = con.createStatement();
            Statement stmt2 = con.createStatement();

            String query = "select " + classNow + " .attendance, student.Firstname, student.Lastname" +
                    " from " + classNow +" join student on " + classNow + ".student_id=student.id";

            ResultSet rs = stmt.executeQuery(query);

            out.println("<table><tr><th>Student Name</th><th>His Total Attendance</tr>");
            while(rs.next()){
                String full = rs.getString("Firstname") + " " + rs.getString("Lastname");
                int att = rs.getInt("attendance");

                out.println("<tr><td>" + full + "</td>");
                out.println("<td>" + att + "</td><tr>");
            }

            out.println("</table>");
            out.println("<form action=\"CheckAt.jsp\" method=\"post\">");
            out.println("<input type=\"hidden\" name=\"clasx\" value=\"" + classNow + "\">");
            out.println("<input type=\"submit\" value=\"Add Attendance for Today\"></form>");
        }catch (Exception exe){
            System.out.println(exe);
        }
    }
}
