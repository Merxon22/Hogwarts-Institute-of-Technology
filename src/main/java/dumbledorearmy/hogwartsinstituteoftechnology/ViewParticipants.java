package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.bind.SchemaOutputResolver;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;

@WebServlet(name = "ViewParticipants", value = "/ViewParticipants")
public class ViewParticipants extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection con = Provider.GetConn();
        try {
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<html>");
            Statement stmt = con.createStatement();

            Cookie[] cookies = request.getCookies();
            String em = "";
            for (Cookie c : cookies) {
                if (c.getName().equals("email")) {
                    em = c.getValue();
                }
            }

            System.out.println(em);

            String[] classes = {};

            String query;
            query = "select * from teacher where email='" + em + "';";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                classes = rs.getString("Class").split(",");
            }

            System.out.println(classes[0]);

            //这里获得了一个(些)代表课的表，然后我们把它和student用fk（id）连起来，就可以得到firstn, lastn, email了
            response.setContentType("text/html");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>View Participants</title>");
            out.println("<link rel=\"stylesheet\" href=\"css/mainStyle.css\">");
            out.println("<link rel=\"icon\" href=\"ResourceFolder/Icon.png\">");
            out.println("</head>");
            out.println("<body background=\"https://res.cloudinary.com/highereducation/image/upload/c_fill,f_auto,fl_lossy,q_auto,w_1200,h_630/v1626194848/BestColleges.com/Blog/BC-Blog_Small-Large-Classrooms_7.13.21_FTR.jpg\" style=\"background-size: cover\"><center>");
            request.getRequestDispatcher("module/headerLoggedIn.jsp").include(request, response);
            out.println("<div id=\"containerBox\">");
            out.println("<div class=\"centerBox\" style=\"width: 60%; !important;\">");
            request.getRequestDispatcher("module/CheckLog.jsp").include(request, response);
            out.println("<h2 style=\"padding-bottom: 20px; margin-bottom: 20px; border-bottom: 1px solid darkgrey\"><b>View Participants</b></h2>");
            for (String clasx : classes) { //注意这里前段得考虑一个老师教多个课的情况，虽然我们现在不需要
                if (clasx.length() >= 2) {
                    System.out.println(clasx);
                    query = "select student.Firstname, student.Lastname, student.email from student " +
                            "join " + clasx + " on " + clasx + "." + "student_id = student.id";
                    System.out.println(query);
                    rs = stmt.executeQuery(query);

                    out.println("<table class=\"table table-striped\" style=\"margin-top: 20px; text-align: center; !important;\">\n" +
                            "        <thead>\n" +
                            "            <tr class=\"table-dark\"><th colspan = '3' class=\"text-center\">");
                    out.println(clasx.substring(0, 1).toUpperCase(Locale.ROOT) + clasx.substring(1, clasx.length()));

                    out.println("</th></tr>");
                    out.println("<tr><th class=\"text-center\" style=\"width: 33.33%;\">First Name</th>\n" +
                            "            <th class=\"text-center\" style=\"width: 33.33%;\">\n" +
                            "                Last Name\n" +
                            "            </th>\n" +
                            "            <th class=\"text-center\" style=\"width: 33.33%;\">\n" +
                            "                Email\n" +
                            "            </th></tr>");
                    out.println("</thead><tbody>");
                    while (rs.next()) {
                        out.println("<tr><td>" + rs.getString("Firstname") + "</td>");
                        out.println("<td>" + rs.getString("Lastname") + "</td>");
                        out.println("<td>" + rs.getString("Email") + "</td></tr>");
                    }
                    out.println("</tbody></table>");

                }
            }


            out.println("<a href= \"TeaBack\"><input class=\"btn btn-primary\" type=\"button\" value=\"Back\"\" style=\"width: 80px; margin-top: 20px;\"></ a>");
            out.println("</div>");
            out.println("</div>");
            request.getRequestDispatcher("module/footer.jsp").include(request, response);
            out.println("</center></body>");
            out.println("</html>");

        } catch (Exception exe) {
            System.out.println(exe);
        }

    }
}