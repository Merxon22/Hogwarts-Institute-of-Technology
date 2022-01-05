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

import static java.lang.System.out;
import static javax.swing.text.html.CSS.getAttribute;

@WebServlet(name = "StudentGradeView", value = "/StudentGradeView")
public class StudentGradeView extends HttpServlet {

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

            out.println("<h2 style=\"padding-bottom: 20px; margin-bottom: 20px; border-bottom: 1px solid darkgrey\"><b>View Grade</b></h2>");
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
            ArrayList<String[]> n= new ArrayList<String[]>();
            for (int i = 0; i < N.size(); i++) {

                ResultSet rp = stmt1.executeQuery("select * from " + N.get(i) + " where student_id=" + id);
                while(rp.next()){
                    System.out.println(N.get(i));
                    ResultSet rp2 = stmt2.executeQuery("select COLUMN_NAME from information_schema.columns where table_name='" + sub.get(i) + "'");
                    while (rp2.next()) {
                    String name = rp2.getString("COLUMN_NAME");
                    if(!name.equals("student_id")&&!name.equals("attendance")){
                    System.out.println(name);
                    String name2 = rp.getString(name);
                    n.add(new String[]{name, name2});}
                }}
                StringBuilder query= new StringBuilder();
                for (int x = 0; x < n.size(); x++) {
                    query.append("<th class=\"text-center\">").append(n.get(x)[0]).append("</td>\n");
                }
                StringBuilder score= new StringBuilder("<tr><td class=\"text-center table-dark\" style=\"width: 120px;\">");
                score.append(N.get(i));
                score.append("</td>");
                for (int z = 0; z < n.size(); z++) {
                    score.append("<td>").append(n.get(z)[1]).append("</td>");
                }
                score.append("</tr></tbody></table>");
                out.println("<table class=\"table table-striped\" style=\"margin: 20px 0px; text-align: center; !important;\">\n" +
                        "        <thead><tr>\n" +
                        "            <th class=\"text-center table-dark\" style=\"width: 120px\">Subject</td>\n" + query+
                        "        </tr></thead>");
                out.println("<tbody>");
                out.println(score);
                n.clear();

            }

            N.clear();





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
        } catch (Exception exe) {
            System.out.println("Exception caught" + exe);
        }
    }
    }


