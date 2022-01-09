package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet(name = "Grading2", value = "/Grading2")
public class Grading2 extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter writer = response.getWriter();
            Connection con = Provider.GetConn();

            Statement stmt = con.createStatement();
            Statement stmt2 = con.createStatement();
            Statement stmt3 = con.createStatement();
            String clasx = request.getParameter("clasx");

            //先拿到我教的所有课


            response.setContentType("text/html");


            ArrayList<String> assignments = new ArrayList<>();
            ArrayList<Integer> stuids = new ArrayList<>();
            //选中这个课所有的quiz

            //Retrieving the data
            ResultSet rs = stmt.executeQuery("select * from " + clasx);
            ResultSetMetaData rsMetaData = rs.getMetaData();
            int count = rsMetaData.getColumnCount();
            for (int i = 1; i <= count; i++) {
                if (!(rsMetaData.getColumnName(i).equals("student_id") ||
                        rsMetaData.getColumnName(i).equals("attendance"))) {
                    assignments.add(rsMetaData.getColumnName(i));
                }
                //有了assignment
            }


            rs = stmt2.executeQuery("select * from " + clasx);
            while (rs.next()) { //打出标题所有学生的名字
                int tmpid = rs.getInt("student_id");
                stuids.add(tmpid);

            }
            String query3;


            for (int as = 0; as < assignments.size(); as++) {
                if(!assignments.get(as).equals("SemesterScore")) {
                    for (int s = 0; s < stuids.size(); s++) {

                        //System.out.println("Checkpoint2");
                        String Grade = "";
                        Grade = request.getParameter(clasx + assignments.get(as) + stuids.get(s));

                        query3 = "update " + clasx +
                                " set `" + assignments.get(as) + "`='" + Grade
                                + "' where student_id=" + stuids.get(s);


                        System.out.println(query3);
                        stmt3.executeUpdate(query3);
                    }
                }

            }

            RequestDispatcher rd = request.getRequestDispatcher("Grading2.jsp?clasx=" + clasx);
//            rd.include(request, response);
            rd.forward(request, response);
            writer.println("<script>\n" +
                    "alert(\"Grade(s) uploaded!\")" +
                    "</script>");

        }catch (Exception exe){
            System.out.println(exe);
        }
    }
}