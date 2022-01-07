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
            String assignments[] = request.getParameter("tests").split(",");
            String claa = request.getParameter("clasx");

            int sum=0;
            Connection con = Provider.GetConn();
            Statement stmt = con.createStatement();
            Statement stmt1 = con.createStatement();
            Statement stmt2 = con.createStatement();

            System.out.println(assignments.length);

            ResultSet rs;
            ArrayList<Integer> percentage = new ArrayList<>();
            for (String cc: assignments){
                if (cc.length() >= 2) {
                    System.out.println(cc);
                    String txx = request.getParameter(claa + "_" + cc);
                    System.out.println(txx);
                    int tmp = Integer.parseInt(txx);
                    percentage.add(tmp);
                    sum += tmp;
                }
            }

            if (sum != 100){
                RequestDispatcher rd = request.getRequestDispatcher("CalFinal.jsp?clasx=" + claa + "&msg=1");
                rd.forward(request, response);
            }
            else{

                String query4 = "SELECT COUNT(*) FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='" + claa + "' AND COLUMN_NAME='GPA'";
                ResultSet rs3 = stmt.executeQuery(query4);
                int ct = 0;
                while (rs3.next()){
                    ct = rs3.getInt("COUNT(*)");
                }
                if (ct == 0){
                    stmt.executeUpdate("alter table " + claa + " add column GPA int default 0");
                }
                String query = "select student_id from " + claa;

                rs = stmt1.executeQuery(query);
                while (rs.next()) {
                    int stu = rs.getInt("student_id");
                    int fin = 0;

                    for (int as = 0; as < assignments.length; as++) {
                        //fin += percentage.get(as);
                        String query2 = "select `" + assignments[as] + "` from " + claa + " where " +
                                "student_id=" + stu;
                        ResultSet rs2 = stmt2.executeQuery(query2);
                        while (rs2.next()) {
                            int original = rs2.getInt(assignments[as]);
                            fin += original * percentage.get(as) / 100;
                        }
                        //把final update进去
                        String query3 = "update " + claa + " set GPA =" + fin + " where student_id=" + stu;
                        stmt2.executeUpdate(query3);
                    }
                }
            }
            request.removeAttribute("clasx");
            request.removeAttribute("clasx");
            request.removeAttribute("clasx");
            request.setAttribute("clasx", claa);
            System.out.println(claa);
            RequestDispatcher rd2 = request.getRequestDispatcher("Grading2.jsp");


            rd2.include(request, response);
            out.println("<script>\n" +
                    "alert(\"You have updated GPA!!\")" +
                    "</script>");



        }catch (Exception exe){
            System.out.println(exe);
        }

    }
}
