<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Hogwarts Institute of Technology</title>

    <link rel="stylesheet" href="css/mainStyle.css">
    <link rel="icon" href="ResourceFolder/Icon.png">

    <style>
        .item img{
            max-width: none;!important;
            min-height: 100%;!important;
            min-width: 100%; !important;
            object-fit:cover; !important;
        }
    </style>
</head>
<body>
<%@include file="module/header.jsp"%>
<div id="containerBox">
    <div class="container-fluid" style="margin: 20px 0px;">
        <div class="carousel slide" data-ride="carousel" id="carousel">
            <ol class="carousel-indicators">
                <li data-target="#carousel" data-slide to="0" class="active"></li>
                <li data-target="#carousel" data-slide to="1"></li>
                <li data-target="#carousel" data-slide to="2"></li>
            </ol>
            <div class="carousel-inner" style="width: 100%; height: 0px; padding-bottom: 50%;">
                <div class="item active">
                    <img src="https://wp-media.petersons.com/blog/wp-content/uploads/2017/11/10124519/adolescent-adult-blur-933964.jpg">

                    <div class="container" style="position: relative; float: left">
                        <div class="carousel-caption text-end" style="position:absolute; transform: translateY(-30em); border: white 3px solid; border-radius: 10px; padding: 20px; box-shadow: black 0px 0px 10px 5px">
                            <h1 style="font-size: 60px; text-shadow: 0px 0px 5px black"><b>HIT-Explore Your Future Here</b></h1>
                            <p style="font-size: 20px; text-shadow: 0px 0px 5px black">Meet the top university education in the nation.</p>
                        </div>
                    </div>
                </div>
                <div class="item">
                    <img src="https://www.your-space.in/wp-content/uploads/2020/09/15-tips-to-kickstart-your-college-life-online.jpg">
                    <div class="carousel-caption text-end" style="position:absolute; transform: translateY(-30em); border: white 3px solid; border-radius: 10px; padding: 20px; box-shadow: black 0px 0px 10px 5px">
                        <h1 style="font-size: 60px; text-shadow: 0px 0px 5px black"><b>HIT-Diverse Student Community</b></h1>
                        <p style="font-size: 20px; text-shadow: 0px 0px 5px black">Interact with students from differnet backgrounds.</p>
                    </div>
                </div>
                <div class="item">
                    <img src="https://cdn.ceoworld.biz/wp-content/uploads/2021/08/university-1.jpg">
                    <div class="carousel-caption text-end" style="position:absolute; transform: translateY(-30em); border: white 3px solid; border-radius: 10px; padding: 20px; box-shadow: black 0px 0px 10px 5px">
                        <h1 style="font-size: 60px; text-shadow: 0px 0px 5px black"><b>HIT-Top Teacher Resources</b></h1>
                        <p style="font-size: 20px; text-shadow: 0px 0px 5px black">We have teachers graduated from world-known universities.</p>
                    </div>
                </div>
            </div>
            <a class="carousel-control left" href="#carousel" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
            </a>
            <a class="carousel-control right" href="#carousel" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
            </a>
        </div>
    </div>

    <div class="container marketing" style="margin-bottom: 40px">
        <center>
        <h1 style="font-size: 60px; margin: 40px"><b>Meet Our Admin Team</b></h1>
        <div class="row">
            <div class="col-lg-4">
                <img src="ResourceFolder/Peter.jpg" class="rounded-circle" width="140" height="140">
                <h2>Peter Yuan</h2>
                <p>Frontend developer for HIT website.</p>
            </div><!-- /.col-lg-4 -->
            <div class="col-lg-4">
                <img src="ResourceFolder/Barbara.jpg" class="rounded-circle" width="140" height="140">

                <h2>Barbara Su</h2>
                <p>Teacher and admin backend developer for HIT website.</p>
            </div><!-- /.col-lg-4 -->
            <div class="col-lg-4">
                <img src="ResourceFolder/Tang.png" class="rounded-circle" width="140" height="140">

                <h2>Tang Tang</h2>
                <p>Student backend developer for HIT website</p>
            </div><!-- /.col-lg-4 -->
        </div><!-- /.row -->
        </center>
<%--        <hr class="featurette-divider">--%>

<%--        <div class="row featurette">--%>
<%--            <div class="col-md-7">--%>
<%--                <h2 class="featurette-heading">First featurette heading. <span class="text-muted">It’ll blow your mind.</span></h2>--%>
<%--                <p class="lead">Some great placeholder content for the first featurette here. Imagine some exciting prose here.</p>--%>
<%--            </div>--%>
<%--            <div class="col-md-5">--%>
<%--                <svg class="bd-placeholder-img bd-placeholder-img-lg featurette-image img-fluid mx-auto" width="500" height="500" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: 500x500" preserveAspectRatio="xMidYMid slice" focusable="false"><title>Placeholder</title><rect width="100%" height="100%" fill="#eee"></rect><text x="50%" y="50%" fill="#aaa" dy=".3em">500x500</text></svg>--%>

<%--            </div>--%>
<%--        </div>--%>
    </div>
</div>
<%@include file="module/footer.jsp"%>
</body>
</html>