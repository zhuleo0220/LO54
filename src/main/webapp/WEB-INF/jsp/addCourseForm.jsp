<%--
    Document   : addCourseForm
    Created on : 7 mai 2021, 20:48:24
    Author     : neil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="navBar.jsp" %>
<!DOCTYPE html>
<html  >
<body>


<section class="form7 cid-svw9c0kpyk" id="form7-7">
    <div class="container">
        <div class="mbr-section-head">
            <h3 class="mbr-section-title mbr-fonts-style align-center mb-0 display-2">
                <strong>Ajouter un nouveau cours</strong></h3>
        </div>
        <div class="row justify-content-center mt-4">
            <div class="col-lg-8 mx-auto mbr-form">
                <form action="/client/addCourseServlet" method="POST" data-rcpha_sitekey="" data-rcpha_secretkey="" class="mbr-form form-with-styler mx-auto">
                    <p class="mbr-text mbr-fonts-style align-center mb-4 display-7">
                        Entrer les informations ci-dessous pour ajouter le nouveau cours</p>
                    <div class="row">

                        <div hidden="hidden" data-form-alert-danger="" class="alert alert-danger col-12">Oops...! some
                            problem!</div>
                    </div>
                    <div class="dragArea row">
                        <div class="col-lg-12 col-md-12 col-sm-12 form-group" data-for="code">
                            <input type="text" name="code" placeholder="Identifiant du cours" data-form-field="code" class="form-control" value="" id="code-form7-7" pattern="[A-Z]{2}[0-9]{2}" title="Le nom doit comporter deux lettres en majuscule suivi de deux chiffre" required>
                        </div>
                        <div class="col-lg-12 col-md-12 col-sm-12 form-group" data-for="title">
                            <input type="text" name="title" placeholder="Nom du cours" data-form-field="title" class="form-control" value="" id="title-form7-7" required>
                        </div>
                        <div class="col-auto mbr-section-btn align-center">
                            <button type="submit" class="btn btn-primary display-4">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section><section style="background-color: #fff; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Helvetica Neue', Arial, sans-serif; color:#aaa; font-size:12px; padding: 0; align-items: center; display: flex;"></section><script src="ressources/assets/web/ressources/assets/jquery/jquery.min.js"></script>  <script src="ressources/assets/popper/popper.min.js"></script>  <script src="ressources/assets/tether/tether.min.js"></script>  <script src="ressources/assets/bootstrap/js/bootstrap.min.js"></script>  <script src="ressources/assets/smoothscroll/smooth-scroll.js"></script>  <script src="ressources/assets/dropdown/js/nav-dropdown.js"></script>  <script src="ressources/assets/dropdown/js/navbar-dropdown.js"></script>  <script src="ressources/assets/touchswipe/jquery.touch-swipe.min.js"></script>  <script src="ressources/assets/formstyler/jquery.formstyler.js"></script>  <script src="ressources/assets/formstyler/jquery.formstyler.min.js"></script>  <script src="ressources/assets/datepicker/jquery.datetimepicker.full.js"></script>  <script src="ressources/assets/theme/js/script.js"></script>
</body>
</html>
