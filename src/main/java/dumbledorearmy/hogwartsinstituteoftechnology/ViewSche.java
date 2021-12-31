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

        Connection con = Provider.GetConn();
        try{
            PrintWriter out = response.getWriter();
            response.setContentType("text/html");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>View Schedule</title>");
            out.println("<link rel=\"stylesheet\" href=\"css/mainStyle.css\">");
            out.println("<link rel=\"icon\" href=\"ResourceFolder/Icon.png\">");
            out.println("</head>");
            out.println("<body background=\"https://www.gozetim.com/images/kirtasiye-testleri.jpg\" style=\"background-size: cover\"><center>");
            request.getRequestDispatcher("module/headerLoggedIn.jsp").include(request, response);
            out.println("<div id=\"containerBox\">");
            out.println("<div class=\"centerBox\" style=\"width: 60%; !important;\">");
            request.getRequestDispatcher("module/CheckLog.jsp").include(request, response);
            out.println("<h2 style=\"padding-bottom: 20px; margin-bottom: 20px; border-bottom: 1px solid darkgrey\"><b>View Schedule</b></h2>");

            Cookie[] cookies = request.getCookies();
            String em = "";
            for (Cookie c: cookies){
                if (c.getName().equals("email")){
                    em = c.getValue();
                }
            }
            int id = 0;
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
            out.println("<table id=\"scheduleTable\" class=\"table table-striped\" style=\"margin-top: 20px; text-align: center; !important;\">\n" +
                    "    <tr class=\"table-dark\">\n" +
                    "        <th class=\"text-center\">Time/Weekday</th>\n" +
                    "        <th class=\"text-center\">Monday</th>\n" +
                    "        <th class=\"text-center\">Tuesday</th>\n" +
                    "        <th class=\"text-center\">Wednesday</th>\n" +
                    "        <th class=\"text-center\">Thursday</th>\n" +
                    "        <th class=\"text-center\">Friday</th>\n" +
                    "    </tr>\n" +
                    "    </thead>\n" +
                    "    <tbody style=\"font-size: 13px\">\n" +
                    "    <tr>\n" +
                    "        <td style=\"background: grey; !important; color: white; !important;\">8:30-9:25</td>");

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
                    "        <td style=\"background: grey; !important; color: white; !important;\">9:30-10:25</td>");

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
                    "        <td style=\"background: grey; !important; color: white; !important;\">10:30-11:25</td>");

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
                    "        <td style=\"background: grey; !important; color: white; !important;\">11:30-12:25</td>");

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
                    "       <td style=\"background: grey; !important; color: white; !important;\">12:30-13:15</td>" +
                    "       <td id = \"spec\" colspan=\"5\">Lunch Break</td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td style=\"background: grey; !important; color: white; !important;\">13:15-14:10</td>");

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
                    "        <td style=\"background: grey; !important; color: white; !important;\">14:15-15:10</td>");

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
                    "        <td style=\"background: grey; !important; color: white; !important;\">15:15-16:10</td>");

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

            out.println("<input class=\"btn btn-primary\" type=\"button\" value=\"Back\" onclick=\"history.back();\" style=\"width: 80px; margin-top: 20px;\">\n");
            out.println("</div>");
            out.println("</div>");
            request.getRequestDispatcher("module/footer.jsp").include(request, response);
            out.println("</center></body>");
            out.println("</html>");
        }catch (Exception exe){
            System.out.println(exe);
        }finally {
            try{
                con.close();
            }catch (Exception e){}
        }
    }

}
