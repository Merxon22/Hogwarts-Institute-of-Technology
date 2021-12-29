package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLOutput;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(name = "ViewSche", value = "/ViewSche")
public class ViewSche extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //现在有老师的邮箱，通过邮箱得到老师的id，这个和classinfo的id一连起来，就可以获得时间了，接下来就是排版了


        HashMap<String, Integer> mapWeek = new HashMap<>();
        HashMap<String, Integer> mapClass = new HashMap<>();
        mapWeek.put("Monday", 0);
        mapWeek.put("Tuesday", 1);
        mapWeek.put("Wednesday", 2);
        mapWeek.put("Thursday", 3);
        mapWeek.put("Friday", 4);
        mapClass.put("8:30-9:25", 0);
        mapClass.put("9:30-10:25", 1);
        mapClass.put("10:30-11:25", 2);
        mapClass.put("11:30-12:25", 3);
        mapClass.put("13:15-14:10", 4);
        mapClass.put("14:15-15:10", 5);
        mapClass.put("15:15-16:10", 6);

        try{
            ScheduleHelper.initialize();
            HashMap<String, Integer> map5= ScheduleHelper.getMapWeek();
            HashMap<String, Integer> map7= ScheduleHelper.getMapClass();

            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<html>");

            Cookie[] cookies = request.getCookies();
            String em = "";
            for (Cookie c: cookies){
                if (c.getName().equals("email")){
                    em = c.getValue();
                }
            }
            int id = 0;
            Connection con = Provider.GetConn();
            Statement stmt = con.createStatement();
            String query = "select * from teacher where email='" + em + "';";

            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                id = rs.getInt("id");
            }
            System.out.println(id);
            query = "select classinfo.time, classinfo.subject from classinfo" +
                    " join teacher on teacher_id = teacher.id where teacher_id=" + id;

            System.out.println(query);
            rs = stmt.executeQuery(query);
            String[][] week = new String[5][7];//对应一整周，5天
            while(rs.next()) { //默认我们后期会一个老师教很多课，但只有1
                String time = rs.getString("time");
                String[] times = time.split("<br>");
                for (String aTime: times){
                    String[] whole = aTime.split(" ");

                    System.out.println(whole[0]);
                    System.out.println(whole[1]);

                    System.out.println(mapWeek.get(whole[0]));
                    System.out.println(mapClass.get(whole[1]));

                    int weekday = mapWeek.get(whole[0]);
                    int period = mapClass.get(whole[1]);
                    week[weekday][period] = rs.getString("subject");
                }
            }
            out.println("<table>\n" +
                    "    <thead>\n" +
                    "    <tr>\n" +
                    "        <th>Time/Weekday</th>\n" +
                    "        <th>Monday</th>\n" +
                    "        <th>Tuesday</th>\n" +
                    "        <th>Wednesday</th>\n" +
                    "        <th>Thursday</th>\n" +
                    "        <th>Friday</th>\n" +
                    "    </tr>\n" +
                    "    </thead>\n" +
                    "    <tbody>\n" +
                    "    <tr>\n" +
                    "        <td>8:30-9:25</td>");

            for (int per = 0; per < 5; per++){
                if (!(week[per][0]==null)){
                    out.println("<td>" + week[0][per] + "</td>");
                }
                else{
                    out.println("<td>Free Period</td>");
                }
            }

            out.print("</tr>\n" +
                    "    <tr>\n" +
                    "        <td>9:30-10:25</td>");

            for (int per = 0; per < 5; per++){
                if (!(week[per][1] == null)){
                    out.println("<td>" + week[per][1] + "</td>");
                }
                else{
                    out.println("<td>Free Period</td>");
                }
            }

            out.println("</tr>\n" +
                    "    <tr>\n" +
                    "        <td>10:30-11:25</td>");

            for (int per = 0; per < 5; per++){
                if (!(week[per][2]==null)){
                    out.println("<td>" + week[per][2] + "</td>");
                }
                else{
                    out.println("<td>Free Period</td>");
                }
            }
            out.println("</tr>\n" +
                    "    <tr>\n" +
                    "        <td>11:30-12:25</td>");

            for (int per = 0; per < 5; per++){
                if (!(week[per][3]==null)){
                    out.println("<td>" + week[per][3] + "</td>");
                }
                else{
                    out.println("<td>Free Period</td>");
                }
            }

            out.println("</tr>\n" +
                    "    <tr>\n" +
                    "        <td id = \"spec\" colspan=\"6\">Lunch Break</td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td>13:15-14:10</td>");

            for (int per = 0; per < 5; per++){
                if (!(week[per][4]==null)){
                    out.println("<td>" + week[per][4] + "</td>");
                }
                else{
                    out.println("<td>Free Period</td>");
                }
            }
            out.println("</tr>\n" +
                    "    <tr>\n" +
                    "        <td>14:15-15:10</td>");

            for (int per = 0; per < 5; per++){
                if (!(week[per][5]==null)){
                    out.println("<td>" + week[per][5] + "</td>");
                }
                else{
                    out.println("<td>Free Period</td>");
                }
            }

            out.println("</tr>\n" +
                    "    <tr>\n" +
                    "        <td>15:15-16:10</td>");

            for (int per = 0; per < 5; per++){
                if (!(week[per][6]==null)){
                    out.println("<td>" + week[per][6] + "</td>");
                }
                else{
                    out.println("<td>Free Period</td>");
                }
            }
            out.println("</tr>\n" +
                    "    </tbody>\n" +
                    "</table>");

            out.println("<html>");
        con.close();

        }catch (Exception exe){
            System.out.println(exe);
        }
    }

}
