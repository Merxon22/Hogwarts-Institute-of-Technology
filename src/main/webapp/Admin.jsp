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
        <div class="centerBox" style="width: 80%;">
            <h2><b>Welcome, admin!</b></h2>
            <%@include file="module/CheckLog.jsp"%>
            <br>
<%--            <div class="d-grid gap-4 mx-auto">--%>
<%--                <a href="AddStudentAcc.jsp"><button class="btn btn-primary" style="width: 100%;" type="button">Add students</button></a>--%>
<%--                <a href="AddTeacherAcc.jsp"><button class="btn btn-primary" style="width: 100%;" type="button">Add teachers</button></a>--%>
<%--                <a href="DelStu.jsp"><button class="btn btn-primary" style="width: 100%;" type="button">Delete students</button></a>--%>
<%--                <a href="DelTea.jsp"><button class="btn btn-primary" style="width: 100%;" type="button">Delete teachers</button></a>--%>
<%--                <a href=ViewStu><button class="btn btn-primary" style="width: 100%;" type="button">All students</button></a>--%>
<%--                <a href=ViewTea><button class="btn btn-primary" style="width: 100%;" type="button">All teachers</button></a>--%>
<%--                <a href=ViewClass><button class="btn btn-primary" style="width: 100%;" type="button">View current classes</button></a>--%>
<%--            </div>--%>
            <div class="row row-cols-1 row-cols-sm-2 row-cols-md-3 g-3">
                <a href="AddStudentAcc.jsp"><div class="col">
                    <div class="card shadow-sm">
                        <img width="100%" height="225" src="https://image.cnbcfm.com/api/v1/image/106922887-1628206615260-gettyimages-887132600-as1700__08.jpeg?v=1628206641" style="object-fit: cover; border-radius: 2px;">
                        <div class="card-body">
                            <h4 class="card-text" style="margin-bottom: 10px"><b>Add Student</b></h4>
                        </div>
                    </div>
                </div></a>
                <a href="DelStu.jsp"><div class="col">
                    <div class="card shadow-sm">
                        <img width="100%" height="225" src="https://media.istockphoto.com/photos/checklist-picture-id1168750663?k=20&m=1168750663&s=612x612&w=0&h=mka7rBZRLnI2Ib8yT_h7Lf_sxLcIrm_6HE6bQ_u_HZc=" style="object-fit: cover; border-radius: 2px;">
                        <div class="card-body">
                            <h4 class="card-text" style="margin-bottom: 10px"><b>Delete Students</b></h4>
                        </div>
                    </div>
                </div></a>
                <a href=ViewStu><div class="col">
                    <div class="card shadow-sm">
                        <img width="100%" height="225" src="https://cdn.theatlantic.com/thumbor/ERSxCd-GCgpsBo6-671hyqGDIrA=/0x517:5616x3676/1600x900/media/img/mt/2019/09/GettyImages_528822224/original.jpg" style="object-fit: cover; border-radius: 2px;">
                        <div class="card-body">
                            <h4 class="card-text" style="margin-bottom: 10px"><b>View All Students</b></h4>
                        </div>
                    </div>
                </div></a>
                <a href="AddTeacherAcc.jsp"><div class="col">
                    <div class="card shadow-sm">
                        <img width="100%" height="225" src="https://www.acenet.edu/PublishingImages/Interior-Page-Heroes/For-Faculty-Credit-Transcipts.jpg?RenditionID=10" style="object-fit: cover; border-radius: 2px;">
                        <div class="card-body">
                            <h4 class="card-text" style="margin-bottom: 10px"><b>Add Teacher</b></h4>
                        </div>
                    </div>
                </div></a>
                <a href="DelTea.jsp"><div class="col">
                    <div class="card shadow-sm">
                        <img width="100%" height="225" src="https://cis.ua.edu/wp-content/uploads/2017/11/faculty-and-staff-header-1024x512.jpg" style="object-fit: cover; border-radius: 2px;">
                        <div class="card-body">
                            <h4 class="card-text" style="margin-bottom: 10px"><b>Delete Teachers</b></h4>
                        </div>
                    </div>
                </div></a>
                <a href=ViewTea><div class="col">
                    <div class="card shadow-sm">
                        <img width="100%" height="225" src="https://mgmt.wharton.upenn.edu/wp-content/uploads/2015/04/2017-Faculty_Group.jpg" style="object-fit: cover; border-radius: 2px;">
                        <div class="card-body">
                            <h4 class="card-text" style="margin-bottom: 10px"><b>View All Teachers</b></h4>
                        </div>
                    </div>
                </div></a>
            </div>
            <div class="row row-cols-1 row-cols-sm-1 row-cols-md-1 g-1" style="width: 33.33%; margin-top: 8px"><center>
                <a href=ViewClass><div class="col">
                    <div class="card shadow-sm">
                        <img width="100%" height="225" src="https://web-static.wrike.com/blog/content/uploads/2020/01/Five-Features-of-a-Good-Monthly-Employee-Work-Schedule-Template.jpg?av=718acbc1e2b21264368f12b5cc57c0e2" style="object-fit: cover; border-radius: 2px;">
                        <div class="card-body">
                            <h4 class="card-text" style="margin-bottom: 10px"><b>View Current Classes</b></h4>
                        </div>
                    </div>
                </div></a>
            </center></div>
        </div>
    </div>
    <%@include file="module/footer.jsp"%>
</body>
</html>
