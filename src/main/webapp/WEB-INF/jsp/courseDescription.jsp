<%--
    Document   : courseDescription
    Created on : 25 avr. 2021, 16:55:58
    Author     : Neil FARMER/Zhu RUIQING
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="navBar.jsp" %>
<!DOCTYPE html>
<html  >
<body>


<section class="content7 cid-svAcH4beFZ" id="content7-d">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-md-10">
                <blockquote>
                    <h5 class="mbr-section-title mbr-fonts-style mb-2 display-7"><strong>${courseCode}</strong></h5>
                    <p class="mbr-text mbr-fonts-style display-4">${courseTitle}</p>
                </blockquote>
            </div>
        </div>
    </div>
</section>

<section class="features9 cid-svAcKF7qHy" id="features10-e">
    <div class="container">
        <div class="mbr-section-head">
            <h3 class="mbr-section-title mbr-fonts-style mb-0 display-2">
                <strong>Liste des session</strong></h3>
        </div>
        ${courseSessionFound}
    </div>
</section>
<section class="content11 cid-svAdpaxaD7" id="content11-f">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-md-12 col-lg-10">
                <div class="mbr-section-btn align-center">
                    ${buttonAddCourseSession}
                </div>
            </div>
        </div>
    </div>
</section><section style="background-color: #fff; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Helvetica Neue', Arial, sans-serif; color:#aaa; font-size:12px; padding: 0; align-items: center; display: flex;"></section><script src="/static/jquery/jquery.min.js"></script>  <script src="/static/popper/popper.min.js"></script>  <script src="/static/tether/tether.min.js"></script>  <script src="/static/bootstrap/js/bootstrap.min.js"></script>  <script src="/static/smoothscroll/smooth-scroll.js"></script>  <script src="/static/dropdown/js/nav-dropdown.js"></script>  <script src="/static/dropdown/js/navbar-dropdown.js"></script>  <script src="/static/touchswipe/jquery.touch-swipe.min.js"></script>  <script src="/static/theme/js/script.js"></script>
</body>
</html>
