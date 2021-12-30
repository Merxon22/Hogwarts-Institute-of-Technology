package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

@WebServlet(name = "DelTea", value = "/DelTea")
public class DelTea extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            Connection con = Provider.GetConn();
            Statement st = con.createStatement();
            String req = request.getParameter("names");
            String allname[] = req.split("\n");
            for (int i = 0; i < allname.length; i++){
                String query = "delete from Teacher where email='"+allname[i] + "'"; //small change
                st.executeUpdate(query);
            }
            RequestDispatcher rd =request.getRequestDispatcher("Admin.jsp");
            rd.include(request, response);
            out.println("<script>\n" +
                    "function myFunction() {\n" +
                    "  alert(\"Teacher(s) Deleted!\");\n" +
                    "}\n" +
                    "</script>");
        } catch (Exception exe) {
            System.out.println("Exception caught" + exe);
        }
    }
}
