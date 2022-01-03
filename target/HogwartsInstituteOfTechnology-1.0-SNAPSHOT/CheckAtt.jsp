<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="dumbledorearmy.hogwartsinstituteoftechnology.Provider" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="dumbledorearmy.hogwartsinstituteoftechnology.Dates" %>
<%@ page import="javax.security.auth.Subject" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: assassin-001
  Date: 2021/12/30
  Time: 2:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
String date = Dates.getDate();
String week = Dates.getWeek();
int totalClass = 0;

PrintWriter writer = response.getWriter();
response.setContentType("text/html");

Connection con = Provider.GetConn();
try {
    Statement stmt = con.createStatement();
    Cookie[] cookies = request.getCookies();
    String em = "";
    for (Cookie c : cookies) {
        if (c.getName().equals("email")) {
            em = c.getValue();
        }
    }

    System.out.println(em);


    String[] classes = {}; //记录下老师的所有课

    String query;
    query = "select id, Class from teacher where email='" + em + "'";

    ResultSet rs = stmt.executeQuery(query);
    int teaid = 0;
    while (rs.next()) {
        classes = rs.getString("Class").split(",");
        teaid = rs.getInt("id");
        System.out.println("teacherid" + teaid);
    }

    query = "select * from checkcheck";
    rs = stmt.executeQuery(query);
    boolean flag = true;
    while (rs.next()) {
        String dd = rs.getString("date");
        System.out.println(dd);
        System.out.println(date);

        int teId = rs.getInt("teacher_id");

        System.out.println(teId);
        System.out.println(teaid);
        if (dd.equals(date) && teId == teaid) {
            RequestDispatcher rd = request.getRequestDispatcher("Teacher.jsp");
            System.out.println("Print something!!");
            flag = false;
            rd.include(request, response);
            writer.println("<script>\n" +
                    "alert(\"You have already checked attendance today!\")" +
                    "</script>");
        }
    }
    if (flag) {
        writer.println("<html><head>\n" +
                "    <title>Check Attendance</title>\n" +
                "\n" +
                "    <link rel=\"stylesheet\" href=\"css/mainStyle.css\">\n" +
                "    <link rel=\"icon\" href=\"ResourceFolder/Icon.png\">\n" +
                "</head><body background=\"https://ww2.kqed.org/app/uploads/sites/23/2015/05/Beard-Algorithm-1440x811.jpg\" style=\"background-size: cover\"><center>\n");
        request.getRequestDispatcher("module/headerLoggedIn.jsp").include(request, response);
        request.getRequestDispatcher("module/CheckLog.jsp").include(request, response);
        writer.println("<div id=\"containerBox\">");
        writer.println("<div class=\"centerBox\" style=\"width: 60%; !important;\">");
        writer.println("<h2 style=\"padding-bottom: 20px; margin-bottom: 20px; border-bottom: 1px solid darkgrey\"><b>Check Attendance</b></h2>");
        writer.println("<h4>Today is " + date + ", " + week + "</h4><br>");
        System.out.println(classes.length);
        System.out.println(classes[0]);

        for (String x: classes){
            System.out.println(x);
        }

        ArrayList<String> subjects = new ArrayList<>();
        ArrayList<String> times = new ArrayList<>();

        for (int i = 0; i < classes.length; i++) {
            String claa = classes[i];

            System.out.println(claa.length());
            System.out.println(claa);
            if (classes[i].length() >= 2) {
                query = "select classinfo.time from classinfo where subject='" + claa + "'";
                //如果对上weekday是今天的，那么把这节课加到subjects，time里


                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String[] timx = rs.getString("time").split("<br>");
                    for (String t : timx) {
                        String[] whole = t.split(" ");
                        String weekdayx = whole[0];
                        String classtime = whole[1];

                        if (weekdayx.equals(week)) {
                            System.out.println("equal" + claa);
                            subjects.add(claa);
                            times.add(classtime);
                            totalClass++;
                            System.out.println("after add" + totalClass);
                        }
                    }
                }
            }
        }


        if (totalClass == 0) {
            writer.println("<p>You have 0 classes today. Come back tomorrow.</p>");
        } else {
            System.out.println("should print " + subjects.size());

            writer.println("<p>You have " + subjects.size() +" class(es) today. Please Check in.</p>");

            writer.println("<form method=\"post\" action=CheckAtt>");
            for (int time = 0; time < subjects.size(); time++) { //对这个老师今天的每节课都搞过一次了，每个课就是一个table
                String subj = subjects.get(time);
                String classt = times.get(time);

                //做成list还是table好呢？

                writer.println("<table class=\"table table-striped\" style=\"margin-top: 20px; text-align: center; !important;\"><thead><tr class=\"table-dark\"><th colspan=\"3\" class=\"text-center\">Check in for " + subj + " at " + classt + "</th></tr>");
                writer.println("<tr><th class=\"text-center\" style=\"width: 50%;\">Name</th><th class=\"text-center\" style=\"width: 50px\">Tick if student is present</th></tr></thead>");

                writer.println("<tbody>");
                query = "select student.Firstname, student.Lastname, student.id from student " +
                        "join " + subj + " on " + subj + "." + "student_id = student.id";

                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    String fn = rs.getString("Firstname");
                    String ln = rs.getString("Lastname");
                    int stuid = rs.getInt("id");
                    String full = fn + " " + ln;
                    writer.println("<tr><td>" + full + "</td>");
                    writer.println("<td><input type=\"checkbox\" name=" + classt + "_" + subj + "_" +
                            stuid + " value=\"1\"></td></tr>");
                }
                writer.println("</tbody></table>");
            }
            writer.println("<a href=\"TeaBack\" style=\"width: 80px;\"><button class=\"btn btn-primary\" style=\"width: 80px; margin-top: 20px;\" type=\"button\">Back</button></a>\n");
            writer.println("<input class=\"btn btn-primary\" type=\"submit\" value=\"Submit\" style=\"width: 80px; margin-top: 20px;\">\n");
        }
        writer.println("</div>");
        writer.println("</div>");
        request.getRequestDispatcher("module/footer.jsp").include(request, response);
        writer.println("</center></body></html>");
    }
    
}catch (Exception exe){
    System.out.println(exe);
}finally {
    try{
        con.close();
    }catch (Exception e){}
}
%>