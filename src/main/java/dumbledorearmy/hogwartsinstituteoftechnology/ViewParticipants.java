package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import javax.xml.bind.SchemaOutputResolver;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Locale;

@WebServlet(name = "ViewParticipants", value = "/ViewParticipants")
public class ViewParticipants extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    try{
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        out.println("<html>");
        Connection con = Provider.GetConn();
        Statement stmt = con.createStatement();

        Cookie[] cookies = request.getCookies();
        String em = "";
        for (Cookie c: cookies){
            if (c.getName().equals("email")){
                em = c.getValue();
            }
        }

        System.out.println(em);

        String[] classes = {};

        String query;
        query = "select * from teacher where email='" + em + "';" ;
        ResultSet rs = stmt.executeQuery(query);
        while(rs.next()){
            classes = rs.getString("Class").split(",");
        }

        System.out.println(classes[0]);

        //这里获得了一个(些)代表课的表，然后我们把它和student用fk（id）连起来，就可以得到firstn, lastn, email了

        for (String clasx: classes){ //注意这里前段得考虑一个老师教多个课的情况，虽然我们现在不需要
            System.out.println(clasx);
            query = "select student.Firstname, student.Lastname, student.email from student " +
                    "join " + clasx + " on " + clasx + "." + "student_id = student.id";


            System.out.println(query);
            rs = stmt.executeQuery(query);


            out.println("<table>\n" +
                    "        <thead>\n" +
                    "            <tr><th colspan = '3'>");
            out.println(clasx.substring(0, 1).toUpperCase(Locale.ROOT) + clasx.substring(1, clasx.length()));

            out.println("</th><tr>\n" +
                    "        </thead>\n" +
                    "        <tbody>");

            out.println("<tr><th>First Name</th>\n" +
                    "            <th>\n" +
                    "                Last Name\n" +
                    "            </th>\n" +
                    "            <th>\n" +
                    "                Email\n" +
                    "            </th><tr>");
            while (rs.next()){
                out.println("<tr><td>" + rs.getString("Firstname") + "</td>");
                out.println("<td>" + rs.getString("Lastname") + "</td>");
                out.println("<td>" + rs.getString("Email") + "</td><tr>");
            }
        }
        out.println("</html>");
        con.close();



    }catch (Exception exe){
        System.out.println(exe);
    }




    }

}
