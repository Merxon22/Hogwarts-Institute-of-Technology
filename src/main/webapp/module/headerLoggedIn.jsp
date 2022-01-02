<%String dummy = request.getParameter("dummy");%>
<header class="p-3 bg-dark text-white">
    <div class="container">
        <div class="d-flex flex-wrap align-items-center justify-content-between flex-column">
            <%
                Cookie[] headerCookies = request.getCookies();
                String loginState = "";
                if(headerCookies != null){
                    for(Cookie headerCookie : headerCookies){
                        if(headerCookie.getName().equals("loginState")){
                            loginState = headerCookie.getValue();
                            break;
                        }
                    }
                    switch (loginState){
                        case "student":
                            %><a href="StuBack" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none"><img src="ResourceFolder/Logo.png" style="height: 100px;"></a><%
                            break;
                        case "teacher":
                            %><a href="TeaBack" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none"><img src="ResourceFolder/Logo.png" style="height: 100px;"></a><%
                            break;
                        case "admin":
                            %><a href="AdmBack" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none"><img src="ResourceFolder/Logo.png" style="height: 100px;"></a><%
                            break;
                        default:
                            %><a href="index.jsp" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none"><img src="ResourceFolder/Logo.png" style="height: 100px;"></a><%
                    }
                }else{
                    %><a href="index.jsp" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none"><img src="ResourceFolder/Logo.png" style="height: 100px;"></a><%
                }
            %>
<%--            <a href="index.jsp" class="d-flex align-items-center mb-2 mb-lg-0 text-white text-decoration-none"><img src="ResourceFolder/Logo.png" style="height: 100px;"></a>--%>
            <div class="text-end"><center>
                <p>Logged in as <u>${cookie["email"].getValue()}</u></p>
                <a href=Logout><button type="button" class="btn btn-warning">Log out</button></a>
            </center></div>
        </div>
    </div>
</header>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
