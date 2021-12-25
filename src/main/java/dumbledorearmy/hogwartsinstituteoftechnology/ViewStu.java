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

        Connection con = Provider.GetConn();
        try{
            Statement stmt = con.createStatement();
            String x;
            ResultSet rs;
            x = "select * from student";
            rs = stmt.executeQuery(x);
            out.println("<html>");
            out.println("<head>");
            out.println("<title>View Student</title>");
            out.println("<link rel=\"stylesheet\" href=\"css/mainStyle.css\">");
            out.println("<link rel=\"icon\" href=\"ResourceFolder/Icon.png\">");
            out.println("</head>");
            out.println("<body background=\"https://tjn-blog-images.s3.amazonaws.com/wp-content/uploads/2015/09/20003023/Which-Fields-Have-the-Highest-Paying-Administrative-Jobs.jpg\" style=\"background-size: cover\"><center>");
            request.getRequestDispatcher("module/headerLoggedIn.jsp").include(request, response);
            out.println("<div id=\"containerBox\">");
            out.println("<div class=\"centerBox\" style=\"width: 60%; !important;\">");
            request.getRequestDispatcher("module/CheckLog.jsp").include(request, response);
            out.println("<h2><b>View Student</b></h2>");
            out.println("<table class=\"table table-striped\" style=\"margin-top: 20px; text-align: center; !important;\">\n" +
                    "        <thead><tr>\n" +
                    "            <th class=\"text-center\">First Name</td>\n" +
                    "            <th class=\"text-center\">Last Name</td>\n" +
                    "            <th class=\"text-center\">Email</td>\n" +
                    "            <th class=\"text-center\">Password</td>\n" +
                    "        </tr></thead>");
            out.println("<tbody>");
            out.println("<tr><td>First1</td><td>Last1</td><td>Email1</td><td>Name1</td></tr>");
            out.println("<tr><td>First2</td><td>Last2</td><td>Email2</td><td>Name2</td></tr>");
            while (rs.next()){
                out.println("<tr>\n" +
                        "            <td>" + rs.getString("Firstname") + "</td>\n" +
                        "            <td>" + rs.getString("Lastname") + "</td>\n" +
                        "            <td>" + rs.getString("email") + "</td>\n" +
                        "            <td>" + rs.getString("password") + "</td>\n" +
                        "        </tr>");
            }
            out.println("</tbody>");
            out.println("</table>");
            out.println("<input class=\"btn btn-primary\" type=\"button\" value=\"Back\" onclick=\"history.back();\" style=\"width: 80px; margin-top: 20px;\">\n");
            out.println("</div>");
            out.println("</div>");
            request.getRequestDispatcher("module/footer.jsp").include(request, response);
            out.println("</center></body>");
            out.println("</html>");
        } catch (Exception exe) {
            System.out.println("Exception caught" + exe);
        }finally {
            try{
                con.close();
            }catch (Exception e){}
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
