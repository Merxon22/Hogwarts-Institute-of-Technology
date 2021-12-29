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
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        Connection con = Provider.GetConn();
        try{
            Statement stmt1 = con.createStatement();
            ResultSet rp = stmt1.executeQuery("select * from classinfo");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>View Teacher</title>");
            out.println("<link rel=\"stylesheet\" href=\"css/mainStyle.css\">");
            out.println("<link rel=\"icon\" href=\"ResourceFolder/Icon.png\">");
            out.println("</head>");
            out.println("<body background=\"https://tjn-blog-images.s3.amazonaws.com/wp-content/uploads/2015/09/20003023/Which-Fields-Have-the-Highest-Paying-Administrative-Jobs.jpg\" style=\"background-size: cover\"><center>");
            request.getRequestDispatcher("module/headerLoggedIn.jsp").include(request, response);
            out.println("<div id=\"containerBox\">");
            out.println("<div class=\"centerBox\" style=\"width: 60%; !important;\">");
            request.getRequestDispatcher("module/CheckLog.jsp").include(request, response);
            out.println("<h2><b>View Teacher</b></h2>");
            out.println("<table class=\"table table-striped\" style=\"margin-top: 20px; text-align: center; !important;\">\n" +
                    "        <thead><tr>\n" +
                    "            <th class=\"text-center\">Subject</td>\n" +
                    "            <th class=\"text-center\">Teacher</td>\n" +
                    "            <th class=\"text-center\">Description</td>\n" +
                    "            <th class=\"text-center\">Time</td>\n" +
                    "        </tr></thead>");
            out.println("<tbody>");

            while (rp.next()){
//                String time[] = rp.getString("time").split(";");
//                String tt = "";
//                for (String t: time){
//                    tt += t;
//                    tt += "<br>";
//                }
//                tt = tt.substring(0, tt.length() - 2);
//                System.out.println(tt);
                out.println("<tr>\n" +
                        "            <td>" + rp.getString("subject") + "</td>\n" +
                        "            <td>" + rp.getString("teacher") + "</td>\n" +
                        "            <td>" + rp.getString("des") + "</td>\n" +
                        "            <td>" + rp.getString("time") + "</td>\n" +
                        "        </tr>");
            }            out.println("</table>");
            out.println("</tbody>");
            out.println("</table>");
            out.println("<input class=\"btn btn-primary\" type=\"button\" value=\"Back\" onclick=\"history.back();\" style=\"width: 80px; margin-top: 20px;\">\n");
            out.println("</div>");
            out.println("</div>");
            request.getRequestDispatcher("module/footer.jsp").include(request, response);
            out.println("</center></body>");
            out.println("</html>");
        } catch(Exception exe){
            System.out.println("Exception caught"+exe);
        }finally {
            try{
                con.close();
            }catch (Exception e){}
        }
    }



}


