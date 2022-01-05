package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

@WebServlet(name = "AddStu2", value = "/AddStu2")
public class AddStu2 extends HttpServlet {
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
            String Address = request.getParameter("add");
            String Phone = request.getParameter("phone");
            String PPhone = request.getParameter("pphone");
            String HPhone = request.getParameter("hphone");
            String pwd = InitialPwGenerator.generate();
            String query = "insert into student (Firstname, Lastname, email, password, birth, gender, nation, address, phone, hphone, pphone ) values (" +
                        "'" + Firstname + "'," +
                        "'" + Lastname + "'," +
                        "'" + Email + "'," +
                    "'" + pwd + "'," +
                    "'" + Birth + "'," +
                    "'" + Gender + "'," +
                    "'" + Nation+ "'," +
                    "'" + Address + "'," +
                    "'" + Phone + "'," +
                    "'" + HPhone + "'," +
                        "'" + PPhone + "')";
            st.executeUpdate(query);


            RequestDispatcher rd = request.getRequestDispatcher("Admin.jsp");

            rd.include(request, response);
            out.println("<script>\n" +
                    "alert(\"Student added!\")" +
                    "</script>");
            con.close();

        } catch (Exception exe) {
            System.out.println("Exception caught" + exe);
        }
    }
}
