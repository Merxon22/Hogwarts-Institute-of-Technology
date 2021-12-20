package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

@WebServlet(name = "SignUpTeacher", value = "/SignUpTeacher")
public class SignUpTeacher extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fname= request.getParameter("TSfirstname");
        String lname= request.getParameter("TSlastname");
        String em = request.getParameter("TSemail");
        String pw = request.getParameter("TSpassword");
        String cpw = request.getParameter("TSconpassword");


        if(pw.equals(cpw)){

            try{
                Connection con = Provider.GetConn();

                Statement stmt=con.createStatement();
                String Qs="insert into students values('"+fname+"','"+lname+"','"+em+"','"+pw+"')";

                stmt.executeUpdate(Qs);

                RequestDispatcher rd=request.getRequestDispatcher("Admin.jsp");
                rd.forward(request, response);
                con.close();

            }catch(Exception e){System.out.println(e);}

        }else
        {
            RequestDispatcher rd=request.getRequestDispatcher("SignUpFail.jsp");
            rd.include(request, response);
        }
    }
    }

