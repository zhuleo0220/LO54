<%-- 
    Document   : addCourseSession
    Created on : 30 avr. 2021, 19:13:25
    Author     : Neil FARMER/Zhu RUIQING
--%>

<%@page import="fr.utbm.school.core.entity.Location"%>
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
  <title>addCourseSession</title>
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
  <section class="menu menu1 cid-svkdE2y6KA" once="menu" id="menu1-g">
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
                
<section class="form7 cid-svAeSNlYih" id="form7-h">
    <div class="container">
        <div class="mbr-section-head">
            <h3 class="mbr-section-title mbr-fonts-style align-center mb-0 display-2">
                <strong>Ajouter une nouvelle session</strong></h3> 
        </div>
        <div class="row justify-content-center mt-4">
            <div class="col-lg-8 mx-auto mbr-form" data-form-type="formoid">
                <form action="/client/addCourseSessionServlet" method="POST" class="mbr-form form-with-styler mx-auto" data-form-title="Form Name">
                    <p class="mbr-text mbr-fonts-style align-center mb-4 display-7">
                        Entrer les informations ci-dessous pour ajouter une nouvelle session</p>
                    <div class="row">
                        <div hidden="hidden" data-form-alert="" class="alert alert-success col-12">Thanks for filling
                            out the form!</div>
                        <div hidden="hidden" data-form-alert-danger="" class="alert alert-danger col-12">Oops...! some
                            problem!</div>
                    </div>
                    <div class="dragArea row">
                        <div class="col-lg-12 col-md-12 col-sm-12 form-group" data-for="courseCode">
                            <% out.println("<input hidden=\"hidden\" type=\"text\" name=\"courseCode\" data-form-field=\"courseCode\" class=\"form-control\" value=\"" + request.getParameter("course") + "\" id=\"courseCode\">"); %>
                        </div>
                        <div class="col-lg-12 col-md-12 col-sm-12 form-group" data-for="locationId">
                            <select id="location_id" name="locationId">
                                <option value="" disabled>--Choisir une ville--</option>
                                <% 
                                    for(Location lo:fr.utbm.school.core.service.LocationService.getListLocation()){
                                        out.println("<option value=\"" + lo.getId() + "\">" + lo.getCity() + "</option>");
                                    }
                                %>
                            </select> 
                        </div>
                        <div class="col-lg-12 col-md-12 col-sm-12 form-group" data-for="startDate">
                            <input type="text" name="startDate" placeholder="Date début du cours" data-form-field="startDate" onfocus="(this.type='date')" onblur="(this.type='text')" class="form-control" value="" id="startDate-form9-j" required>
                        </div>
                        <div class="col-lg-12 col-md-12 col-sm-12 form-group" data-for="startTime">
                            <input type="text" name="startTime" placeholder="Heure debut du cours" data-form-field="startTime" onfocus="(this.type='time')" onblur="(this.type='text')" class="form-control" value="" id="endTime-form9-j" required>
                        </div>
                        <div class="col-lg-12 col-md-12 col-sm-12 form-group" data-for="endDate">
                            <input type="text" name="endDate" placeholder="Date fin du cours" data-form-field="endDate" onfocus="(this.type='date')" onblur="(this.type='text')" class="form-control" value="" id="endDate-form9-j" required>
                        </div>
                        <div class="col-lg-12 col-md-12 col-sm-12 form-group" data-for="endTime">
                            <input type="text" name="endTime" placeholder="Heure fin du cours" data-form-field="endTime" onfocus="(this.type='time')" onblur="(this.type='text')" class="form-control" value="" id="endTime-form9-j" required>
                        </div>
                        <div class="col-lg-12 col-md-12 col-sm-12 form-group" data-for="maxstudent">
                            <input type="text" name="maxstudent" placeholder="Nombre maximum d'étudiant" data-form-field="maxstudent" onfocus="(this.type='number')" onblur="(this.type='number')" class="form-control" value="" id="maxstudent-form9-j">
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