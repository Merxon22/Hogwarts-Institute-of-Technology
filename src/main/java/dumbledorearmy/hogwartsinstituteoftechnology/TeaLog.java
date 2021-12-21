package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "TeaLog", value = "/TeaLog")
public class TeaLog extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acc = request.getParameter("email");
        String pwd = request.getParameter("pwd");
        try{
            Connection con = Provider.GetConn();

            Statement st = con.createStatement();

            String query = "SELECT EXISTS(SELECT * from Teacher WHERE email=('" + acc + "')";


            ResultSet nRows = st.executeQuery(query);
            int a = nRows.getRow();
            System.out.println(a);

            if (a == 0){
                RequestDispatcher rd =request.getRequestDispatcher("TeaLog.jsp");
                rd.forward(request, response);
            }

            else{
                query = "SELECT * from Teacher WHERE email=('" + acc + "')";
                nRows = st.executeQuery(query);
                String pwdcorr = nRows.getString("password");
                if (pwd.equals(pwdcorr)){
                    RequestDispatcher rd = request.getRequestDispatcher("Teacher.jsp"); //pending
                    Cookie email = new Cookie("email", acc);
                    Cookie pwdd = new Cookie("password", pwd);
                    email.setMaxAge(60 * 60 * 24);
                    pwdd.setMaxAge(60 * 60 * 24);
                    response.addCookie(email);
                    response.addCookie(pwdd);
                    rd.forward(request, response);
                }
                else{
                    RequestDispatcher rd =request.getRequestDispatcher("TeaLog.jsp");
                    rd.forward(request, response);
                }
            }

        }catch (Exception exe){
            System.out.println(exe);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {



    }
}
