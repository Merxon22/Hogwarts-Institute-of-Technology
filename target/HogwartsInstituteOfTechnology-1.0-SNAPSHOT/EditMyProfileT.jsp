<%--
  Created by IntelliJ IDEA.
  User: Tang
  Date: 2022-01-04
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Teacher</title>

    <link rel="stylesheet" href="css/mainStyle.css">
    <link rel="icon" href="ResourceFolder/Icon.png">
</head>
<body background="https://tjn-blog-images.s3.amazonaws.com/wp-content/uploads/2015/09/20003023/Which-Fields-Have-the-Highest-Paying-Administrative-Jobs.jpg" style="background-size: cover"><center>
    <%@include file="module/headerLoggedIn.jsp"%>
    <div class="centerBox">
        <h2 style="padding-bottom: 20px; margin-bottom: 20px; border-bottom: 1px solid darkgrey"><b>Add Teacher</b></h2>
        <form action="AddTea2" method="post" style="width: 60%">
            <div style="padding: 10px">
                <label>Firstname</label>
                <input class="form-control" type=text name="fname" required="required">
            </div>
            <div style="padding: 10px">
                <label>Lastname</label>
                <input class="form-control" type=text name="lname" required="required">
            </div>
            <div style="padding: 10px">
                <label>Email</label>
                <input class="form-control" type=email name="email" placeholder="...@hit.edu" required="required">
            </div>
            <div style="padding: 10px">
                <label>Gender</label><br>
                <input type=radio name="gender" value="Male" style="transform: scale(1)" id="male"><label for="male">Male</label><br>
                <input type=radio name="gender" value="Female" style="transform: scale(1)" id="female"><label for="female">Female</label>
            </div>
            <div style="padding: 10px">
                <label>Date of Birth</label>
                <input class="form-control" type=date name="birth" required="required">
            </div>
            <div style="padding: 10px">
                <label>Nationality</label>
                <input class="form-control" type=text name="nation" required="required">
            </div>
            <div style="padding: 10px">
                <label>Description</label>
                <textarea class="form-control" class="form-control" name="des" required="required"></textarea>
            </div>
            <div style="padding: 10px">
                <label>Phone Number</label>
                <input class="form-control" type=tel name="phone" required="required">
            </div>
            <div style="padding: 10px">
                <label>Degree</label>
                <input class="form-control" type=text name="de" required="required">
            </div>
            <div style="padding: 10px">
                <label>Teaching Courses</label>
                <input class="form-control" type=text name="class" required="required">
            </div>

            <a href="AdmBack"><button class="btn btn-primary" type="button" style="width: 80px; margin-top: 20px">Back</button></a>
            <button class="btn btn-primary" type="submit" style="width: 80px; margin-top: 20px">Submit</button>
        </form>
    </div>
    <%@include file="module/footer.jsp"%>
</center></body>
</html>

