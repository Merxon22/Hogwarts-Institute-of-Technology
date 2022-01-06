package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet(name = "StudentAttendenceView", value = "/StudentAttendenceView")
public class StudentAttendenceView extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String Email2="";
        if (session.getAttribute("email") != null) {
            Email2 = session.getAttribute("email").toString();
        }
        String Email="";
        Cookie[] cookies = request.getCookies();
        for (Cookie c: cookies){
            if (c.getName().equals("email")){
                Email = c.getValue();
            }
        }
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection con = Provider.GetConn();
        ArrayList<String> sub = new ArrayList<String>();
        sub.add("Chinese");
        sub.add("Math");
        sub.add("CS");
        sub.add("Physics");
        sub.add("Chemistry");
        sub.add("MacroEconomics");
        sub.add("MicroEconomics");
        try {

            Statement stmt1 = con.createStatement();
            Statement stmt2 = con.createStatement();
            int id = 0;
            ResultSet rs3= stmt2.executeQuery("select student.id from student where email='"+Email+"'");
            while (rs3.next()){
                id=rs3.getInt("id");
            }
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
            out.println("<h2 style=\"padding-bottom: 20px; margin-bottom: 20px; border-bottom: 1px solid darkgrey\"><b>View Attendance</b></h2>");
            ArrayList<String> N = new ArrayList<String>();
            N.add("Chinese");
            N.add("Math");
            N.add("CS");
            N.add("Physics");
            N.add("Chemistry");
            N.add("MacroEconomics");
            N.add("MicroEconomics");
            Statement stmt = con.createStatement();
            for (int i = 0; i < sub.size(); i++) {
                ResultSet rs = stmt.executeQuery("select * from "+sub.get(i)+" where student_id="+id);
                if(!rs.next()){
                    N.remove(sub.get(i));
                }
            }
            out.println("<table class=\"table table-striped\" style=\"margin-top: 20px; text-align: center; !important;\">\n" +
                    "        <thead><tr class=\"table-dark\">\n" +
                    "            <th class=\"text-center\">Subject</td>\n" +
                    "            <th class=\"text-center\">Attendance</td>\n"+
                    "        </tr></thead>");
            out.println("<tbody>");
            String t = null;
            String participate = null;
            for (int i = 0; i < N.size(); i++) {
                ResultSet rp = stmt1.executeQuery("select * from " + N.get(i) + " where student_id=" + id);
                ResultSet rp5 = stmt2.executeQuery("select * from classinfo where subject='"+N.get(i)+"'");
                if(rp5.next()){
                t=rp5.getString("total");}
                while(rp.next()){
                    participate=rp.getString("attendance");
                    }


                out.println("<tr><td>"+N.get(i)+"</td><td>"+participate+"/"+t+"</td></tr>");


            }




            //out.println("<tr><td>Subject1</td><td>80</td><td>90</td><td>50/60</td></tr>");
            //out.println("<tr><td>Subject2</td><td>70</td><td>95</td><td>23/30</td></tr>");
            //con.commit();
            con.close();
            out.println("</tbody>");
            out.println("</table>");
            out.println("<a href=\"StuBack\" style=\"width: 80px;\"><button class=\"btn btn-primary\" style=\"width: 80px; margin-top: 20px;\" type=\"button\">Back</button></a>");
            out.println("</div>");
            out.println("</div>");
            request.getRequestDispatcher("module/footer.jsp").include(request, response);
            out.println("</center></body>");
            out.println("</html>");
            N.clear();
        } catch (Exception exe) {
            System.out.println("Exception caught" + exe);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
