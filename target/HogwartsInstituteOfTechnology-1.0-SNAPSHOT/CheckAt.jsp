<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="dumbledorearmy.hogwartsinstituteoftechnology.Provider" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="dumbledorearmy.hogwartsinstituteoftechnology.Dates" %>
<%@ page import="javax.security.auth.Subject" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.mysql.cj.protocol.Resultset" %><%--
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


    PrintWriter writer = response.getWriter();
    response.setContentType("text/html");


    try {
        Connection con = Provider.GetConn();
        Statement stmt = con.createStatement();

        String classNow = request.getParameter("clasx");

        String query = "select * from checkcheck";
        ResultSet rs = stmt.executeQuery(query);
        boolean flag = true;
        while (rs.next()) {
            String dd = rs.getString("date");
            String sub = rs.getString("subject");
            System.out.println(dd);
            System.out.println(date);
            if (dd.equals(date) && sub.equals(classNow)) {
                RequestDispatcher rd = request.getRequestDispatcher("Attendance.jsp");
                System.out.println("Print something!!");
                flag = false;
                rd.include(request, response);
                writer.println("<script>\n" +
                        "alert(\"You have already checked attendance for this class today!\")" +
                        "</script>");

            }
        }
        boolean flag2 = false;
        if (flag) {
            String claa = classNow;



            System.out.println(claa.length());
            System.out.println(claa);

            query = "select classinfo.time from classinfo where subject='" + claa + "'";
            //如果对上weekday是今天的，那么把这节课加到subjects，time里


            rs = stmt.executeQuery(query);
            while (rs.next()) {
                String[] timx = rs.getString("time").split("<br>");
                for (String t : timx) {
                    String[] whole = t.split(" ");
                    String weekdayx = whole[0];
                    String classt = whole[1];


                    if (weekdayx.equals(week)) {
                        flag2 = true;

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
                        writer.println("<h2 style=\"padding-bottom: 20px; margin-bottom: 20px; border-bottom: 1px solid darkgrey\"><b>Check Attendance For " + classNow + "</b></h2>");
                        writer.println("<h4>Today is " + date + ", " + week + "</h4><br>");

                        writer.println("<form method=\"post\" action=CheckAt>");
                        writer.println("<input type=\"hidden\" value=\"" + claa + "\" name=\"clasx\">");

                        writer.println("<table class=\"table table-striped text-center\"><thead><tr class=\"table-dark\"><th class=\"text-center\" width=\"50%\">Name</th><th class=\"text-center\" width=\"50%\">Tick if student presents</th></tr></thead>");

                        writer.println("<tbody>");
                        query = "select student.Firstname, student.Lastname, student.id from student " +
                                "join " + claa + " on " + claa + "." + "student_id = student.id";

                        rs = stmt.executeQuery(query);

                        while (rs.next()) {
                            String fn = rs.getString("Firstname");
                            String ln = rs.getString("Lastname");
                            int stuid = rs.getInt("id");
                            String full = fn + " " + ln;
                            writer.println("<tr><td>" + full + "</td>");
                            writer.println("<td><input type=\"checkbox\" name=\"" + classt + "_" + claa + "_" +
                                    stuid + "\" value=\"1\" style=\"transform: scale(2)\"></td></tr>");
                        }
                        writer.println("</tbody></table>");

                        writer.println("<a href=\"TeaBack\" style=\"width: 80px;\"><button class=\"btn btn-primary\" style=\"width: 80px; margin-top: 20px;\" type=\"button\">Back</button></a>");
                        writer.println("<input class=\"btn btn-primary\" type=\"submit\" value=\"Submit\" style=\"width: 80px; margin-top: 20px;\"></form>");
                        writer.println("</div>");
                        writer.println("</div>");
                        request.getRequestDispatcher("module/footer.jsp").include(request, response);
                        writer.println("</center></body></html>");
                        break;
                    }

                }
            }
            if (!flag2){
                RequestDispatcher rd2 = request.getRequestDispatcher("Attendance.jsp");
                rd2.include(request, response);
                writer.println("<script>\n" +
                        "alert(\"You do not have this class today!\")" +
                        "</script>");

            }

        }

    }catch (Exception exe){
        System.out.println(exe);
    }
%>