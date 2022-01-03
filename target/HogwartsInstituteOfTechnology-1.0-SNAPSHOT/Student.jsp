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
        <div class="centerBox" style="width: 80%;">
            <h2><b>Welcome, student!</b></h2>
            <br>
<%--            <div class="d-grid gap-4 mx-auto">--%>
<%--                <a href="StudentSchduleView"><button class="btn btn-primary" style="width: 100%;" type="button">View my schedule</button></a>--%>
<%--                <a href="StudentGradeView"><button class="btn btn-primary" style="width: 100%;" type="button">View my grade</button></a>--%>
<%--                <a href="StudentAttendenceView"><button class="btn btn-primary" style="width: 100%;" type="button">View my Attendance</button></a>--%>
<%--            </div>--%>
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                <a href="StudentSchduleView"><div class="col">
                    <div class="card shadow-sm">
                        <img width="100%" height="225" src="https://www.jobillico.com/blog/wp-content/uploads/2020/03/Benefits-of-a-Flexible-Work-Schedule.jpg" style="object-fit: cover; border-radius: 2px;">
                        <div class="card-body">
                            <h4 class="card-text" style="margin-bottom: 10px"><b>View My Schedule</b></h4>
                        </div>
                    </div>
                </div></a>
                <a href="StudentGradeView"><div class="col">
                    <div class="card shadow-sm">
                        <img width="100%" height="225" src="https://www.thoughtco.com/thmb/MNAuHiUw6oxN3RUkpCdutm9Aj-s=/3888x2592/filters:fill(auto,1)/the-best-157314672-5a2ffaf8beba33003773ac96.jpg" style="object-fit: cover; border-radius: 2px;">
                        <div class="card-body">
                            <h4 class="card-text" style="margin-bottom: 10px"><b>View My Grade</b></h4>
                        </div>
                    </div>
                </div></a>
                <a href=StudentAttendenceView><div class="col">
                    <div class="card shadow-sm">
                        <img width="100%" height="225" src="https://mitrefinch.com/wp-content/uploads/2016/09/Attendance-Tracking-Software-for-Schools.jpg" style="object-fit: cover; border-radius: 2px;">
                        <div class="card-body">
                            <h4 class="card-text" style="margin-bottom: 10px"><b>Check Attendance</b></h4>
                        </div>
                    </div>
                </div></a>
            </div>
        </div>
    </div>
    <%@include file="module/footer.jsp"%>
</center></body>
</html>
