<%@ page import="dumbledorearmy.hogwartsinstituteoftechnology.Dates" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="dumbledorearmy.hogwartsinstituteoftechnology.Provider" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="dumbledorearmy.hogwartsinstituteoftechnology.ScheduleManager" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--
  Created by IntelliJ IDEA.
  User: assassin-001
  Date: 2022/1/4
  Time: 11:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%
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

        writer.println("<html><head>\n" +
                "    <title>Attendance</title>\n" +
                "\n" +
                "    <link rel=\"stylesheet\" href=\"css/mainStyle.css\">\n" +
                "    <link rel=\"icon\" href=\"ResourceFolder/Icon.png\">\n" +
                "</head><body background=\"https://ww2.kqed.org/app/uploads/sites/23/2015/05/Beard-Algorithm-1440x811.jpg\" style=\"background-size: cover\"><center>\n");
                request.getRequestDispatcher("module/headerLoggedIn.jsp").include(request, response);
        request.getRequestDispatcher("module/CheckLog.jsp").include(request, response);
        writer.println("<div id=\"containerBox\">");
        writer.println("<div class=\"centerBox\" style=\"width: 60%; !important;\">");
        writer.println("<h2 style=\"padding-bottom: 20px; margin-bottom: 20px; border-bottom: 1px solid darkgrey\"><b>Choose the class you want to View/Check Attendance</b></h2>\n");
        writer.println("<h4><b>Subjects</b></h4>");
        writer.println("<div class=\"list-group\">");
        for (int i = 0; i < classes.length; i++) {
            if (classes[i].length() >= 2){
                String claa = classes[i];
                writer.println("<a href=\"ViewAtt?clasx=" + claa + "\" class=\"list-group-item list-group-item-action\" style=\"width: 40%; display: flex; justify-content: center; align-items: center; margin-left: auto; margin-right: auto\">");
                writer.println("<svg width=\"40\" height=\"40\"><circle cx=\"20\" cy=\"20\" r=\"16\" fill=\"" + ScheduleManager.GetColor(claa) + "\"></svg>");
                writer.println("<h4>" + claa + "</h4></input>");
                writer.println("</a>");
            }
        }
        writer.println("<a href=\"TeaBack\" style=\"width: 80px; margin: auto\"><button class=\"btn btn-primary\" style=\"width: 80px; margin-top: 20px;\" type=\"button\">Back</button></a>");
        writer.println("</div>");
        writer.println("</div>");
        request.getRequestDispatcher("module/footer.jsp").include(request, response);
        writer.println("</center></body></html>");
    }catch (Exception exe){
        System.out.println(exe);
    }finally {
        try{
            con.close();
        }catch (Exception e){}
    }
%>



</body>
</html>
