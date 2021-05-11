<%--
    Document   : registerCourseSession
    Created on : 7 mai 2021, 09:19:51
    Author     : Neil FARMER/Zhu RUIQING
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="navBar.jsp" %>
<!DOCTYPE html>
<html  >

<body>


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
</section><section style="background-color: #fff; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Helvetica Neue', Arial, sans-serif; color:#aaa; font-size:12px; padding: 0; align-items: center; display: flex;"></section><script src="/static/jquery/jquery.min.js"></script>  <script src="/static/popper/popper.min.js"></script>  <script src="/static/tether/tether.min.js"></script>  <script src="/static/bootstrap/js/bootstrap.min.js"></script>  <script src="/static/smoothscroll/smooth-scroll.js"></script>  <script src="/static/dropdown/js/nav-dropdown.js"></script>  <script src="/static/dropdown/js/navbar-dropdown.js"></script>  <script src="/static/touchswipe/jquery.touch-swipe.min.js"></script>  <script src="/static/formstyler/jquery.formstyler.js"></script>  <script src="/static/formstyler/jquery.formstyler.min.js"></script>  <script src="/static/datepicker/jquery.datetimepicker.full.js"></script>  <script src="/static/theme/js/script.js"></script>  <script src="/static/formoid/formoid.min.js"></script>
</body>
</html>
