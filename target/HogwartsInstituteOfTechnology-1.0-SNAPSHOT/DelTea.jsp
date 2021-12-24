<%--
  Created by IntelliJ IDEA.
  User: assassin-001
  Date: 2021/12/21
  Time: 3:03 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Teacher</title>

    <link rel="stylesheet" href="css/mainStyle.css">
    <link rel="icon" href="ResourceFolder/Icon.png">
</head>
<body background="https://tjn-blog-images.s3.amazonaws.com/wp-content/uploads/2015/09/20003023/Which-Fields-Have-the-Highest-Paying-Administrative-Jobs.jpg" style="background-size: cover"><center>
    <%@include file="module/headerLoggedIn.jsp"%>
    <div id="containerBox">
        <div class="centerBox">
            <h2 style="padding-bottom: 20px; margin-bottom: 20px; border-bottom: 1px solid darkgrey"><b>Delete teacher</b></h2>
            <h4><b>Instruction:</b></h4>
            <p>Enter the email of teachers you want to delete.</p>
            <p>To enter another, separate by the break of line.</p>
            <p>If you enter a wrong email address, nothing will happen.</p>
            <form action = "DelTea" method = "post" style="padding: 20px 0px;">
                <div class="form-floating" style="text-align: left; !important;">
                    <textarea class="form-control" id="floatingTextarea2" name="names" style="height: 200px; resize: none"></textarea>
                </div>
                <input class="btn btn-primary" type="button" value="Back" onclick="history.back();" style="width: 80px; margin-top: 20px;">
                <input class="btn btn-primary" type="submit" value="Submit" style="width: 80px; margin-top: 20px;">
            </form>
        </div>
    </div>
    <%@include file="module/footer.jsp"%>
</center></body>

</body>
</html>