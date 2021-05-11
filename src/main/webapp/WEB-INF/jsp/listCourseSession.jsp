<%--
    Document   : listCourseSession
    Created on : 26 avr. 2021, 09:01:51
    Author     : Neil FARMER/Zhu RUIQING
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="navBar.jsp" %>
<!DOCTYPE html>
<html  >
<body>


<section class="features9 cid-svA6uBZdYn" id="features10-9">
    <div class="container">
        <div class="mbr-section-head">
            <h3 class="mbr-section-title mbr-fonts-style mb-0 display-2">
                <strong>Liste des session répondant aux critère</strong></h3>
        </div>
        <% out.println(fr.utbm.school.web.controller.CourseSessionController.getListCourseSession(request.getParameter("locationId"), request.getParameter("date"))); %>
    </div>
</section><section style="background-color: #fff; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Helvetica Neue', Arial, sans-serif; color:#aaa; font-size:12px; padding: 0; align-items: center; display: flex;"></section><script src="ressources/assets/web/ressources/assets/jquery/jquery.min.js"></script>  <script src="ressources/assets/popper/popper.min.js"></script>  <script src="ressources/assets/tether/tether.min.js"></script>  <script src="ressources/assets/bootstrap/js/bootstrap.min.js"></script>  <script src="ressources/assets/smoothscroll/smooth-scroll.js"></script>  <script src="ressources/assets/dropdown/js/nav-dropdown.js"></script>  <script src="ressources/assets/dropdown/js/navbar-dropdown.js"></script>  <script src="ressources/assets/touchswipe/jquery.touch-swipe.min.js"></script>  <script src="ressources/assets/theme/js/script.js"></script>
</body>
</html>
