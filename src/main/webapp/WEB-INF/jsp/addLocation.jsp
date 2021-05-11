<%--
    Document   : addLocation
    Created on : 7 mai 2021, 20:55:09
    Author     : neil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="navBar.jsp" %>
<!DOCTYPE html>
<html  >
<body>


<section class="form7 cid-svAgcG8APk" id="form7-n">
    <div class="container">
        <div class="mbr-section-head">
            <h3 class="mbr-section-title mbr-fonts-style align-center mb-0 display-2">
                <strong>Ajouter une ville</strong></h3>
        </div>
        <div class="row justify-content-center mt-4">
            <div class="col-lg-8 mx-auto mbr-form" data-form-type="formoid">
                <form action="/Location/add" method="POST" class="mbr-form form-with-styler mx-auto" data-form-title="Form Name"><input type="hidden" name="email" data-form-email="true" value="6HgF1tgkzsSC7ks/0UM2f9IwQg133pzQLRrTbsJQNAIZAO+YNF8IL3SJXOcRl9iVfCPfTWNvwikVz6m+M+hMIZ46I706Bekjz6ce79+30J85ZL21thaW0GCLMnWWRSPK">
                    <p class="mbr-text mbr-fonts-style align-center mb-4 display-7">
                        Entrer les informations ci-dessous pour ajouter le nouveau cours</p>
                    <div class="row">
                        <div hidden="hidden" data-form-alert="" class="alert alert-success col-12">Thanks for filling
                            out the form!</div>
                        <div hidden="hidden" data-form-alert-danger="" class="alert alert-danger col-12">Oops...! some
                            problem!</div>
                    </div>
                    <div class="dragArea row">
                        <div class="col-lg-12 col-md-12 col-sm-12 form-group" data-for="city">
                            <input type="text" name="city" placeholder="Nom de la ville" data-form-field="city" class="form-control" value="" id="city-form7-n">
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
