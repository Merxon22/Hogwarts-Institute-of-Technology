package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "ValidateAdmin", value = "/ValidateAdmin")
public class ValidateAdmin extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String un=request.getParameter("Adusername");
        String pw=request.getParameter("Adpassword");

        String un1="Tang";
        String pw1="20040713";

        String un2="Peter";
        String pw2="123456";

        String un3="Barbara";
        String pw3="123456";
        if(Objects.equals(un, un1) && Objects.equals(pw, pw1)){
            RequestDispatcher rd=request.getRequestDispatcher("Admin.jsp");
            rd.forward(request,response);
        }else if(Objects.equals(un, un2) && Objects.equals(pw, pw2)){
            RequestDispatcher rd=request.getRequestDispatcher("Admin.jsp");
            rd.forward(request,response);
        }else if(Objects.equals(un, un3) && Objects.equals(pw, pw3)){
            RequestDispatcher rd=request.getRequestDispatcher("Admin.jsp");
            rd.forward(request,response);
        }else{
            String msg = "Invalid Username or Password";
            response.sendRedirect("AdminLogin.jsp?msg="+msg);
        }
    }
    }
