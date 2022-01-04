<%@ page import="dumbledorearmy.hogwartsinstituteoftechnology.Dates" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="dumbledorearmy.hogwartsinstituteoftechnology.Provider" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: assassin-001
  Date: 2022/1/4
  Time: 11:20 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Attendance</title>
</head>
<body>
<h3>Choose the class you want to View/Check Attendance</h3>
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

        writer.println("<table><tr><th>Subjects</th></tr>");
        for (int i = 0; i < classes.length; i++) {
            if (classes[i].length() >= 2){
                writer.println("<tr><td>");

                String claa = classes[i];
                writer.println("<form method=\"get\" action=ViewAtt>");
                writer.println("<input type=\"submit\" name=\"clasx\" value=\"" + claa + "\"></td></tr></form>");
            }
        }
        writer.println("</table>");


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
