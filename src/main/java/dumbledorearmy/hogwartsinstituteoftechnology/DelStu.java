package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

@WebServlet(name = "DelStu", value = "/DelStu")
public class DelStu extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            Connection con = Provider.GetConn();
            Statement st = con.createStatement();

            String req = request.getParameter("names");
            String allname[] = req.split("\n");
            System.out.println(allname.length);

            for (int i = 0; i < allname.length; i++){
                System.out.println(allname[i]);
                System.out.println(allname[i].substring(allname[i].length() - 1));
            }


//            query = "SET FOREIGN_KEY_CHECKS=0;";


            for (int i = 0; i < allname.length; i++){
                String q1 = "SET FOREIGN_KEY_CHECKS=0;";
                st.executeQuery(q1);
                if (i != allname.length - 1) {
                    allname[i] = allname[i].substring(0, allname[i].length() - 1);
                }
//
                String query = "delete from student where email='"+ allname[i] + "'"; //small change
                System.out.println(query);
                st.executeUpdate(query);
            }

            RequestDispatcher rd = request.getRequestDispatcher("Admin.jsp");
            rd.include(request, response);
            out.println("<script>\n" +
                    "function myFunction() {\n" +
                    "  alert(\"Student(s) Deleted!\");\n" +
                    "}\n" +
                    "</script>");
        } catch (Exception exe) {
            System.out.println("Exception caught" + exe);
        }
    }
}
