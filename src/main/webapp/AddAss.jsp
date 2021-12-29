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
<body background="https://cdn.cdnparenting.com/articles/2018/06/471582446_H.webp" style="background-size: cover"><center>
    <%@include file="module/headerLoggedIn.jsp"%>
    <div id="containerBox">
        <div class="centerBox">
            <h2 style="padding-bottom: 20px; margin-bottom: 20px; border-bottom: 1px solid darkgrey"><b>Upload Assignments</b></h2>
            <p>Type the assignments you want to add, in the format of class name/assignment name.</p>
            <p>e.g. math/final</p>
            <p>To add multiple, separate by the break of line.</p>
            <form action = AddAss method = "post" style="padding: 20px 0px;">
                <textarea class="form-control" id="floatingTextarea2" name = "ass" style="height: 200px; resize: none"></textarea>
                <input class="btn btn-primary" type="button" value="Back" onclick="history.back();" style="width: 80px; margin-top: 20px;">
                <input type="submit" class="btn btn-primary" value="Submit" style="width: 80px; margin-top: 20px;">
            </form>
        </div>
    </div>
    <%@include file="module/footer.jsp"%>
</center></body>
</html>
