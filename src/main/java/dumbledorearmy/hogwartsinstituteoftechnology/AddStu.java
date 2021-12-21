package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

//Tano/Ahsoka/ATano@hit.edu/10/A

@WebServlet(name = "AddStu", value = "/AddStu")
public class AddStu extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Connection con = Provider.GetConn();

            Statement st = con.createStatement();

            String req = request.getParameter("names");
            String allinfo[] = req.split("\n");

            for (int i = 0; i < allinfo.length; i++){
                String allinfos[] = allinfo[i].split("/");
                String Firstname = allinfos[0];
                String Lastname = allinfos[1];
                String email = allinfos[2];
                String pwd = InitialPwGenerator.generate();
                String grade = allinfos[3];
                String cla = allinfos[4];
                String query = "insert into Students (Firstname, Lastname, email, password, grade, class) values (" +
                        "'" + Firstname + "'," +
                        "'" + Lastname + "'," +
                        "'" + email + "'," +
                        "'" + pwd + "'," +
                        "'" + grade + "'," +
                        "'" + cla + "')";
                st.executeUpdate(query);
            }

            RequestDispatcher rd =request.getRequestDispatcher("/AdminViewAccounts");
            rd.forward(request, response);


        } catch (Exception exe) {
            System.out.println("Exception caught" + exe);
        }
    }
}
