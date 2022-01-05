package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

@WebServlet(name = "AddTea2", value = "/AddTea2")
public class AddTea2 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        try{
            Connection con = Provider.GetConn();
            Statement st = con.createStatement();

            String Firstname = request.getParameter("fname");
            String Lastname = request.getParameter("lname");
            String Email = request.getParameter("email");
            String Gender = request.getParameter("gender");
            String Birth = request.getParameter("birth");
            String Nation = request.getParameter("nation");
            String Des = request.getParameter("des");
            String Phone = request.getParameter("phone");
            String De = request.getParameter("de");
            String Class = request.getParameter("class");
            String pwd = InitialPwGenerator.generate();
            String query = "insert into teacher (Firstname, Lastname, email, password, Class, birth, gender, degree , nation, des, phone ) values (" +
                    "'" + Firstname + "'," +
                    "'" + Lastname + "'," +
                    "'" + Email + "'," +
                    "'" + pwd + "'," +
                    "'" + Class + "'," +
                    "'" + Birth + "'," +
                    "'" + Gender+ "'," +
                    "'" + De + "'," +
                    "'" + Nation + "'," +
                    "'" + Des + "'," +
                    "'" + Phone + "')";
            st.executeUpdate(query);


            RequestDispatcher rd = request.getRequestDispatcher("Admin.jsp");

            rd.include(request, response);
            out.println("<script>\n" +
                    "alert(\"Teacher added!\")" +
                    "</script>");
            con.close();

        } catch (Exception exe) {
            System.out.println("Exception caught" + exe);
        }
    }
}
