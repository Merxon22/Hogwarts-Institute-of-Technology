<%--
  Created by IntelliJ IDEA.
  User: assassin-001
  Date: 2021/12/22
  Time: 1:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Portal</title>

    <link rel="stylesheet" href="css/mainStyle.css">
    <link rel="icon" href="ResourceFolder/Icon.png">
</head>
<body background="https://tjn-blog-images.s3.amazonaws.com/wp-content/uploads/2015/09/20003023/Which-Fields-Have-the-Highest-Paying-Administrative-Jobs.jpg" style="background-size: cover"><center>
    <%@include file="module/headerLoggedIn.jsp"%>
    <div id="containerBox">
        <div class="centerBox">
            <h2><b>Welcome, admin!</b></h2>
            <br>
            <div class="d-grid gap-4 mx-auto">
                <a href="AddStudentAcc.jsp"><button class="btn btn-primary" style="width: 100%;" type="button">Add students</button></a>
                <a href="AddTeacherAcc.jsp"><button class="btn btn-primary" style="width: 100%;" type="button">Add teachers</button></a>
                <a href="DelStu.jsp"><button class="btn btn-primary" style="width: 100%;" type="button">Delete students</button></a>
                <a href="DelTea.jsp"><button class="btn btn-primary" style="width: 100%;" type="button">Delete teachers</button></a>
                <a href="ViewStu"><button class="btn btn-primary" style="width: 100%;" type="button">All students</button></a>
                <a href="ViewTea"><button class="btn btn-primary" style="width: 100%;" type="button">All teachers</button></a>
                <a href="ViewClassInfo"><button class="btn btn-primary" style="width: 100%;" type="button">View current classes</button></a>
            </div>
        </div>
    </div>
    <%@include file="module/footer.jsp"%>
</body>
</html>
