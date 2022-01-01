<%@ page import="java.io.PrintWriter" %>
<%@ page import="dumbledorearmy.hogwartsinstituteoftechnology.Provider" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.*" %>
<%@ page import="com.mysql.cj.protocol.Resultset" %><%--
  Created by IntelliJ IDEA.
  User: assassin-001
  Date: 2021/12/30
  Time: 7:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Connection con = Provider.GetConn();
    try {
        PrintWriter writer = response.getWriter();

        Statement stmt = con.createStatement();
        Statement stmt2 = con.createStatement();
        Statement stmt3 = con.createStatement();

        //先拿到我教的所有课

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
        while (rs.next()){
            classes = rs.getString("Class").split(",");
            teaid = rs.getInt("id");
            System.out.println("teacherid" + teaid);
        }

        response.setContentType("text/html");
        writer.println("<html><head>\n" +
                "    <title>Check Attendance</title>\n" +
                "\n" +
                "    <link rel=\"stylesheet\" href=\"css/mainStyle.css\">\n" +
                "    <link rel=\"icon\" href=\"ResourceFolder/Icon.png\">\n" +
                "</head><body background=\"https://ww2.kqed.org/app/uploads/sites/23/2015/05/Beard-Algorithm-1440x811.jpg\" style=\"background-size: cover\"><center>\n");
        request.getRequestDispatcher("module/headerLoggedIn.jsp").include(request, response);
        request.getRequestDispatcher("module/CheckLog.jsp").include(request, response);
        writer.println("<div id=\"containerBox\">");
        writer.println("<div class=\"centerBox\" style=\"width: 80%; !important;\">");
        writer.println("<form action=Grading method=\"post\">");

        //System.out.println(classes.length);

        for (String clasx: classes) {
            if (clasx.length() >= 2) {
                writer.println("<table class=\"table table-striped\" style=\"margin-top: 20px; text-align: center; !important;\">");
                ArrayList<String> assignments = new ArrayList<>();
                ArrayList<Integer> stuids = new ArrayList<>();
                writer.println("<h2 style=\"padding-bottom: 20px; margin-bottom: 20px; border-bottom: 1px solid darkgrey\"><b>Assign grade for " + clasx + "</b></h2>");
                //选中这个课所有的quiz

                //Retrieving the data
                rs = stmt.executeQuery("select * from " + clasx);
                ResultSetMetaData rsMetaData = rs.getMetaData();
                int count = rsMetaData.getColumnCount();
                for (int i = 1; i <= count; i++) {
                    if (!(rsMetaData.getColumnName(i).equals("student_id") ||
                            rsMetaData.getColumnName(i).equals("attendance"))) {
                        assignments.add(rsMetaData.getColumnName(i));
                    }
                    //有了assignment
                }

                //获取当前成绩打出来


                writer.println("<thead><tr class=\"table-dark\"><th>Assignments/Student names</th>");

                rs = stmt2.executeQuery("select * from " + clasx);
                ResultSet rs1;
                while (rs.next()) { //打出标题所有学生的名字
                    int tmpid = rs.getInt("student_id");
                    stuids.add(tmpid);

                    String query2 = "select Firstname, Lastname from student where id=" + tmpid;

                    rs1 = stmt3.executeQuery(query2);

                    while (rs1.next()) {
                        //System.out.println("Checkpoint1");
                        String fn = rs1.getString("Firstname");
                        String ln = rs1.getString("Lastname");

                        String full = fn + " " + ln;
                        writer.println("<th>" + full + "</th>");
                    }
                }
                writer.println("</tr></thead>");
                String query3;
                writer.println("<tbody>");
                for (int as = 0; as < assignments.size(); as++) {
                    writer.println("<tr><th>" + assignments.get(as) + "</th>");
                    for (int s = 0; s < stuids.size(); s++) {
<<<<<<< HEAD
                        query3 = "select `" + assignments.get(as) + "` from " + clasx + " where student_id=" + stuids.get(s);                        ResultSet rs3 = stmt3.executeQuery(query3);
=======
                        query3 = "select `" + assignments.get(as) + "` from " + clasx + " where student_id=" + stuids.get(s);
                        ResultSet rs3 = stmt3.executeQuery(query3);
>>>>>>> 2d2e1259ef03c585491039e249eb0d5cb3e17993
                        //System.out.println("Checkpoint2");
                        String Grade = "";
                        while (rs3.next()) {
                            Grade = rs3.getString(assignments.get(as));
                            //System.out.println(Grade);
                        }
                        writer.println("<td><input type=\"text\" name=\"" +
                                clasx + assignments.get(as) + stuids.get(s) + "\"" +
                                "value=\"" + Grade + "\"></td>");
                    }
                    writer.println("</tr>");
                }
                writer.println("</tbody></table>");
            }
        }
        writer.println("<a href=\"TeaBack\" style=\"width: 80px;\"><button class=\"btn btn-primary\" style=\"width: 80px; margin-top: 20px;\" type=\"button\">Back</button></a>\n");
        writer.println("<input type=\"submit\" class=\"btn btn-primary\" value=\"Submit\" style=\"width: 80px; margin-top: 20px;\">\n");
        writer.println("</form>");
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
