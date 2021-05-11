<%-- 
    Document   : error
    Created on : 7 mai 2021, 11:13:08
    Author     : Neil FARMER/Zhu RUIQING
--%>

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
  <title>errorPage</title>
  <link rel="stylesheet" href="ressources/assets/tether/tether.min.css">
  <link rel="stylesheet" href="ressources/assets/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="ressources/assets/bootstrap/css/bootstrap-grid.min.css">
  <link rel="stylesheet" href="ressources/assets/bootstrap/css/bootstrap-reboot.min.css">
  <link rel="stylesheet" href="ressources/assets/dropdown/css/style.css">
  <link rel="stylesheet" href="ressources/assets/socicon/css/styles.css">
  <link rel="stylesheet" href="ressources/assets/theme/css/style.css">
  <link rel="stylesheet" href="ressources/assets/transaction/tabs.css">
  <link rel="stylesheet" href="ressources/assets/usersession/userNavBar.css">
  <link rel="preload" href="https://fonts.googleapis.com/css?family=Jost:100,200,300,400,500,600,700,800,900,100i,200i,300i,400i,500i,600i,700i,800i,900i&display=swap" as="style" onload="this.onload=null;this.rel='stylesheet'">
  <noscript><link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Jost:100,200,300,400,500,600,700,800,900,100i,200i,300i,400i,500i,600i,700i,800i,900i&display=swap"></noscript>
  <link rel="preload" as="style" href="ressources/assets/theme/css/mbr-additional.css"><link rel="stylesheet" href="ressources/assets/theme/css/mbr-additional.css" type="text/css">
</head>
<body>
  <section class="menu menu1 cid-svAgczTY8c" once="menu" id="menu1-o">
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
    </nav>
</section>

<!-- Modal HTML -->
<div id="id01" class="modal">
    <form class="modal-content animate" action="/client/connectionServlet" method="POST">
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
                
<section class="tabs content18 cid-svAgZpTjSR" id="tabs1-p">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-md-8">
                <h3 class="mbr-section-title mb-0 mbr-fonts-style display-2">
                    <strong>Une erreur est survenu</strong></h3> 
            </div>
        </div>
        <div class="row justify-content-center mt-4">
            <div class="tabset">
                <!-- Tab 1 -->
                <input type="radio" name="tabset" id="tab1" aria-controls="erreur" checked>
                <label for="tab1">Erreur</label>
                
                <!-- Tab 2 -->
                <input type="radio" name="tabset" id="tab2" aria-controls="solution">
                <label for="tab2">Solution</label>
  
                <div class="tab-panels">
                    <section id="erreur" class="tab-panel">
                        <h2>Erreur</h2>
                        <% 
                            String err = request.getParameter("error");
                            if(err != null && err != ""){
                                out.println("<p>" + err + "</p>");
                            }else{
                                out.println("<p>Aucune solution indique pour le moment</p>");
                            }
                        %>
                    </section>
                    <section id="solution" class="tab-panel">
                        <h2>Solution</h2>
                        <% 
                            String sol = request.getParameter("solution");
                            if(sol != null && sol != ""){
                                out.println("<p>" + sol + "</p>");
                            }else{
                                out.println("<p>Aucune solution indique pour le moment</p>");
                            }
                        %>
                    </section>
                </div>
            </div>
        </div>
    </div>
</section><section style="background-color: #fff; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Helvetica Neue', Arial, sans-serif; color:#aaa; font-size:12px; padding: 0; align-items: center; display: flex;"></section><script src="ressources/assets/web/ressources/assets/jquery/jquery.min.js"></script>  <script src="ressources/assets/popper/popper.min.js"></script>  <script src="ressources/assets/tether/tether.min.js"></script>  <script src="ressources/assets/bootstrap/js/bootstrap.min.js"></script>  <script src="ressources/assets/smoothscroll/smooth-scroll.js"></script>  <script src="ressources/assets/dropdown/js/nav-dropdown.js"></script>  <script src="ressources/assets/dropdown/js/navbar-dropdown.js"></script>  <script src="ressources/assets/touchswipe/jquery.touch-swipe.min.js"></script>  <script src="ressources/assets/mbr-tabs/mbr-tabs.js"></script>  <script src="ressources/assets/theme/js/script.js"></script>
</body>
</html>
