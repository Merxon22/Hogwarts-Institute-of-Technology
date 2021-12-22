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

    if(cookies != null)
    {
        for (int i = 0; i < cookies.length; i++){
            if (cookies[i].getName().equals("email")){
                cookie = cookies[i];
            }
        }
    }
    else
    {
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.include(request,response);
        write.println("<h3>Please login!</h3>");
    };
    write.println("<h3>Welcome, " + cookie.getValue() + "!</h3>");

%>