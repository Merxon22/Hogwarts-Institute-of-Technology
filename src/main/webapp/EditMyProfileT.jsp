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
        <title>Add Teacher Account</title>
</head>
<body>
<form action="AddTea2" method="post">
    <div>
        <label>Firstname</label>
        <input type=text name="fname" required="required">
    </div>
    <div>
        <label>Lastname</label>
        <input type=text name="lname" required="required">
    </div>
    <div>
        <label>Email</label>
        <input type=email name="email" placeholder="...@hit.edu" required="required">
    </div>
    <div>
        <label>Gender</label>
        <input type=text name="gender">
    </div>
    <div>
        <label>Date of Birth</label>
        <input type=text name="birth">
    </div>
    <div>
        <label>Nationality</label>
        <input type=text name="nation">
    </div>
    <div>
        <label>Description</label>
        <input type=text name="des">
    </div>
    <div>
        <label>Phone Number</label>
        <input type=text name="phone">
    </div>
    <div>
        <label>Degree</label>
        <input type=text name="de">
    </div>
    <div>
        <label>Teaching Courses</label>
        <input type=text name="class">
    </div>

    <a href="AdmBack"><button type="button">Back</button></a>
    <button type="submit" >Submit</button>
</form>

</body>
</html>

