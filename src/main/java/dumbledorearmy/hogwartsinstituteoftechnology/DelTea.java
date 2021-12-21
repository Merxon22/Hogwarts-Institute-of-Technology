package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

@WebServlet(name = "DelTea", value = "/DelTea")
public class DelTea extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Connection con = Provider.GetConn();

            Statement st = con.createStatement();

            String req = request.getParameter("names");
            String allname[] = req.split("\n");
            String name;

            for (int i = 0; i < allname.length; i++){
                String query = "delete from Student where names="+allname[i];
            }

            RequestDispatcher rd =request.getRequestDispatcher("/AdminViewAccounts");
            rd.forward(request, response);

        } catch (Exception exe) {
            System.out.println("Exception caught" + exe);
        }
    }
}
