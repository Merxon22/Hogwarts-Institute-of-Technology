<%--
  Created by IntelliJ IDEA.
  User: assassin-001
  Date: 2021/12/22
  Time: 3:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Portal</title>

    <link rel="stylesheet" href="css/mainStyle.css">
    <link rel="icon" href="ResourceFolder/Icon.png">
</head>
<body style="background-image: url('https://londoninformaticsacademy.com/wp-content/uploads/2019/01/Gordon-College.jpg'); background-repeat: no-repeat; background-size: cover; background-position: center"><center>
    <%@include file="module/headerLoggedIn.jsp"%>
    <%@include file="module/CheckLog.jsp"%>
    <div id="containerBox">
        <div class="centerBox">
            <h2><b>Welcome, student!</b></h2>
            <br>
            <div class="d-grid gap-4 mx-auto">
                <a href="AddTeacherAcc.jsp"><button class="btn btn-primary" style="width: 100%;" type="button">View my schedule</button></a>
                <a href="AddStudentAcc.jsp"><button class="btn btn-primary" style="width: 100%;" type="button">View my grade</button></a>
                <a href=Logout><button class="btn btn-primary" style="width: 100%;" type="button">Logout</button></a>
            </div>
        </div>
    </div>
    <%@include file="module/footer.jsp"%>
</center></body>
</html>
