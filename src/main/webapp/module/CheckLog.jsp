<%--
  Created by IntelliJ IDEA.
  User: assassin-001
  Date: 2021/12/23
  Time: 12:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.io.PrintWriter" %>
<%
    PrintWriter write = response.getWriter();
    Cookie cookie = null; Cookie[] cookies = request.getCookies();

    if(cookies != null) cookie = cookies[0];

    else
    {
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.include(request,response);
        write.println("<script>\n" +
                "alert(\"Please login!\")" +
                "</script>");
    };



%>