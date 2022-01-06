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

@WebServlet(name = "EditMyProfile", value = "/EditMyProfile")
public class EditMyProfile extends HttpServlet {
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
        try{
            Statement stmt1=con.createStatement();
            Statement stmt2=con.createStatement();
            int id = 0;
            Statement stmt = con.createStatement();
            ResultSet rs3= stmt2.executeQuery("select student.id from student where email='"+Email+"'");
            while (rs3.next()){
                id=rs3.getInt("id");
            }
            ResultSet rs=stmt1.executeQuery("select * from student where id="+id);

            while(rs.next()){
                String fname=rs.getString("Firstname");
                String lname=rs.getString("Lastname");
                String email=rs.getString("email");
                String birth=rs.getString("birth");
                String gender=rs.getString("gender");
                String add=rs.getString("address");
                String nation=rs.getString("nation");
                String pphone=rs.getString("pphone");
                String phone=rs.getString("phone");
                String hphone=rs.getString("hphone");
//                out.println("<html>");
//                out.println("<head>");
//                out.println("<title>View Student Profile</title>");
//                out.println("<link rel=\"stylesheet\" href=\"css/mainStyle.css\">");
//                out.println("<link rel=\"icon\" href=\"ResourceFolder/Icon.png\">");
//                out.println("</head>");
//                out.println("<body>");
//                out.println("<h2>"+fname+" "+lname+" Profile</h2>");
//                out.println("<div><label>Date of Birth</label> "+birth+"</div>");
//                out.println("<div><label>Gender</label> "+gender+"</div>");
//                out.println("<div><label>Nationality</label> "+nation+"</div>");
//                out.println("<div><label>Phone Number</label> "+phone+"</div>");
//                out.println("<div><label>Parent's Phone Number</label> "+pphone+"</div>");
//                out.println("<div><label>Home Number</label> "+hphone+"</div>");
//                out.println("<div><label>Email</label> "+email+"</div>");
//                StringBuilder Class=new StringBuilder();
//                for (int i = 0; i <sub.size(); i++) {
//                    ResultSet rp2=stmt2.executeQuery("select * from "+sub.get(i)+" where student_id="+id);
//                    while(rp2.next()){
//                        Class.append(sub.get(i)+" ");
//                    }
//                }
//
//                out.println("<div><label>Class taken</label> "+Class+"</div>");
//                out.println("<div><label>Address</label> "+add+"</div>");
//                out.println("<br>");
//                out.println("<a href=\"StuBack\" style=\"width: 80px;\"><button class=\"btn btn-primary\" style=\"width: 80px; margin-top: 20px;\" type=\"button\">Back</button></a>");
//                out.println("</body>");
//                out.println("</html>");
//

                out.println("<html>");
                out.println("<head>");
                out.println("<title>My Profile</title>");
                out.println("<link rel=\"stylesheet\" href=\"css/mainStyle.css\">");
                out.println("<link rel=\"stylesheet\" href=\"css/profile.css\">");
                out.println("<link rel=\"stylesheet\" href=\"css/schedule.css\">");
                out.println("<link rel=\"icon\" href=\"ResourceFolder/Icon.png\">");
                out.println("</head>");
                out.println("<body background=\"https://www.gozetim.com/images/kirtasiye-testleri.jpg\" style=\"background-size: cover\"><center>");
                request.getRequestDispatcher("module/headerLoggedIn.jsp").include(request, response);
                out.println("<div id=\"containerBox\">");
                out.println("<div class=\"centerBox\" style=\"width: 60%; !important;\">");
                request.getRequestDispatcher("module/CheckLog.jsp").include(request, response);
                out.println("<div id=\"mainInfo\">");
                out.println("<div>");
                out.println("<h5 style=\"color: #dddddd; margin-bottom: -10px\"><i>Student</i></h5>");
                out.println("<h2><b>"+fname+" "+lname+"</h2><a href=\"EditMyProfileT2?id="+id+"\"></b></h2></a>");
                out.println("</div>");
                out.println("<svg width=\"40\" height=\"40\"><circle cx=\"20\" cy=\"20\" r=\"16\" fill=\"888888\"></svg>");
                out.println("</div>");
                out.println("<div style=\"display: flex; justify-content: space-between; flex-direction: row\">");
                out.println("<div id=\"bottomLeftBox\">");
                out.println("<div><label>Email:</label> "+email+"</div>");
                out.println("<div><label>Phone Number:</label> "+phone+"</div>");
                out.println("<div><label>Parent's Phone Number:</label> "+pphone+"</div>");
                out.println("<div><label>Gender:</label>"+gender+"</div>");
                out.println("<div><label>Home Number:</label> "+hphone+"</div>");
                out.println("<div><label>Address:</label>"+add+"</div>");
                out.println("</div>");
                out.println("<div id=\"bottomRightBox\">");
                StringBuilder Class =new StringBuilder();
                for (int i = 0; i <sub.size(); i++) {
                    ResultSet rp2=stmt2.executeQuery("select * from "+sub.get(i)+" where student_id="+id);
                    while(rp2.next()) {
                        Class.append(sub.get(i) + "<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;");
                    }
                }
                out.println("<div><label>Class taken:</label>"+Class+"</div>");
                out.println("</div>");
                out.println("</div>");
                out.println("<a href=\"TeaBack\" style=\"width: 80px;\"><button class=\"btn btn-primary\" style=\"width: 80px; margin-top: 20px;\" type=\"button\">Back</button></a>");
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
