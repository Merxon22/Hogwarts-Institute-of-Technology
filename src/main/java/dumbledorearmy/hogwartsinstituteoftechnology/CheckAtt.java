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

@WebServlet(name = "CheckAtt", value = "/CheckAtt")
public class CheckAtt extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try{
            Connection con = Provider.GetConn();
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            String date = Dates.getDate();
            String week = Dates.getWeek();

            Cookie[] cookies = request.getCookies();
            Statement stmt = con.createStatement();
            Statement stmt1 = con.createStatement();
            String em = "";
            for (Cookie c: cookies){
                if (c.getName().equals("email")){
                    em = c.getValue();
                }
            }

            System.out.println(em);

            String[] classes = {}; //记录下老师的所有课

            String query;
            query = "select * from teacher where email='" + em + "';" ;

            ResultSet rs = stmt.executeQuery(query);

            int teaid = 0;
            while(rs.next()){
                classes = rs.getString("Class").split(",");
                teaid = rs.getInt("id");
            }

            //System.out.println(classes[0]);

            ArrayList<String> subjects = new ArrayList<>();
            ArrayList<String> times = new ArrayList<>();

            for (String clasx : classes) {
                query = "select classinfo.time, classinfo.total from classinfo where subject='" + clasx + "'";
                //如果对上weekday是今天的，那么把这节课加到subjects，time里
                int total = 0;

                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String[] timx = rs.getString("time").split("<br>");
                    total = rs.getInt("total");

                    for (String t : timx) {
                        String[] whole = t.split(" ");
                        String weekdayx = whole[0];
                        String classtime = whole[1];

                        if (weekdayx.equals(week)) {
                            subjects.add(clasx);
                            times.add(classtime);
                        }
                    }
                }

                String query3 = "update classinfo set total=" + (total+1);
                stmt1.executeUpdate(query3);
            }
            //这步存了老师教的课，和课的时间
            //然后通过这个课找到学生的id

            for (int time = 0; time < subjects.size(); time++) { //对这个老师今天的每节课都搞过一次了，每个课就是一个table
                String subj = subjects.get(time);
                String classt = times.get(time);

                //找到选这个课的学生的邮箱

                query = "select student_id from " + subj;

                rs = stmt.executeQuery(query);


                while (rs.next()) {
                    int stuid = rs.getInt("student_id");
                    String param = classt + "_" + subj + "_" + stuid;
                    String checked = request.getParameter(param);
//                        System.out.println(checked);
                    if (checked != null) {
                        query = "select attendance from " + subj + " where student_id=" + stuid;
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

                        query = "update " + subj +
                                " set attendance=" + newAtt + " where student_id=" + stuid;
                        stmt1.executeUpdate(query);
                    }

                }
            }
            query = "insert into checkcheck (teacher_id, date) values(" + teaid + ", '" + date + "');";
            stmt.executeUpdate(query);

            con.close();
            RequestDispatcher rd = request.getRequestDispatcher("Teacher.jsp");
            rd.include(request, response);
            out.println("<script>\n" +
                    "alert(\"Attendance checked!\")" +
                    "</script>");


        }catch (Exception exe){
            System.out.println(exe);
        }
    }
}
