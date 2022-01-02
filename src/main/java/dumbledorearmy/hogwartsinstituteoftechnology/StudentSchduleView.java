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

@WebServlet(name = "StudentSchduleView", value = "/StudentSchduleView")
public class StudentSchduleView extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String Email="";
        if (session.getAttribute("email") != null) {
            Email = session.getAttribute("email").toString();
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
            ArrayList<String> sub = new ArrayList<String>();
            sub.add("Chinese");
            sub.add("Math");
            sub.add("Computer");

            ArrayList<String> N=sub;

            int id = 0;
            Statement stmt = con.createStatement();
            ResultSet rs3= stmt.executeQuery("select student.id from student where email='"+Email+"'");
            while (rs3.next()){
                id=rs3.getInt("id");
            }

            for (int i = 0; i < sub.size(); i++) {
                ResultSet rs = stmt.executeQuery("select * from "+N.get(i)+" where id="+id);
                if(!rs.next()){
                    N.remove(N.get(i));
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

            out.println("<a href=\"TeaBack\" style=\"width: 80px;\"><button class=\"btn btn-primary\" style=\"width: 80px;\" type=\"button\">Back</button></a>\n");
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
