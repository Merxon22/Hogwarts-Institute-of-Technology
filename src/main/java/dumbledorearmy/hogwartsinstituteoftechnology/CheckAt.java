package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

@WebServlet(name = "CheckAt", value = "/CheckAt")
public class CheckAt extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Connection con = Provider.GetConn();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            String date = Dates.getDate();
            String week = Dates.getWeek();

            Statement stmt = con.createStatement();
            Statement stmt1 = con.createStatement();
            String claa = request.getParameter("clasx");


            String query;


            //System.out.println(classes[0]);


            query = "select classinfo.time, classinfo.total from classinfo where subject='" + claa + "'";
            //如果对上weekday是今天的，那么把这节课加到subjects，time里



            int total;

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String[] timx = rs.getString("time").split("<br>");
                total = rs.getInt("total");

                for (String t : timx) {
                    String[] whole = t.split(" ");
                    String weekdayx = whole[0];
                    String classtime = whole[1];

                    if (weekdayx.equals(week)) {
                        String query3 = "update classinfo set total=" + (total+1) + " where subject='" + claa + "'";
                        stmt1.executeUpdate(query3);
                        query = "select student_id from " + claa;

                        rs = stmt.executeQuery(query);


                        while (rs.next()) {
                            int stuid = rs.getInt("student_id");
                            String param = classtime + "_" + claa + "_" + stuid;
                            String checked = request.getParameter(param);
//                          System.out.println(checked);
                            if (checked != null) {
                                query = "select attendance from " + claa + " where student_id=" + stuid;
                                ResultSet rs2 = stmt1.executeQuery(query);
                                int att = 0;
                                int newAtt = 0;

                                while (rs2.next()) {
                                    att = rs2.getInt("attendance");
                                    System.out.println("original att " + att);
                                    System.out.println("enter");
                                }
                                newAtt = att+1;
                                System.out.println("new attendence " + newAtt);


                                query = "update " + claa +
                                        " set attendance=" + newAtt + " where student_id=" + stuid;
                                stmt1.executeUpdate(query);
                                System.out.println("c0");
                            }
                        }
                    }
                }
            }
            System.out.println("c1");


            query = "insert into checkcheck (subject, date) values('" + claa + "', '" + date + "');";
            stmt.executeUpdate(query);

            System.out.println("c2");

            con.close();
            RequestDispatcher rd = request.getRequestDispatcher("Attendance.jsp");
            rd.include(request, response);
            out.println("<script>\n" +
                    "alert(\"Attendance checked!\")" +
                    "</script>");


        }catch (Exception exe){
            System.out.println(exe);
        }
    }
}