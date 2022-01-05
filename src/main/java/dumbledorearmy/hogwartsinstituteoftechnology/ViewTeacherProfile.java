package dumbledorearmy.hogwartsinstituteoftechnology;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

@WebServlet(name = "ViewTeacherProfile", value = "/ViewTeacherProfile")
public class ViewTeacherProfile extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String Sid= request.getParameter("id");
    int id=Integer.parseInt(Sid);
    response.setContentType("text/html");
    PrintWriter out = response.getWriter();
    Connection con = Provider.GetConn();
    StringBuffer buf=new StringBuffer();
    try{
        Statement stmt1=con.createStatement();

        ResultSet rs=stmt1.executeQuery("select * from teacher where id="+id);
        while(rs.next()){
            String fname=rs.getString("Firstname");
            String lname=rs.getString("Lastname");
            String email=rs.getString("email");
            String birth=rs.getString("birth");
            String gender=rs.getString("gender");
            String course=rs.getString("Class");
            String degree=rs.getString("degree");
            String nation=rs.getString("nation");
            String des=rs.getString("des");
            String phone=rs.getString("phone");
            buf.append(course);
            out.println("<html>");
            out.println("<head>");
            out.println("<title>My Profile</title>");
            out.println("<link rel=\"stylesheet\" href=\"css/mainStyle.css\">");
            out.println("<link rel=\"stylesheet\" href=\"css/profile.css\">");
            out.println("<link rel=\"stylesheet\" href=\"css/schedule.css\">");
            out.println("<link rel=\"icon\" href=\"ResourceFolder/Icon.png\">");
            out.println("</head>");
            out.println("<body background=\"https://www.gozetim.com/images/kirtasiye-testleri.jpg\" style=\"background-size: cover\"><center>");
            request.getRequestDispatcher("module/headerLoggedIn.jsp").include(request, response);
            out.println("<div id=\"containerBox\">");
            out.println("<div class=\"centerBox\" style=\"width: 60%; !important;\">");
            request.getRequestDispatcher("module/CheckLog.jsp").include(request, response);
            out.println("<div id=\"mainInfo\">");
            out.println("<div>");
            out.println("<h5 style=\"color: #dddddd; margin-bottom: -10px\"><i>Teacher</i></h5>");
            out.println("<h2><b>"+fname+" "+lname+"</h2><a href=\"EditMyProfileT2?id="+id+"\"></b></h2></a>");
            out.println("</div>");
            out.println("<svg width=\"40\" height=\"40\"><circle cx=\"20\" cy=\"20\" r=\"16\" fill=\"888888\"></svg>");
            out.println("</div>");
            out.println("<div style=\"display: flex; justify-content: space-between; flex-direction: row\">");
            out.println("<div class=\"leftBox\">");
            out.println("<div><label>Email:</label> "+email+"</div>");
            out.println("<div><label>Phone Number:</label> "+phone+"</div>");
            out.println("<div><label>Gender:</label>"+gender+"</div>");
            out.println("</div>");
            out.println("<div class=\"rightBox\">");
            out.println("<div><label>Nationality:</label> "+nation+"</div>");
            out.println("<div><label>Courses:</label> "+buf.substring(0,buf.length()-1)+"</div>");
            out.println("<div><label>Degree:</label> "+degree+"</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div id=\"bottomBox\"><h5><b>Detail:</b></h5><p>"+des+"</p></div>");

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

            id = 0;
            Statement stmt = con.createStatement();
            String query = "select * from teacher where email='" + email + "';";

            rs = stmt.executeQuery(query);
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
            out.println("<h4 style=\"padding-bottom: 20px; margin: 20px 0px` ; border-bottom: 1px solid darkgrey\"><b>View Teacher's Schedule</b></h4>");
            out.println("<table id=\"scheduleTable\"><thead>" +
                    "    <tr class=\"scheduleHeader\" style=\"height: 40px; !important; background: #404040; color: white;\">\n" +
                    "        <th class=\"text-center\" style=\"width: 20%;\">Time/Weekday</th>\n" +
                    "        <th class=\"text-center\" style=\"width: 16%;\">Monday</th>\n" +
                    "        <th class=\"text-center\" style=\"width: 16%;\">Tuesday</th>\n" +
                    "        <th class=\"text-center\" style=\"width: 16%;\">Wednesday</th>\n" +
                    "        <th class=\"text-center\" style=\"width: 16%;\">Thursday</th>\n" +
                    "        <th class=\"text-center\" style=\"width: 16%;\">Friday</th>\n" +
                    "    </tr>\n" +
                    "    </thead>\n" +
                    "    <tbody id=\"scheduleBody\">\n" +
                    "    <tr>\n" +
                    "        <td class=\"timePeriod\">8:30-9:25</td>");

            for (int per = 0; per < 5; per++){
                if (!(week[per][0]==null)){
                    out.println("<td style=\"" + ScheduleManager.GetStyle(week[per][0]) + "\">" + week[per][0] + "</td>");
                }
                else{
                    out.println("<td class=\"freePeriod\">Free Period</td>");
                }
            }

            out.print("</tr>\n" +
                    "    <tr>\n" +
                    "        <td class=\"timePeriod\">9:30-10:25</td>");

            for (int per = 0; per < 5; per++){
                if (!(week[per][1] == null)){
                    out.println("<td style=\"" + ScheduleManager.GetStyle(week[per][1]) + "\">" + week[per][1] + "</td>");
                }
                else{
                    out.println("<td class=\"freePeriod\">Free Period</td>");
                }
            }

            out.println("</tr>\n" +
                    "    <tr>\n" +
                    "        <td class=\"timePeriod\">10:30-11:25</td>");

            for (int per = 0; per < 5; per++){
                if (!(week[per][2]==null)){
                    out.println("<td style=\"" + ScheduleManager.GetStyle(week[per][2]) + "\">" + week[per][2] + "</td>");
                }
                else{
                    out.println("<td class=\"freePeriod\">Free Period</td>");
                }
            }
            out.println("</tr>\n" +
                    "    <tr>\n" +
                    "        <td class=\"timePeriod\">11:30-12:25</td>");

            for (int per = 0; per < 5; per++){
                if (!(week[per][3]==null)){
                    out.println("<td style=\"" + ScheduleManager.GetStyle(week[per][3]) + "\">" + week[per][3] + "</td>");
                }
                else{
                    out.println("<td class=\"freePeriod\">Free Period</td>");
                }
            }

            out.println("</tr>\n" +
                    "    <tr>\n" +
                    "       <td class=\"timePeriod\">12:30-13:15</td>" +
                    "       <td id = \"spec\" colspan=\"5\" style=\"background: #cccccc\"><b>Lunch Break</b></td>\n" +
                    "    </tr>\n" +
                    "    <tr>\n" +
                    "        <td class=\"timePeriod\">13:15-14:10</td>");

            for (int per = 0; per < 5; per++){
                if (!(week[per][4]==null)){
                    out.println("<td style=\"" + ScheduleManager.GetStyle(week[per][4]) + "\">" + week[per][4] + "</td>");
                }
                else{
                    out.println("<td class=\"freePeriod\">Free Period</td>");
                }
            }
            out.println("</tr>\n" +
                    "    <tr>\n" +
                    "        <td class=\"timePeriod\">14:15-15:10</td>");

            for (int per = 0; per < 5; per++){
                if (!(week[per][5]==null)){
                    out.println("<td style=\"" + ScheduleManager.GetStyle(week[per][5]) + "\">" + week[per][5] + "</td>");
                }
                else{
                    out.println("<td class=\"freePeriod\">Free Period</td>");
                }
            }

            out.println("</tr>\n" +
                    "    <tr>\n" +
                    "        <td class=\"timePeriod\">15:15-16:10</td>");

            for (int per = 0; per < 5; per++){
                if (!(week[per][6]==null)){
                    out.println("<td style=\"" + ScheduleManager.GetStyle(week[per][6]) + "\">" + week[per][6] + "</td>");
                }
                else{
                    out.println("<td class=\"freePeriod\">Free Period</td>");
                }
            }
            out.println("</tr>\n" +
                    "    </tbody>\n" +
                    "</table>");

            out.println("<button class=\"btn btn-primary\" style=\"width: 80px; margin-top: 20px;\" type=\"button\" onclick=\"history.back()\">Back</button>");
            out.println("</div>");
            out.println("</div>");
            request.getRequestDispatcher("module/footer.jsp").include(request, response);
            out.println("</center></body>");
            out.println("</html>");        }


    } catch (Exception exe) {
        System.out.println("Exception caught" + exe);
    }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
