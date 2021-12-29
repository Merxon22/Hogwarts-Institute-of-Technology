<%--
  Created by IntelliJ IDEA.
  User: assassin-001
  Date: 2021/12/23
  Time: 2:10 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload Assignments</title>
</head>
<body>
    <h3>Type the assignments you want to add, in the format of class name/assignment name.
        e.g. math/final
        To add multiple, separate by the break of line.</h3>
    <form action = AddAss method = "post">
        <textarea name = "ass"></textarea>
        <input type="submit">
    </form>
</body>
</html>
