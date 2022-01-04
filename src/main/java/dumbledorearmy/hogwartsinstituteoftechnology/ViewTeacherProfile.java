package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet(name = "ViewTeacherProfile", value = "/ViewTeacherProfile")
public class ViewTeacherProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    int id= (int) request.getAttribute("id");
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    Connection con = Provider.GetConn();
    try{
        Statement stmt1=con.createStatement();

        ResultSet rs=stmt1.executeQuery("select * from teacher where id="+id);
        while(rs.next()){
            String fname=rs.getString("Firstname");
            String lname=rs.getString("Lastname");
            String email=rs.getString("email");
            String birth=rs.getString("birth");
            String gender=rs.getString("gender");
            String course=rs.getString("Class");
            String degree=rs.getString("degree");
            String nation=rs.getString("nation");
            String des=rs.getString("des");
            String phone=rs.getString("phone");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>View Grade</title>");
            out.println("<link rel=\"stylesheet\" href=\"css/mainStyle.css\">");
            out.println("<link rel=\"icon\" href=\"ResourceFolder/Icon.png\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<h2>"+fname+" "+lname+" Profile</h2>");
            out.println("<div><label>Date of Birth</label> "+birth+"</div>");
            out.println("<div><label>Gender</label> "+gender+"</div>");
            out.println("<div><label>Nationality</label> "+nation+"</div>");
            out.println("<div><label>Phone Number</label> "+phone+"</div>");
            out.println("<div><label>Email</label> "+email+"</div>");
            out.println("<div><label>Teaching Courses</label> "+course+"</div>");
            out.println("<div><label>Degrees</label> "+degree+"</div>");
            out.println("<br>");
            out.println("<div><p>"+des+"</p></div>");
            out.println("<a href=\"StuBack\" style=\"width: 80px;\"><button class=\"btn btn-primary\" style=\"width: 80px; margin-top: 20px;\" type=\"button\">Back</button></a>");
            out.println("</body>");
            out.println("</html>");




        }


    } catch (Exception exe) {
        System.out.println("Exception caught" + exe);
    }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
