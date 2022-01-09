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
                  rsMetaData.getColumnName(i).equals("SemesterScore"))) {
            assignments.add(rsMetaData.getColumnName(i));
          }
          //有了assignment
        }

        //获取当前成绩打出来
        writer.println("<html><head>\n" +
                "    <title>Update Semester Score</title>\n" +
                "\n" +
                "    <link rel=\"stylesheet\" href=\"css/mainStyle.css\">\n" +
                "    <link rel=\"icon\" href=\"ResourceFolder/Icon.png\">\n" +
                "</head><body background=\"https://ww2.kqed.org/app/uploads/sites/23/2015/05/Beard-Algorithm-1440x811.jpg\" style=\"background-size: cover\"><center>\n");
        request.getRequestDispatcher("module/headerLoggedIn.jsp").include(request, response);
        request.getRequestDispatcher("module/CheckLog.jsp").include(request, response);
        writer.println("<div class=\"centerBox\" style=\"width: 80%; !important;\">");
        writer.println("<h2 style=\"padding-bottom: 20px; margin-bottom: 20px; border-bottom: 1px solid darkgrey\">Update Semester Score</h2>");
        writer.println("<p>Please add the percentage for each assignment. Make sure they add up to 100.</p>");
        writer.println("<form action=CalFinal method=\"post\"><table class=\"table table-striped text-center\" style=\"width: 100%\"><thead>");
        writer.println("<tr class=\"table-dark\">");
        for (String cc: assignments){
            if (cc.length() >= 2 && !cc.equals("SemesterScore")){
                writer.println("<th class=\"text-center\" style=\"" + 1f/cc.length() + "\">" + cc + "</th>");
            }
        }

        writer.println("</tr></thead>");
        writer.println("<tbody><tr>");

        String param = "";

        for (String cc: assignments){
            if (cc.length() >= 2 && !cc.equals("SemesterScore")){
                param += cc;
                param += ",";
                writer.println("<td><input type=\"text\" name=\"" + claa + "_" + cc + "\" value=\"0\" style=\"width: 100%\"></td>");
            }
        }
        writer.println("</tr></tbody></table>");
        System.out.println(param);


        writer.println("<input type=\"hidden\" name=\"clasx\" value=\"" + claa + "\">");
        writer.println("<input type=\"hidden\" name=\"tests\" value=\"" + param + "\">");


        writer.println("<input type=\"submit\" class=\"btn btn-primary\" style=\"margin-top: 20px; width: 120px\" value=\"Confirm\">");
        writer.println("</form>");
        writer.println("<button class=\"btn btn-primary\" style=\"width: 120px\" onclick=\"history.back()\">Back</button></div>");
        request.getRequestDispatcher("module/footer.jsp").include(request, response);
        writer.println("</center></body></html>");


    }catch (Exception exe){
      System.out.println(exe);
    }







%>
</html>
