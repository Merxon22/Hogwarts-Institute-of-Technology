<%--
  Created by IntelliJ IDEA.
  User: assassin-001
  Date: 2021/12/21
  Time: 2:40 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<header>
    Instruction: please enter the names of students you want to add. Enter in the format of First name/Last name/Email
    for example: Tano/Ahsoka/Tano@hit.edu
    to enter another, separate by the break of line
    Note that the replicated account (with same email address) will be ignore
</header>
<body>
    <form action = "AddTea" method = "post">
        <textarea name = "names"></textarea>
        <input type="submit">
    </form>

</body>
</html>
