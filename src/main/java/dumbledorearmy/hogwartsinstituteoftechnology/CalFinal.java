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

@WebServlet(name = "CalFinal", value = "/CalFinal")
public class CalFinal extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //first check whether sum to 100?
        try{
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            String claa = request.getParameter("clasx");
            int sum=0;
            Connection con = Provider.GetConn();
            Statement stmt = con.createStatement();
            Statement stmt1 = con.createStatement();
            Statement stmt2 = con.createStatement();



            ResultSet rs = stmt.executeQuery("select * from " + claa);
            ResultSetMetaData rsMetaData = rs.getMetaData();
            ArrayList<String> assignments = new ArrayList<>();
            ArrayList<Integer> percentage = new ArrayList<>();

            int count = rsMetaData.getColumnCount();
            for (int i = 1; i <= count; i++) {
                if (!(rsMetaData.getColumnName(i).equals("student_id") ||
                        rsMetaData.getColumnName(i).equals("attendance")
                || rsMetaData.getColumnName(i).equals("GPA"))) {
                    assignments.add(rsMetaData.getColumnName(i));
                }
                //有了assignment
            }

            for (String cc: assignments){
                System.out.println(cc);
                String txx = request.getParameter(claa + "_" + cc);
                System.out.println(txx);
                int tmp = Integer.parseInt(txx);
                percentage.add(tmp);
                sum += tmp;
            }

            if (sum != 100){
//                request.setAttribute("clasx", claa);
//                request.setAttribute("msg", "1");
                RequestDispatcher rd = request.getRequestDispatcher("CalFinal.jsp?clasx=" + claa + "&msg=1");
                rd.forward(request, response);
            }
            else{
                stmt.executeUpdate("alter table " + claa + " drop column GPA");
                stmt.executeUpdate("alter table " + claa + " add column GPA int default 0");

                //update final for each student
                String query = "select student_id from " + claa;

                //

                rs = stmt1.executeQuery(query);

                while (rs.next()) {
                    int stu = rs.getInt("student_id");
                    int fin = 0;

                    for (int as = 0; as < assignments.size(); as++) {
                        //fin += percentage.get(as);

                        String query2 = "select `" + assignments.get(as) + "` from " + claa + " where " +
                                "student_id=" + stu;
                        ResultSet rs2 = stmt2.executeQuery(query2);
                        while (rs2.next()) {
                            int original = rs2.getInt(assignments.get(as));
                            fin += original * percentage.get(as) / 100;
                        }
                        //把final update进去


                        String query3 = "update " + claa + " set GPA =" + fin + " where student_id=" + stu;
                        stmt2.executeUpdate(query3);
                    }
                }
            }
            RequestDispatcher rd2 = request.getRequestDispatcher("Grading.jsp");
            rd2.include(request, response);
            out.println("<script>\n" +
                    "alert(\"You have updated GPA!!\")" +
                    "</script>");



        }catch (Exception exe){
            System.out.println(exe);
        }

    }
}
