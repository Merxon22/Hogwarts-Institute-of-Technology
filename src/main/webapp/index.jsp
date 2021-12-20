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
<header class="p-3 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-center">
            <a href="index.jsp" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none">
                <img src="ResourceFolder/Logo.png" style="height: 100px">
            </a>

            <div class="text-end">
                <button type="button" class="btn btn-warning">Student Portal</button>
                <button type="button" class="btn btn-warning">Teacher Portal</button>
                <button type="button" class="btn btn-warning">Teacher Portal</button>
            </div>
        </div>
    </div>
</header>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>

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

<footer style="width: 100%; background-color: #343a40; color: white; padding: 20px; margin-top: 20px"><center>
    <div class="border-bottom" style="width: 60%">
        <b><h2>Contact Us:</h2></b>
        <p>Email: hogwarts@hit.edu</p>
        <p>Tel: +86 12345679812</p>
    </div>
    <p style="margin: 10px; color: lightgrey">Hogwarts Institute of Technology, 2021</p>
</center></footer>
</body>
</html>