<%--
  Created by IntelliJ IDEA.
  User: assassin-001
  Date: 2021/12/21
  Time: 2:40 AM
  To change this template use File | Settings | File Templates.
--%>
//明早请把加减老师，学生搞完！
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<header>
    Instruction: please enter the names of students you want to delete. Enter in the format of
    First name/Last name/Email/Grade/Class
    for example: Tano/Ahsoka/ATano@hit.edu/10/A

    to enter another, separate by the break of line
</header>
<body>
<form action = "AddStu" method = "post">
    <input type = "text" name = "names">
    <input type="submit">
</form>

</body>

</body>
</html>
