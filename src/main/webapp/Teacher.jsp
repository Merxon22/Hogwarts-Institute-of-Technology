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
    <title>Teacher Portal</title>

    <link rel="stylesheet" href="css/mainStyle.css">
    <link rel="icon" href="ResourceFolder/Icon.png">
</head>
<body background="https://ww2.kqed.org/app/uploads/sites/23/2015/05/Beard-Algorithm-1440x811.jpg" style="background-size: cover"><center>
<%@include file="module/headerLoggedIn.jsp"%>
<%@include file="module/CheckLog.jsp"%>
<div id="containerBox">
    <div class="centerBox">
        <br>
        <div class="d-grid gap-4 mx-auto">
            <a href=ViewSche><button class="btn btn-primary" style="width: 100%;" type="button">View my Schedule</button></a>
            <a href="AddStudentAcc.jsp"><button class="btn btn-primary" style="width: 100%;" type="button">View the participants of my class</button></a>
            <a href="AddStudentAcc.jsp"><button class="btn btn-primary" style="width: 100%;" type="button">Check attendance</button></a>
            <a href="AddAss.jsp"><button class="btn btn-primary" style="width: 100%;" type="button">Upload assignment</button></a>
            <a href="AddStudentAcc.jsp"><button class="btn btn-primary" style="width: 100%;" type="button">Give grades</button></a>
        </div>
    </div>
</div>
<%@include file="module/footer.jsp"%>
</body>
</html>
