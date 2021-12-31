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
<html>
<head>
    <title>Check Attendance</title>
    <%
        String date = Dates.getDate();
        String week = Dates.getWeek();
        int totalClass = 0;

        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");


        try {
            Connection con = Provider.GetConn();
            Cookie[] cookies = request.getCookies();
            Statement stmt = con.createStatement();
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

                //System.out.println(classes);

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
            if (flag){
                writer.println("<h3>Today is " + date + ", " + week + "</h3>");
                System.out.println(classes[0]);

                ArrayList<String> subjects = new ArrayList<>();
                ArrayList<String> times = new ArrayList<>();

                for (String clasx : classes) {
//                    System.out.println(clasx);
//                    System.out.println(clasx.length());

                    query = "select time from classinfo where subject=\"" + clasx + "\"";
                    //如果对上weekday是今天的，那么把这节课加到subjects，time里

                    rs = stmt.executeQuery(query);
                    while (rs.next()) {
                        System.out.println("enter!!");
                        String[] timx = rs.getString("time").split("<br>");
                        for (String i: timx) System.out.println(i);
                        for (String t : timx) {
                            String[] whole = t.split(" ");
                            String weekdayx = whole[0];
                            String classtime = whole[1];
                            System.out.println(weekdayx);
                            //System.out.println(classtime);

                            if (weekdayx.equals(week)) {
                                subjects.add(clasx);
                                times.add(classtime);
                                totalClass++;
                            }
                        }
                    }
                }
                if (totalClass == 0) {
                    writer.println("<h3>You have 0 classes today. Come back tomorrow.");
                } else {
                    writer.println("<h3>You have " + totalClass + " classes today. Please Check in.");

                    writer.println("<form method=\"post\" action=CheckAtt>");
                    for (int time = 0; time < totalClass; time++) { //对这个老师今天的每节课都搞过一次了，每个课就是一个table
                        String subj = subjects.get(time);
                        String classt = times.get(time);

                        //做成list还是table好呢？

                        writer.println("<table><tr><th colspan=\"3\">Check in for " + subj + " at " + classt + "</th></tr>");
                        writer.println("<tr><th>Name</th><th>Tick if he attended</th></tr>");

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
                        writer.println("</table>");
                    }
                    writer.println("<input type=\"submit\">");

                }

                con.close();
            }

        }catch (Exception exe){
            System.out.println(exe);
        }


    %>
</head>
<body>

</body>
</html>
