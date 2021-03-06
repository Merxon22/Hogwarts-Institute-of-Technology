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

    <link rel="stylesheet" href="css/mainStyle.css">
    <link rel="icon" href="ResourceFolder/Icon.png">
</head>
<body style="background-image: url('https://cdn.cdnparenting.com/articles/2018/06/471582446_H.webp'); background-size: cover; background-attachment: fixed;"><center>
    <%@include file="module/headerLoggedIn.jsp"%>
    <div class="centerBox">
        <h2 style="padding-bottom: 20px; margin-bottom: 20px; border-bottom: 1px solid darkgrey"><b>Upload Assignments</b></h2>
        <p>Type the assignments you want to add, in the format of class name/assignment name.</p>
        <p>e.g. math/final</p>
        <p>To add multiple, separate by the break of line.</p>
        <form action = AddAss method = "post" style="padding: 20px 0px;">
            <textarea class="form-control" id="floatingTextarea2" name = "ass" style="height: 200px; resize: none"></textarea>
            <a href="TeaBack" style="width: 80px;"><button class="btn btn-primary" style="width: 80px; margin-top: 20px;" type="button">Back</button></a>
            <input type="submit" class="btn btn-primary" value="Submit" style="width: 80px; margin-top: 20px;">
        </form>
    </div>
    <%@include file="module/footer.jsp"%>
</center></body>
</html>
