<%@ page import="java.io.PrintWriter" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Connection" %>
<%@ page import="dumbledorearmy.hogwartsinstituteoftechnology.Provider" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="com.mysql.cj.protocol.Resultset" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.ResultSetMetaData" %><%--
  Created by IntelliJ IDEA.
  User: assassin-001
  Date: 2022/1/6
  Time: 1:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    try{



        Connection con = Provider.GetConn();
        Statement stmt = con.createStatement();
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");

        if (!(request.getParameter("msg")==null)) {
            writer.println("<script>\n" +
                    "alert(\"Check your Math!!\")" +
                    "</script>");
        }


        String claa = request.getParameter("clasx");

        ArrayList<String> assignments = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("select * from " + claa);
        ResultSetMetaData rsMetaData = rs.getMetaData();
        int count = rsMetaData.getColumnCount();
        for (int i = 1; i <= count; i++) {
          if (!(rsMetaData.getColumnName(i).equals("student_id") ||
                  rsMetaData.getColumnName(i).equals("attendance") ||
                  rsMetaData.getColumnName(i).equals("GPA"))) {
            assignments.add(rsMetaData.getColumnName(i));
          }
          //有了assignment
        }

        //获取当前成绩打出来

        writer.println("<h3>Now add the percentage for each assignment. Make sure they add up to 100.");
        writer.println("<form action=CalFinal method=\"post\"><table>");
        writer.println("<tr>");
        for (String cc: assignments){
            if (cc.length() >= 2){
                writer.println("<th>" + cc + "</th>");
            }
        }

        writer.println("</tr>");
        writer.println("<tr>");

        String param = "";

        for (String cc: assignments){
            if (cc.length() >= 2){
                param += cc;
                param += ",";
                writer.println("<th><input type=\"text\" name=\"" + claa + "_" + cc + "\" value=\"0\">");
            }
        }
        writer.println("</tr>");
        System.out.println(param);


        writer.println("<input type=\"hidden\" name=\"clasx\" value=\"" + claa + "~" + param + "\">");



        writer.println("<input type=\"submit\" value=\"Confirm!\">");


    }catch (Exception exe){
      System.out.println(exe);
    }







%>
</html>
