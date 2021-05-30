
<%--
    Document   : listCourse
    Created on : 25 avr. 2021, 15:54:36
    Author     : Neil Farmer/Ruiqing Zhu
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="navBar.jsp" %>
<!DOCTYPE html>
<html  >
<head>
    <title>Liste des cours</title>
</head>
<body>
<section class="features9 cid-svA6uBZdYn" id="features10-9">
    <div class="container">
        <div class="mbr-section-head">
            <h3 class="mbr-section-title mbr-fonts-style mb-0 display-2">
                <strong>Catalogue des cours</strong></h3>
        </div>
        <div class="row mt-4">
            ${list}
        </div>
        <div class="container">
            ${pagination}
        </div>
    </div>
</section>
<section style="background-color: #fff; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Helvetica Neue', Arial, sans-serif; color:#aaa; font-size:12px; padding: 0; align-items: center; display: flex;"></section><script src="/static/jquery/jquery.min.js"></script>  <script src="/static/popper/popper.min.js"></script>  <script src="/static/tether/tether.min.js"></script>  <script src="/static/bootstrap/js/bootstrap.min.js"></script>  <script src="/static/smoothscroll/smooth-scroll.js"></script>  <script src="/static/dropdown/js/nav-dropdown.js"></script>  <script src="/static/dropdown/js/navbar-dropdown.js"></script>  <script src="/static/touchswipe/jquery.touch-swipe.min.js"></script>  <script src="/static/theme/js/script.js"></script>
</body>
</html>
