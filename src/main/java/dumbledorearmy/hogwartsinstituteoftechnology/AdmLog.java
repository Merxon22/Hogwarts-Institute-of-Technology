package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "AdmLog", value = "/AdmLog")
public class AdmLog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String acc = request.getParameter("email");
        String pw = request.getParameter("pwd");
        boolean flag = false;
        response.setContentType("text/html");
        try{

            Connection con = LocalConn.GetConn();

            Statement stmt1 = con.createStatement();
            ResultSet rp = stmt1.executeQuery("select * from Admin");
            String em = "";
            String pwd = "";

            while (rp.next()){
                em = rp.getString("email");
                pwd = rp.getString("password");
                if (em.equals(acc) && pw.equals(pwd)) {
                    flag = true;
                    break;
                }
            }
            if (flag){
                RequestDispatcher rd =request.getRequestDispatcher("Admin.jsp");
                Cookie email = new Cookie("email", acc);

                email.setMaxAge(60 * 60 * 24);
                // Add both the cookies in the response header.
                response.addCookie(email);
                rd.include(request, response);
                out.println("<h1>Welcome Admin</h1>");

            }
            else {
                RequestDispatcher rd = request.getRequestDispatcher("AdminLog.jsp");
                rd.include(request, response);
                out.println("<h1>Wrong Com!</h1>");
            }

            con.close();
        } catch(Exception exe){System.out.println("Exception caught"+exe);}
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
