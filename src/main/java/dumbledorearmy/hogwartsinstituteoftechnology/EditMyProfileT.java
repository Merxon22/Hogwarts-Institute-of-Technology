package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "EditMyProfileT", value = "/EditMyProfileT")
public class EditMyProfileT extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        try{
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            Connection con = Provider.GetConn();
            StringBuffer buf=new StringBuffer();
            Cookie[] cookies = request.getCookies();
            String em = "";
            for (Cookie c: cookies){
                if (c.getName().equals("email")){
                    em = c.getValue();
                }
            }
            int id=0;
            Statement stmt1=con.createStatement();
            Statement stmt2=con.createStatement();
            ResultSet rs4 = stmt1.executeQuery("select * from teacher where email='" + em + "'" );
            while(rs4.next()){
                id = rs4.getInt("id");
            }


            ResultSet rs=stmt2.executeQuery("select * from teacher where id="+id);
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
                buf.append(course);
                out.println("<html>");
                out.println("<head>");
                out.println("<title>My Profile</title>");
                out.println("<link rel=\"stylesheet\" href=\"css/mainStyle.css\">");
                out.println("<link rel=\"stylesheet\" href=\"css/profile.css\">");
                out.println("<link rel=\"icon\" href=\"ResourceFolder/Icon.png\">");
                out.println("</head>");
                out.println("<body background=\"https://www.gozetim.com/images/kirtasiye-testleri.jpg\" style=\"background-size: cover\"><center>");
                request.getRequestDispatcher("module/headerLoggedIn.jsp").include(request, response);
                out.println("<div id=\"containerBox\">");
                out.println("<div class=\"centerBox\" style=\"width: 60%; !important;\">");
                request.getRequestDispatcher("module/CheckLog.jsp").include(request, response);
                out.println("<div id=\"mainInfo\">");
                out.println("<h2>"+fname+" "+lname+"</h2><a href=\"EditMyProfileT2?id="+id+"\"></a>");
                out.println("<svg width=\"40\" height=\"40\"><circle cx=\"20\" cy=\"20\" r=\"16\" fill=\"888888\"></svg>");
                out.println("</div>");
                out.println("<div><label>Date of Birth</label> "+birth+"</div>");
                out.println("<div><label>Gender</label> "+gender+"</div>");
                out.println("<div><label>Nationality</label> "+nation+"</div>");
                out.println("<div><label>Phone Number</label> "+phone+"</div>");
                out.println("<div><label>Email</label> "+email+"</div>");
                out.println("<div><label>Teaching Courses</label> "+buf.substring(0,buf.length()-1)+"</div>");
                out.println("<div><label>Degrees</label> "+degree+"</div>");
                out.println("<br>");
                out.println("<div><p>"+des+"</p></div>");
                out.println("<a href=\"StuBack\" style=\"width: 80px;\"><button class=\"btn btn-primary\" style=\"width: 80px; margin-top: 20px;\" type=\"button\">Back</button></a>");
                out.println("</div>");
                out.println("</div>");
                request.getRequestDispatcher("module/footer.jsp").include(request, response);
                out.println("</center></body>");
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
