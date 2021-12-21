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
                </div>
                <div class="item">
                    <img src="https://www.your-space.in/wp-content/uploads/2020/09/15-tips-to-kickstart-your-college-life-online.jpg">
                </div>
                <div class="item">
                    <img src="https://cdn.ceoworld.biz/wp-content/uploads/2021/08/university-1.jpg">
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
</div>
<%@include file="module/footer.jsp"%>
</body>
</html>