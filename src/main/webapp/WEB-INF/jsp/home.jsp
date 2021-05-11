<%--
    Document   : home
    Created on : 28 avr. 2021, 17:28:48
    Author     : Neil FARMER/Zhu RUIQING
--%>

<%@page import="java.util.Arrays"%>
<%@ page import="org.hibernate.Session" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html  >
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="generator" content="Mobirise v5.3.5, mobirise.com">
  <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1">
  <link rel="shortcut icon" href="ressources/assets/images/logo.png" type="image/x-icon">
  <meta name="description" content="">
  <title>Home</title>
  <link rel="stylesheet" href="ressources/assets/tether/tether.min.css">
  <link rel="stylesheet" href="ressources/assets/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="ressources/assets/bootstrap/css/bootstrap-grid.min.css">
  <link rel="stylesheet" href="ressources/assets/bootstrap/css/bootstrap-reboot.min.css">
  <link rel="stylesheet" href="ressources/assets/dropdown/css/style.css">
  <link rel="stylesheet" href="ressources/assets/socicon/css/styles.css">
  <link rel="stylesheet" href="ressources/assets/theme/css/style.css">
  <link rel="stylesheet" href="ressources/assets/transaction/alert.css">
  <link rel="stylesheet" href="ressources/assets/usersession/userNavBar.css">
  <link rel="preload" href="https://fonts.googleapis.com/css?family=Jost:100,200,300,400,500,600,700,800,900,100i,200i,300i,400i,500i,600i,700i,800i,900i&display=swap" as="style" onload="this.onload=null;this.rel='stylesheet'">
  <noscript><link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Jost:100,200,300,400,500,600,700,800,900,100i,200i,300i,400i,500i,600i,700i,800i,900i&display=swap"></noscript>
  <link rel="preload" as="style" href="ressources/assets/theme/css/mbr-additional.css"><link rel="stylesheet" href="ressources/assets/theme/css/mbr-additional.css" type="text/css">
</head>
<body>
  <section class="menu menu1 cid-svkdE2y6KA" once="menu" id="menu1-1">
    <nav class="navbar navbar-dropdown navbar-fixed-top navbar-expand-lg">
        <div class="container">
            <div class="navbar-brand">
                <span class="navbar-caption-wrap"><a class="navbar-caption text-black text-primary display-7" href="home.jsp">Ecole</a></span>
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
                    <li class="nav-item"><a class="nav-link link text-black text-primary display-4" href="addLocation.jsp">Ajouter une ville</a></li>
                    <li class="nav-item"><a class="nav-link link text-black text-primary display-4" href="addCourseForm.jsp">Ajouter un cours</a></li>
                    <%
                        HttpSession cookie = null;
                        boolean found = false;
                        Object emailUser=null;

                        // Get an array of Cookies associated with the this domain
                        cookie = request.getSession();

                        if(cookie != null) {


                            emailUser=cookie.getAttribute("emailUser");

                                if(emailUser!=null){
                                    found = true;
                                    out.println("<button class=\"button button2\" onclick=\"location.href='/client/userAccount.jsp?email=" + emailUser + "';\">Mon compte</button>");
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

<section class="header18 cid-svkdLqJpnM mbr-fullscreen" id="header18-2">
    <div class="align-center container">
        <div class="row justify-content-center">
            <div class="col-12 col-lg-10">
                <h1 class="mbr-section-title mbr-fonts-style mbr-white mb-3 display-1"><strong>École</strong></h1>

                <p class="mbr-text mbr-fonts-style mbr-white display-7">
                    Projet de catalogue de matière d'une école, réalisé dans le cadre du projet de LO54</p>
                <div class="mbr-section-btn mt-3"><a class="btn btn-primary display-4" href="listCourse.jsp">Voir le catalogue</a></div>
            </div>
        </div>
    </div>
</section>
<section class="features3 cid-svkdPHzdyv" id="features3-3">
    <div class="container">
        <div class="mbr-section-head">
            <h4 class="mbr-section-title mbr-fonts-style align-center mb-0 display-2">
                <strong>Features</strong></h4>
        </div>
        <div class="row mt-4">
            <div class="item features-image сol-12 col-md-6 col-lg-4">
                <div class="item-wrapper">
                    <div class="item-img">
                        <img src="ressources/assets/images/mbr-695x463.jpg" alt="">
                    </div>
                    <div class="item-content">
                        <h5 class="item-title mbr-fonts-style display-7"><strong>Catalogue des cours</strong></h5>

                        <p class="mbr-text mbr-fonts-style mt-3 display-7">Trouver le cours que vous souhaitez depuis le catalogue des cours.</p>
                    </div>
                    <div class="mbr-section-btn item-footer mt-2"><a href="listCourse.jsp" class="btn btn-primary item-btn display-7" target="_blank">Voir &gt;</a></div>
                </div>
            </div>
            <div class="item features-image сol-12 col-md-6 col-lg-4">
                <div class="item-wrapper">
                    <div class="item-img">
                        <img src="ressources/assets/images/mbr-1-695x463.jpg" alt="">
                    </div>
                    <div class="item-content">
                        <h5 class="item-title mbr-fonts-style display-7"><strong>Rechercher un cours</strong></h5>

                        <p class="mbr-text mbr-fonts-style mt-3 display-7">Trouver un cours grâce à son intitulé.</p>
                    </div>
                    <div class="mbr-section-btn item-footer mt-2"><a href="searchCourse.jsp" class="btn btn-primary item-btn display-7" target="_blank">Voir &gt;</a></div>
                </div>
            </div>
            <div class="item features-image сol-12 col-md-6 col-lg-4">
                <div class="item-wrapper">
                    <div class="item-img">
                        <img src="ressources/assets/images/mbr-2-695x463.jpg" alt="">
                    </div>
                    <div class="item-content">
                        <h5 class="item-title mbr-fonts-style display-7"><strong>Rechercher une session</strong></h5>

                        <p class="mbr-text mbr-fonts-style mt-3 display-7">Trouver une session proche de chez-vous et quand vous le souhaitez.<br></p>
                    </div>
                    <div class="mbr-section-btn item-footer mt-2"><a href="searchCourseSession.jsp" class="btn btn-primary item-btn display-7" target="_blank">Voir &gt;</a></div>
                </div>
            </div>
        </div>
    </div>
</section><section style="background-color: #fff; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Helvetica Neue', Arial, sans-serif; color:#aaa; font-size:12px; padding: 0; align-items: center; display: flex;"></section><script src="ressources/assets/web/ressources/assets/jquery/jquery.min.js"></script>  <script src="ressources/assets/popper/popper.min.js"></script>  <script src="ressources/assets/tether/tether.min.js"></script>  <script src="ressources/assets/bootstrap/js/bootstrap.min.js"></script>  <script src="ressources/assets/smoothscroll/smooth-scroll.js"></script>  <script src="ressources/assets/dropdown/js/nav-dropdown.js"></script>  <script src="ressources/assets/dropdown/js/navbar-dropdown.js"></script>  <script src="ressources/assets/touchswipe/jquery.touch-swipe.min.js"></script>  <script src="ressources/assets/theme/js/script.js"></script>
</body>
</html>
