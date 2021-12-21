package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

@WebServlet(name = "AddTea", value = "/AddTea")
public class AddTea extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        try{
            Connection con = LocalConn.GetConn();
            Statement st = con.createStatement();

            String req = request.getParameter("names");
            String allinfo[] = req.split("\n");

            for (int i = 0; i < allinfo.length; i++){
                String allinfos[] = allinfo[i].split("/");
                String Firstname = allinfos[0];
                String Lastname = allinfos[1];
                String email = allinfos[2];
                String pwd = InitialPwGenerator.generate();
                String query = "insert into Teacher (Firstname, Lastname, email, password) values (" +
                        "'" + Firstname + "'," +
                        "'" + Lastname + "'," +
                        "'" + email + "'," +
                        "'" + pwd + "')";
                st.executeUpdate(query);
            }

            RequestDispatcher rd =request.getRequestDispatcher("Admin.jsp");

            rd.include(request, response);
            out.println("<h3>Teacher(s) successfully added!</h3>");
            con.close();

        } catch (Exception exe) {
            System.out.println("Exception caught" + exe);
        }
    }
}

