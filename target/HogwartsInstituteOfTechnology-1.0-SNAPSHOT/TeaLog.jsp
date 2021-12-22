<%--
  Created by IntelliJ IDEA.
  User: assassin-001
  Date: 2021/12/21
  Time: 9:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Teacher Login</title>

    <link rel="stylesheet" href="css/mainStyle.css">
    <link rel="icon" href="ResourceFolder/Icon.png">
</head>

<body background="https://ww2.kqed.org/app/uploads/sites/23/2015/05/Beard-Algorithm-1440x811.jpg" style="background-size: cover"><center>
<%@include file="module/header.jsp"%>
<div id="containerBox">
<form style="background-color: white; width: 40%; border-radius: 5px; box-shadow: 0px 0px 8px 2px black; margin: 10% 40px; padding: 40px 80px" action="TeaLog" method="get">
    <h2><b>Teacher Login</b></h2>
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

<%@include file="module/footer.jsp"%>
</center></body>
</html>
