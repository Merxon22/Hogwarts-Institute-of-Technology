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
<html>
<head>
    <title>Give Grades</title>
</head>
<body>
    <%
        try {
            PrintWriter writer = response.getWriter();
            Connection con = Provider.GetConn();

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
            writer.println("<form action=Grading method=\"post\">");

            for (String clasx: classes){
                writer.println("<table>");
                ArrayList<String> assignments = new ArrayList<>();
                ArrayList<Integer> stuids = new ArrayList<>();
                writer.println("<h3>Assign grade for "+clasx+"</h3>");
                //选中这个课所有的quiz

                //Retrieving the data
                rs = stmt.executeQuery("select * from " + clasx);
                ResultSetMetaData rsMetaData = rs.getMetaData();
                int count = rsMetaData.getColumnCount();
                for(int i = 1; i<=count; i++) {
                    if (!(rsMetaData.getColumnName(i).equals("student_id") ||
                            rsMetaData.getColumnName(i).equals("attendance"))){
                        assignments.add(rsMetaData.getColumnName(i));
                    }
                     //有了assignment
                }

                //获取当前成绩打出来


                writer.println("<tr><th>Assignments/Student names</th>");

                rs = stmt2.executeQuery("select * from " + clasx);
                ResultSet rs1;
                while(rs.next()){ //打出标题所有学生的名字
                    int tmpid = rs.getInt("student_id");
                    stuids.add(tmpid);

                    String query2 = "select Firstname, Lastname from student where id=" + tmpid;

                    rs1 = stmt3.executeQuery(query2);

                    while (rs1.next()){
                        System.out.println("Checkpoint1");
                        String fn = rs1.getString("Firstname");
                        String ln = rs1.getString("Lastname");

                        String full = fn + " " + ln;
                        writer.println("<th>" + full + "</th>");
                    }
                }
                writer.println("</tr>");
                String query3;

                for (int as = 0; as < assignments.size(); as++){
                    writer.println("<tr><th>" + assignments.get(as) + "</th>");
                    for (int s = 0; s < stuids.size(); s++){
                        query3 = "select " + assignments.get(as) + " from " + clasx + " where student_id=" + stuids.get(s);
                        ResultSet rs3 = stmt3.executeQuery(query3);
                        System.out.println("Checkpoint2");
                        String Grade = "";
                        while (rs3.next()){
                            Grade = rs3.getString(assignments.get(as));
                            System.out.println(Grade);
                        }
                        writer.println("<td><input type=\"text\" name=\"" +
                                clasx + assignments.get(as) + stuids.get(s) + "\"" +
                                "value=\""+Grade+"\"></td>");
                    }
                    writer.println("</tr>");
                }
                writer.println("<table>");


            }
            writer.println("<input type=\"submit\">");



        }catch (Exception exe){
            System.out.println(exe);
        }

    %>

</body>
</html>
