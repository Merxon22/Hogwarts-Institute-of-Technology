<%--
  Created by IntelliJ IDEA.
  User: 12576
  Date: 2021-12-22
  Time: 06:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Login</title>

    <link rel="stylesheet" href="css/mainStyle.css">
    <link rel="icon" href="ResourceFolder/Icon.png">
</head>

<body background="https://londoninformaticsacademy.com/wp-content/uploads/2019/01/Gordon-College.jpg" style="background-size: cover; background-position: center"><center>
    <%@include file="module/header.jsp"%>
    <div id="containerBox">
        <div class="centerBox">
            <form action = "StuLog" method = "get">
                <h2><b>Student Login</b></h2>
                <br>
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Email address</label>
                    <input type="email" name="email" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp">
                </div>
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Password</label>
                    <input type="password" name="pwd" class="form-control" id="exampleInputPassword1">
                </div>
                <button type="submit" class="btn btn-primary">Login</button>
            </form>
        </div>
    </div>
    <%@include file="module/footer.jsp"%>
</center></body>
</html>
