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
        try{

            response.setContentType("text/html");
            Statement stmt1 = con.createStatement();
            ResultSet rp = stmt1.executeQuery("select * from Student where email='"+Email+"'");


            while (rp.next())
            {
                String name = rp.getString("Firstname")+" "+rp.getString("Lastname");
                String mid = rp.getString("mid-term");
                String finalexam  = rp.getString("final");
                String p=rp.getString("participate")+"/"+rp.getString("total");

                out.println("<tr><td>" + name + "</td><td>" + mid +"</td><td>"+finalexam+"</td><td>"+p+"</td></tr>");
            }
            con.commit();
            con.close();
        } catch(Exception exe){System.out.println("Exception caught"+exe);}
    }



    }
}
