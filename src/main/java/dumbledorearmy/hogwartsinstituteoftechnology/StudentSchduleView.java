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
import java.util.HashMap;
import java.util.Objects;

@WebServlet(name = "StudentSchduleView", value = "/StudentSchduleView")
public class StudentSchduleView extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String Email2="";
        if (session.getAttribute("email") != null) {
            Email2 = session.getAttribute("email").toString();
        }
        String Email="";
        Cookie[] cookies = request.getCookies();
        for (Cookie c: cookies){
            if (c.getName().equals("email")){
                Email = c.getValue();
            }
        }
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
            out.println("<link rel=\"stylesheet\" href=\"css/schedule.css\">");
            out.println("<link rel=\"icon\" href=\"ResourceFolder/Icon.png\">");
            out.println("</head>");
            out.println("<body background=\"https://www.gozetim.com/images/kirtasiye-testleri.jpg\" style=\"background-size: cover\"><center>");
            request.getRequestDispatcher("module/headerLoggedIn.jsp").include(request, response);
            out.println("<div id=\"containerBox\">");
            out.println("<div class=\"centerBox\" style=\"width: 60%; !important;\">");
            request.getRequestDispatcher("module/CheckLog.jsp").include(request, response);
            out.println("<h2 style=\"padding-bottom: 20px; margin-bottom: 20px; border-bottom: 1px solid darkgrey\"><b>View Schedule</b></h2>");

            ArrayList<String> sub = new ArrayList<String>();
            sub.add("Chinese");
            sub.add("Math");
            sub.add("CS");
            sub.add("Physics");
            sub.add("Chemistry");
            sub.add("MacroEconomics");
            sub.add("MicroEconomics");
            sub.add("English");
            sub.add("Philosophy");

            int id = 0;
            Statement stmt = con.createStatement();
            ResultSet rs3= stmt.executeQuery("select student.id from student where email='"+Email+"'");
            while (rs3.next()){
                id=rs3.getInt("id");
            }

            ArrayList<String> N = new ArrayList<String>();
            N.add("Chinese");
            N.add("Math");
            N.add("CS");
            N.add("Physics");
            N.add("Chemistry");
            N.add("MacroEconomics");
            N.add("MicroEconomics");
            N.add("English");
            N.add("Philosophy");

            for (int i = 0; i < sub.size(); i++) {
                ResultSet rs = stmt.executeQuery("select * from "+sub.get(i)+" where student_id="+id);
                if(!rs.next()){
                    N.remove(sub.get(i));
                }
            }

            String[][] week = new String[5][7];//对应一整周，5天
            for (int j = 0; j < N.size(); j++) {

            ResultSet rs2 = stmt.executeQuery("select * from classinfo where subject='"+N.get(j)+"'");
            while(rs2.next()) { //默认我们后期会一个老师教很多课，但只有1
                String time = rs2.getString("time");
                String[] times = time.split("<br>");
                for (String aTime: times){
                    String[] whole = aTime.split(" ");

                    System.out.println(whole[0]);
                    System.out.println(whole[1]);

                    System.out.println(mapWeek.get(whole[0]));
                    System.out.println(mapClass.get(whole[1]));

                    int weekday = mapWeek.get(whole[0]);
                    int period = mapClass.get(whole[1]);
                    week[weekday][period] = rs2.getString("subject");
                }
            }
            }
            out.println("<table id=\"scheduleTable\">\n" +
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
            out.println("<h4 style=\"padding-top: 20px; margin: 20px 0px; border-top: 1px solid darkgrey\"><b>My Teachers</b></h4>");
            out.println("<div class=\"list-group\">");
            for (int i = 0; i < N.size(); i++) {
                ResultSet rp8= stmt.executeQuery("select classinfo.teacher_id, teacher.Firstname, teacher.Lastname, teacher.email from classinfo join teacher on classinfo.teacher_id = teacher.id where subject='"+N.get(i)+"'");
                while(rp8.next()){
                    String tid=rp8.getString("teacher_id");
                    String fname=rp8.getString("Firstname");
                    String lname= rp8.getString("Lastname");
                    String email= rp8.getString("email");
                    out.println("<a href=\"ViewTeacherProfile?id=" + tid + "\" class=\"list-group-item list-group-item-action d-flex gap-3 py-3\" aria-current=\"true\">");
                    out.println("<svg height=\"40\" width=\"40\"><circle cx=\"20\" cy=\"20\" r=\"8\"fill=\"" + ScheduleManager.GetColor(N.get(i)) + "\" /></svg>");
                    //                    out.println("<img src=\"https://github.com/twbs.png\" alt=\"twbs\" width=\"32\" height=\"32\" class=\"rounded-circle flex-shrink-0\">");
                    out.println("<div class=\"d-flex gap-2 w-100 justify-content-between\">\n");
                    out.println("<h4><b>" + N.get(i) + "</b></h4>");
                    out.println("<div>");
                    out.println("<h5 class=\"mb-0\">" + fname + " " + lname + "</h5>\n");
                    out.println("<p class=\"mb-0 opacity-75\"><u>" + email + "</u></p>");
                    out.println("</div>");
                    out.println("</div>");
                    out.println("</a>");
//                    out.println("<tr><td>"+N.get(i)+"</td><td><a href=\"ViewTeacherProfile?id="+tid+"\">"+fname+" "+lname+"</a></td><td>"+email+"</td></tr>");
                }
            }
            out.println("</div>");

//            out.println("<table><tr><th>Your Subject</th><th>Teacher</th><th>Email</th></tr><tbody>");
//            for (int i = 0; i < N.size(); i++) {
//                ResultSet rp8= stmt.executeQuery("select classinfo.teacher_id, teacher.Firstname, teacher.Lastname, teacher.email from classinfo join teacher on classinfo.teacher_id = teacher.id where subject='"+N.get(i)+"'");
//                while(rp8.next()){
//                    String tid=rp8.getString("teacher_id");
//                    String fname=rp8.getString("Firstname");
//                    String lname= rp8.getString("Lastname");
//                    String email= rp8.getString("email");
//
//                    out.println("<tr><td>"+N.get(i)+"</td><td><a href=\"ViewTeacherProfile?id="+tid+"\">"+fname+" "+lname+"</a></td><td>"+email+"</td></tr>");
//                }
//            }
//            out.println("</tbody></table>");

            out.println("<a href=\"StuBack\" style=\"width: 80px;\"><button class=\"btn btn-primary\" style=\"width: 80px; margin-top: 20px;\" type=\"button\">Back</button></a>\n");
            out.println("</div>");
            out.println("</div>");
            request.getRequestDispatcher("module/footer.jsp").include(request, response);
            out.println("</center></body>");
            out.println("</html>");
            N.clear();
        }catch (Exception exe){
            System.out.println(exe);
        }finally {
            try{
                con.close();
            }catch (Exception e){}
        }
    }
}
