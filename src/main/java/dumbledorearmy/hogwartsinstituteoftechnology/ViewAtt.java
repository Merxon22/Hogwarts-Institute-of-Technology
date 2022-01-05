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

            String query = "select total from classinfo where subject='" + classNow + "'";
            ResultSet rs = stmt.executeQuery(query);

            out.println("<html><head>\n" +
                    "    <title>Check Attendance</title>\n" +
                    "\n" +
                    "    <link rel=\"stylesheet\" href=\"css/mainStyle.css\">\n" +
                    "    <link rel=\"icon\" href=\"ResourceFolder/Icon.png\">\n" +
                    "</head><body background=\"https://ww2.kqed.org/app/uploads/sites/23/2015/05/Beard-Algorithm-1440x811.jpg\" style=\"background-size: cover\"><center>\n");
            request.getRequestDispatcher("module/headerLoggedIn.jsp").include(request, response);
            request.getRequestDispatcher("module/CheckLog.jsp").include(request, response);
            out.println("<div class=\"centerBox\">");
            out.println("<h2 style=\"padding-bottom: 20px; margin-bottom: 20px; border-bottom: 1px solid darkgrey\"><b>Checking Attendance for " + classNow + "</b></h2>");
            int total=0;
            while(rs.next()){
                total = rs.getInt("total");
//                out.println("<h5>You have totally " + total + " " + classNow + " before checking today</h5>");
            }

            out.println("<table class=\"table table-stripped text-center\"><tr class=\"table-dark\"><th class=\"text-center\" style=\"width: 50%\">Student Name</th><th class=\"text-center\" style=\"width: 50%\">His/her Total Attendance</tr>");
            query = "select " + classNow + " .attendance, student.Firstname, student.Lastname" +
                    " from " + classNow +" join student on " + classNow + ".student_id=student.id";

            rs = stmt.executeQuery(query);
            while(rs.next()){
                String full = rs.getString("Firstname") + " " + rs.getString("Lastname");
                int att = rs.getInt("attendance");

                out.println("<tr><td>" + full + "</td>");
                out.println("<td>" + att + "/" + total + "</td><tr>");
            }
            out.println("</table>");
//            out.println("<input type=\"hidden\" name=\"clasx\" value=\"" + classNow + "\">");
            out.println("<a href=\"TeaBackAtt\"><button class=\"btn btn-primary\" style=\"width: 160px; margin-top: 20px;\">Back</button></a>");
            out.println("<a href=\"CheckAt.jsp?clasx=" + classNow + "\"><input class=\"btn btn-primary\" type=\"submit\" value=\"Update Attendance\" style=\"width: 160px; margin-top: 20px;\"></a>");
            out.println("</div>");
            request.getRequestDispatcher("module/footer.jsp").include(request, response);
            out.println("</center></body></html>");
        }catch (Exception exe){
            System.out.println(exe);
        }
    }
}
