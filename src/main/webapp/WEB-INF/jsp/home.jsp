<%--
    Document   : home
    Created on : 28 avr. 2021, 17:28:48
    Author     : Neil FARMER/Ruiqing Zhu
--%>

<%@page import="java.util.Arrays"%>
<%@ page import="org.hibernate.Session" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="navBar.jsp" %>
<!DOCTYPE html>
<html  >
<body>



<section class="header18 cid-svkdLqJpnM mbr-fullscreen" id="header18-2">
    <div class="align-center container">
        <div class="row justify-content-center">
            <div class="col-12 col-lg-10">
                <h1 class="mbr-section-title mbr-fonts-style mbr-white mb-3 display-1"><strong>École</strong></h1>

                <p class="mbr-text mbr-fonts-style mbr-white display-7">
                    Projet de catalogue de matière d'une école, réalisé dans le cadre du projet de LO54</p>
                <div class="mbr-section-btn mt-3"><a class="btn btn-primary display-4" href="/Course/listCourse">Voir le catalogue</a></div>
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
                        <img src="/static/images/mbr-695x463.jpg" alt="">
                    </div>
                    <div class="item-content">
                        <h5 class="item-title mbr-fonts-style display-7"><strong>Catalogue des cours</strong></h5>

                        <p class="mbr-text mbr-fonts-style mt-3 display-7">Trouver le cours que vous souhaitez depuis le catalogue des cours.</p>
                    </div>
                    <div class="mbr-section-btn item-footer mt-2"><a href="/Course/listCourse" class="btn btn-primary item-btn display-7" target="_blank">Voir &gt;</a></div>
                </div>
            </div>
            <div class="item features-image сol-12 col-md-6 col-lg-4">
                <div class="item-wrapper">
                    <div class="item-img">
                        <img src="/static/images/mbr-1-695x463.jpg" alt="">
                    </div>
                    <div class="item-content">
                        <h5 class="item-title mbr-fonts-style display-7"><strong>Rechercher un cours</strong></h5>

                        <p class="mbr-text mbr-fonts-style mt-3 display-7">Trouver un cours grâce à son intitulé.</p>
                    </div>
                    <div class="mbr-section-btn item-footer mt-2"><a href="/Course/searchPage" class="btn btn-primary item-btn display-7" target="_blank">Voir &gt;</a></div>
                </div>
            </div>
            <div class="item features-image сol-12 col-md-6 col-lg-4">
                <div class="item-wrapper">
                    <div class="item-img">
                        <img src="/static/images/mbr-2-695x463.jpg" alt="">
                    </div>
                    <div class="item-content">
                        <h5 class="item-title mbr-fonts-style display-7"><strong>Rechercher une session</strong></h5>

                        <p class="mbr-text mbr-fonts-style mt-3 display-7">Trouver une session proche de chez-vous et quand vous le souhaitez.<br></p>
                    </div>
                    <div class="mbr-section-btn item-footer mt-2"><a href="/CourseSession/searchPage" class="btn btn-primary item-btn display-7" target="_blank">Voir &gt;</a></div>
                </div>
            </div>
        </div>
    </div>
</section><section style="background-color: #fff; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Helvetica Neue', Arial, sans-serif; color:#aaa; font-size:12px; padding: 0; align-items: center; display: flex;"></section><script src="/static/jquery/jquery.min.js"></script>  <script src="/static/popper/popper.min.js"></script>  <script src="/static/tether/tether.min.js"></script>  <script src="/static/bootstrap/js/bootstrap.min.js"></script>  <script src="/static/smoothscroll/smooth-scroll.js"></script>  <script src="/static/dropdown/js/nav-dropdown.js"></script>  <script src="/static/dropdown/js/navbar-dropdown.js"></script>  <script src="/static/touchswipe/jquery.touch-swipe.min.js"></script>  <script src="/static/theme/js/script.js"></script>
</body>
</html>
