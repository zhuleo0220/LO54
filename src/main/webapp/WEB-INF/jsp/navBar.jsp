<%--
  Created by IntelliJ IDEA.
  User: zhuruiqing
  Date: 11/05/2021
  Time: 17:46
  To change this template use File | Settings | File Templates.
--%>
<html>


<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="generator" content="Mobirise v5.3.5, mobirise.com">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1">
    <link rel="shortcut icon" href="/static/images/logo.png" type="image/x-icon">
    <meta name="description" content="">
    <title>Home</title>
    <link rel="stylesheet" href="/static/tether/tether.min.css">
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap-grid.min.css">
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap-reboot.min.css">
    <link rel="stylesheet" href="/static/dropdown/css/style.css">
    <link rel="stylesheet" href="/static/socicon/css/styles.css">
    <link rel="stylesheet" href="/static/theme/css/style.css">
    <link rel="stylesheet" href="/static/transaction/alert.css">
    <link rel="stylesheet" href="/static/usersession/userNavBar.css">
    <link rel="preload" href="https://fonts.googleapis.com/css?family=Jost:100,200,300,400,500,600,700,800,900,100i,200i,300i,400i,500i,600i,700i,800i,900i&display=swap" as="style" onload="this.onload=null;this.rel='stylesheet'">
    <noscript><link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Jost:100,200,300,400,500,600,700,800,900,100i,200i,300i,400i,500i,600i,700i,800i,900i&display=swap"></noscript>
    <link rel="preload" as="style" href="/static/theme/css/mbr-additional.css"><link rel="stylesheet" href="/static/theme/css/mbr-additional.css" type="text/css">
</head>
<body>
<section class="menu menu1 cid-svkdE2y6KA" once="menu" id="menu1-1">
    <nav class="navbar navbar-dropdown navbar-fixed-top navbar-expand-lg">
        <div class="container">
            <div class="navbar-brand">
                <span class="navbar-caption-wrap"><a class="navbar-caption text-black text-primary display-7" href="/Home/Homepage">Ecole</a></span>
            </div>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <div class="hamburger">
                    <span></span>
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav nav-dropdown nav-right" data-app-modern-menu="true"><li class="nav-item"><a class="nav-link link text-black text-primary display-4" href="listCourse.jsp">Catalogue</a></li>
                    <li class="nav-item"><a class="nav-link link text-black text-primary display-4" href="/Location/addpage">Ajouter une ville</a></li>
                    <li class="nav-item"><a class="nav-link link text-black text-primary display-4" href="addCourseForm.jsp">Ajouter un cours</a></li>
                    <%
                        Cookie cookie = null;
                        Cookie[] cookies = null;
                        boolean found = false;

                        // Get an array of Cookies associated with the this domain
                        cookies = request.getCookies();

                        if(cookies != null) {

                            for (int i = 0; i < cookies.length; i++) {
                                cookie = cookies[i];
                                if(cookie.getName().equals("emailUser")){
                                    found = true;
                                    out.println("<button class=\"button button2\" onclick=\"location.href='/client/userAccount.jsp?email=" + cookie.getValue() + "';\">Mon compte</button>");
                                }
                            }
                        }

                        if(!found){
                            out.println("<button class=\"button button2\" onclick=\"document.getElementById('id01').style.display='block'\">Se connecter</button>");
                        }

                    %>
                </ul>
            </div>
        </div>

        <%
            String message = request.getParameter("success");

            if(message != null && message != ""){
                out.println("<div class=\"alert success\">");
                out.println("<span class=\"closebtnalert\" onclick=\"this.parentElement.style.display='none';\">&times;</span>");
                out.println("<strong>Succès!</strong> " + message);
                out.println("</div>");
            }
        %>

    </nav>
</section>
<!-- Modal HTML -->
<div id="id01" class="modal">
    <form class="modal-content animate" action="/Login/doLogin.do" method="POST">
        <p></p>
        <h1 style="text-align:center"><strong>Login</strong></h1>

        <div class="container">
            <input type="email" placeholder="email" name="email" required>
            <button class="loginButton" type="submit">Login</button>
        </div>

        <div class="container" style="background-color:#f1f1f1">
            <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
        </div>
    </form>
</div>
</body>
</html>
