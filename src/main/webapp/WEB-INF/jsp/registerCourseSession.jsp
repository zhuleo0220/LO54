<%-- 
    Document   : registerCourseSession
    Created on : 7 mai 2021, 09:19:51
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
  <title>registerCourseSession</title>
  <link rel="stylesheet" href="ressources/assets/tether/tether.min.css">
  <link rel="stylesheet" href="ressources/assets/bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="ressources/assets/bootstrap/css/bootstrap-grid.min.css">
  <link rel="stylesheet" href="ressources/assets/bootstrap/css/bootstrap-reboot.min.css">
  <link rel="stylesheet" href="ressources/assets/dropdown/css/style.css">
  <link rel="stylesheet" href="ressources/assets/formstyler/jquery.formstyler.css">
  <link rel="stylesheet" href="ressources/assets/formstyler/jquery.formstyler.theme.css">
  <link rel="stylesheet" href="ressources/assets/datepicker/jquery.datetimepicker.min.css">
  <link rel="stylesheet" href="ressources/assets/socicon/css/styles.css">
  <link rel="stylesheet" href="ressources/assets/theme/css/style.css">
  <link rel="stylesheet" href="ressources/assets/usersession/userNavBar.css">
  <link rel="preload" href="https://fonts.googleapis.com/css?family=Jost:100,200,300,400,500,600,700,800,900,100i,200i,300i,400i,500i,600i,700i,800i,900i&display=swap" as="style" onload="this.onload=null;this.rel='stylesheet'">
  <noscript><link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Jost:100,200,300,400,500,600,700,800,900,100i,200i,300i,400i,500i,600i,700i,800i,900i&display=swap"></noscript>
  <link rel="preload" as="style" href="ressources/assets/theme/css/mbr-additional.css"><link rel="stylesheet" href="ressources/assets/theme/css/mbr-additional.css" type="text/css">
</head>
<body>
  <section class="menu menu1 cid-svAfBpOv1f" once="menu" id="menu1-k">
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
                
<section class="form7 cid-svAfBvqTZs" id="form7-l">
    <div class="container">
        <div class="mbr-section-head">
            <h3 class="mbr-section-title mbr-fonts-style align-center mb-0 display-2">
                <strong>S'inscrire à la session</strong></h3> 
        </div>
        <div class="row justify-content-center mt-4">
            <div class="col-lg-8 mx-auto mbr-form" data-form-type="formoid">
                <form action="/client/registerCourseSessionServlet" method="POST" class="mbr-form form-with-styler mx-auto" data-form-title="Form Name">
                    <input type="hidden" name="courseSession" value="1">
                    <p class="mbr-text mbr-fonts-style align-center mb-4 display-7">
                        Entrer les informations ci-dessous pour ajouter le nouveau cours</p>
                    <div class="row">
                        <div hidden="hidden" data-form-alert="" class="alert alert-success col-12">Thanks for filling
                            out the form!</div>
                        <div hidden="hidden" data-form-alert-danger="" class="alert alert-danger col-12">Oops...! some
                            problem!</div>
                    </div>
                    <div class="dragArea row">
                        <div class="col-lg-12 col-md-12 col-sm-12 form-group" data-for="courseSessionId">
                            <% out.println("<input hidden=\"hidden\" type=\"text\" name=\"courseSessionId\" data-form-field=\"courseSessionId\" class=\"form-control\" value=\"" + request.getParameter("courseSession") + "\" id=\"courseSessionId\">"); %>
                        </div>
                        <div class="col-lg-12 col-md-12 col-sm-12 form-group" data-for="lastname">
                            <input type="text" name="lastname" placeholder="Nom" data-form-field="lastname" class="form-control" value="" id="lastname-form7-l" pattern="[a-zA-Z]+" required>
                        </div>
                        <div class="col-lg-12 col-md-12 col-sm-12 form-group" data-for="firstname">
                            <input type="text" name="firstname" placeholder="Prénom" data-form-field="firstname" class="form-control" value="" id="firstname-form7-l" pattern="[a-zA-Z]+" required>
                        </div>
                        <div class="col-lg-12 col-md-12 col-sm-12 form-group" data-for="address">
                            <input type="text" name="address" placeholder="Adresse" data-form-field="address" class="form-control" value="" id="address-form7-l" required>
                        </div>
                        <div data-for="phone" class="col-lg-12 col-md-12 col-sm-12 form-group">
                            <input type="tel" name="phone" placeholder="Téléphone" data-form-field="phone" class="form-control" value="" id="phone-form7-l" required>
                        </div>
                        <div data-for="email" class="col-lg-12 col-md-12 col-sm-12 form-group">
                            <input type="email" name="email" placeholder="Email" data-form-field="email" class="form-control" value="" id="email-form7-l" required>
                        </div>
                        <div class="col-auto mbr-section-btn align-center">
                            <button type="submit" class="btn btn-primary display-4">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section><section style="background-color: #fff; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Helvetica Neue', Arial, sans-serif; color:#aaa; font-size:12px; padding: 0; align-items: center; display: flex;"></section><script src="ressources/assets/web/ressources/assets/jquery/jquery.min.js"></script>  <script src="ressources/assets/popper/popper.min.js"></script>  <script src="ressources/assets/tether/tether.min.js"></script>  <script src="ressources/assets/bootstrap/js/bootstrap.min.js"></script>  <script src="ressources/assets/smoothscroll/smooth-scroll.js"></script>  <script src="ressources/assets/dropdown/js/nav-dropdown.js"></script>  <script src="ressources/assets/dropdown/js/navbar-dropdown.js"></script>  <script src="ressources/assets/touchswipe/jquery.touch-swipe.min.js"></script>  <script src="ressources/assets/formstyler/jquery.formstyler.js"></script>  <script src="ressources/assets/formstyler/jquery.formstyler.min.js"></script>  <script src="ressources/assets/datepicker/jquery.datetimepicker.full.js"></script>  <script src="ressources/assets/theme/js/script.js"></script>  <script src="ressources/assets/formoid/formoid.min.js"></script>
</body>
</html>
