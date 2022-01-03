package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Statement;

@WebServlet(name = "AddAss", value = "/AddAss")
public class AddAss extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            Connection con = Provider.GetConn();
            String allAss[] = request.getParameter("ass").split("\n");
            String que;
            Statement stmt = con.createStatement();
            int count = 0;
            for (String as: allAss){
                String sep[] = as.split("/");
                String cN = sep[0];
                String className = cN.substring(0, 1).toUpperCase() + cN.substring(1, cN.length());
                String assName = sep[1];
                if (count != allAss.length - 1){
                    assName = assName.substring(0, assName.length() - 1);
                }


                System.out.println(assName);
                que = "ALTER TABLE " + className + "\n" +
                        "ADD COLUMN `" + assName + "` varchar(64) DEFAULT 'N/A';";
                stmt.executeUpdate(que);
                count++;
            }
            RequestDispatcher rd = request.getRequestDispatcher("Teacher.jsp");

            rd.include(request, response);
            out.println("<script>\n" +
                    "alert(\"Assignment(s) added!\")" +
                    "</script>");
            con.close();

        }catch (Exception exe){
            System.out.println(exe);
        }
    }
}