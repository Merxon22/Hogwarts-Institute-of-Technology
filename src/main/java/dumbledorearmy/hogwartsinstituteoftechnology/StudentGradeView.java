package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import static javax.swing.text.html.CSS.getAttribute;

@WebServlet(name = "StudentGradeView", value = "/StudentGradeView")
public class StudentGradeView extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String Email = "";
        if(session.getAttribute("email")!=null) {
            Email = session.getAttribute("email").toString();
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection con=Provider.GetConn();
        String[] S={"MagicChinese","MagicEconomics","MagicMathematics","MagicChemistry","MagicPhysics","MagicComputerScience"};

        try{
            Statement stmt1 = con.createStatement();

            out.println("<html>");
            out.println("<head>");
            out.println("<title>View Grade</title>");
            out.println("<link rel=\"stylesheet\" href=\"css/mainStyle.css\">");
            out.println("<link rel=\"icon\" href=\"ResourceFolder/Icon.png\">");
            out.println("</head>");
            out.println("<body background=\"https://st2.depositphotos.com/1371034/10014/v/950/depositphotos_100142054-stock-illustration-abstract-seamless-pattern-with-colorful.jpg\" style=\"background-size: cover\"><center>");
            request.getRequestDispatcher("module/headerLoggedIn.jsp").include(request, response);
            out.println("<div id=\"containerBox\">");
            out.println("<div class=\"centerBox\" style=\"width: 60%; !important;\">");
            request.getRequestDispatcher("module/CheckLog.jsp").include(request, response);
            out.println("<h2><b>View Grade</b></h2>");
            out.println("<table class=\"table table-striped\" style=\"margin-top: 20px; text-align: center; !important;\">\n" +
                    "        <thead><tr>\n" +
                    "            <th class=\"text-center\">Subject</td>\n" +
                    "            <th class=\"text-center\">Mid-term</td>\n" +
                    "            <th class=\"text-center\">Final</td>\n" +
                    "            <th class=\"text-center\">Attendance</td>\n" +
                    "        </tr></thead>");
            out.println("<tbody>");
            out.println("<tr><td>Subject1</td><td>80</td><td>90</td><td>50/60</td></tr>");
            out.println("<tr><td>Subject2</td><td>70</td><td>95</td><td>23/30</td></tr>");
//            for (int i = 0; i <6 ; i++) {
//                ResultSet rp = stmt1.executeQuery("select * from student join "+S[i]+" on Student.email="+S[i]+".email where email='"+Email+"'");
//                while (rp.next())
//                {
//                    String Subject=rp.getString("Subject");
//                    String mid = rp.getString("mid-term");
//                    String finalexam  = rp.getString("final");
//                    String p=rp.getString("participate")+"/"+rp.getString("total");
//
//                    out.println("<tr><td>" + Subject + "</td><td>" + mid +"</td><td>"+finalexam+"</td><td>"+p+"</td></tr>");
//                }
//            }
            out.println("</tbody>");
            out.println("</table>");
            out.println("<a href=\"StuBack\" style=\"width: 80px;\"><button class=\"btn btn-primary\" style=\"width: 80px; margin-top: 20px;\" type=\"button\">Back</button></a>\n<input class=\"btn btn-primary\" type=\"button\" value=\"Back\" style=\"width: 80px; margin-top: 20px;\"></a>");
            out.println("</div>");
            out.println("</div>");
            request.getRequestDispatcher("module/footer.jsp").include(request, response);
            out.println("</center></body>");
            out.println("</html>");
//            con.commit();
        } catch(Exception exe){
            System.out.println("Exception caught"+exe);
        }finally {
            try{
                con.close();
            }catch (Exception e){}
        }
    }



}

