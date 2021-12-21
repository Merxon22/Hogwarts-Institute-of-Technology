package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.swing.plaf.nimbus.State;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

@WebServlet(name = "AddTea", value = "/AddTea")
public class AddTea extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Connection con = Provider.GetConn();
            Statement st = con.createStatement();

            String allinfo[] = request.getParameter("names").split("\n");

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

            RequestDispatcher rd =request.getRequestDispatcher("/AdminViewAccounts");
            rd.forward(request, response);



        }catch (Exception exe){
            System.out.println(exe);
        }
    }
}
