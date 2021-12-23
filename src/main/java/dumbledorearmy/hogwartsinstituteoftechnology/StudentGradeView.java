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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String Email=session.getAttribute("email").toString();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        Connection con=Provider.GetConn();
        String[] S={"MagicChinese","MagicEconomics","MagicMathematics","MagicChemistry","MagicPhysics","MagicComputerScience"};

        try{

            response.setContentType("text/html");
            Statement stmt1 = con.createStatement();
            out.println("<center>");
            out.println("<table border=1 width=50% height=50%>");
            out.println("<tr><th>Subject</th><th>Mid-term</th><th>Final</th><th>Attendance</th></tr>");

            for (int i = 0; i <6 ; i++) {
                ResultSet rp = stmt1.executeQuery("select * from Student join "+S[i]+" on Student.email="+S[i]+".email where email='"+Email+"'");
                while (rp.next())
                {
                    String Subject=rp.getString("Subject");
                    String mid = rp.getString("mid-term");
                    String finalexam  = rp.getString("final");
                    String p=rp.getString("participate")+"/"+rp.getString("total");

                    out.println("<tr><td>" + Subject + "</td><td>" + mid +"</td><td>"+finalexam+"</td><td>"+p+"</td></tr>");
                }
            }

            con.commit();
            con.close();
            out.println("</table>");
            out.println("</center>");
        } catch(Exception exe){System.out.println("Exception caught"+exe);}
    }



    }

