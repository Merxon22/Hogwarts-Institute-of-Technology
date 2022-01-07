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
    <div class="centerBox" style="width: 80%">
        <h2><b>Welcome, teacher!</b></h2>
        <br>
<%--        <div class="d-grid gap-4 mx-auto">--%>
<%--            <a href=ViewSche><button class="btn btn-primary" style="width: 100%;" type="button">View my Schedule</button></a>--%>
<%--            <a href=ViewParticipants><button class="btn btn-primary" style="width: 100%;" type="button">View the participants of my class</button></a>--%>
<%--            <a href="CheckAtt.jsp"><button class="btn btn-primary" style="width: 100%;" type="button">Check attendance</button></a>--%>
<%--            <a href="AddAss.jsp"><button class="btn btn-primary" style="width: 100%;" type="button">Upload assignment</button></a>--%>
<%--            <a href="Grading.jsp"><button class="btn btn-primary" style="width: 100%;" type="button">Give grades</button></a>--%>
<%--        </div>--%>
        <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
            <a href="ViewSche"><div class="col">
                <div class="card shadow-sm">
                    <img width="100%" height="225" src="https://transcode-v2.app.engoo.com/image/fetch/f_auto,c_lfill,h_128,dpr_3/https://assets.app.engoo.com/images/6j6RLc94A2iUae44YhEvsh.png" style="object-fit: cover; border-radius: 2px;">
                    <div class="card-body">
                        <h4 class="card-text" style="margin-bottom: 10px"><b>View My Schedule</b></h4>
                    </div>
                </div>
            </div></a>
            <a href="ViewParticipants"><div class="col">
                <div class="card shadow-sm">
                    <img width="100%" height="225" src="https://ichef.bbci.co.uk/news/976/cpsprodpb/B422/production/_114141164_gettyimages-522737859.jpg" style="object-fit: cover; border-radius: 2px;">
                    <div class="card-body">
                        <h4 class="card-text" style="margin-bottom: 10px"><b>View Participants of my Class</b></h4>
                    </div>
                </div>
            </div></a>
            <a href=Attendance.jsp><div class="col">
                <div class="card shadow-sm">
                    <img width="100%" height="225" src="https://www.gradelink.com/wp-content/uploads/2016/03/DigitalAttendance2.jpg" style="object-fit: cover; border-radius: 2px;">
                    <div class="card-body">
                        <h4 class="card-text" style="margin-bottom: 10px"><b>Check Attendance</b></h4>
                    </div>
                </div>
            </div></a>
            <a href="AddAss.jsp"><div class="col">
                <div class="card shadow-sm">
                    <img width="100%" height="225" src="https://ltl.lincoln.ac.nz/wp-content/uploads/sites/20/2016/02/Actively-reading-the-assignment-question.jpg" style="object-fit: cover; border-radius: 2px;">
                    <div class="card-body">
                        <h4 class="card-text" style="margin-bottom: 10px"><b>Upload Assignment</b></h4>
                    </div>
                </div>
            </div></a>
            <a href="Grades.jsp"><div class="col">
                <div class="card shadow-sm">
                    <img width="100%" height="225" src="https://s18670.pcdn.co/wp-content/uploads/Untitled-design-52-1.png" style="object-fit: cover; border-radius: 2px;">
                    <div class="card-body">
                        <h4 class="card-text" style="margin-bottom: 10px"><b>Upload Grades</b></h4>
                    </div>
                </div>
            </div></a>
            <a href="EditMyProfileT"><div class="col">
                <div class="card shadow-sm">
                    <img width="100%" height="225" src="https://img.freepik.com/free-photo/profile-serious-woman-with-healthy-pure-skin-has-bushy-hairstyle_273609-44522.jpg?size=626&ext=jpg&ga=GA1.2.1006149401.1635465600" style="object-fit: cover; border-radius: 2px;">
                    <div class="card-body">
                        <h4 class="card-text" style="margin-bottom: 10px"><b>My Profile</b></h4>
                    </div>
                </div>
            </div></a>
        </div>
    </div>
</div>
<%@include file="module/footer.jsp"%>
</body>
</html>
