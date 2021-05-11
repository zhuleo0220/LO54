<%--
    Document   : searchCourseSession
    Created on : 26 avr. 2021, 08:35:36
    Author     : Neil FARMER/Zhu RUIQING
--%>
<%@page import="fr.utbm.school.core.entity.Location"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="navBar.jsp" %>
<!DOCTYPE html>
<html  >

<body>


<section class="form9 cid-svAfdW8m7f" id="form9-j">
    <div class="container">
        <div class="mbr-section-head">
            <h3 class="mbr-section-title mbr-fonts-style align-center mb-0 display-2">
                <strong>Rechercher un cours</strong></h3>
        </div>
        <div class="row justify-content-center mt-4">
            <div class="col-lg-8 mx-auto mbr-form">
                <form action="/client/searchCourseSesssionServlet" method="POST" class="mbr-form form-with-styler mx-auto">
                    <div class="row">
                        <div hidden="hidden" data-form-alert-danger="" class="alert alert-danger col-12">Oops...! some problem!</div>
                    </div>
                    <div class="dragArea row">
                        <div class="col-lg-12">
                            <p class="mbr-text mbr-fonts-style align-center mb-4 display-7"> Entrer le nom du cours que vous souhaitez</p>
                        </div>
                        <div class="col-lg-4 col-md-12 col-sm-12 form-group" data-for="locationId">
                            <select id="locationId" name="locationId" class="form-control">
                                <option value="">--Localisation de la session--</option>
                                <%
                                for(Location lo:fr.utbm.school.core.service.LocationService.getListLocation()){
                                    out.println("<option value=\"" + lo.getId() + "\">" + lo.getCity() + "</option>");
                                }
                                %>
                            </select>
                        </div>
                        <div data-for="date" class="col-lg-4 col-md-12 col-sm-12 form-group">
                            <input type="text" name="date" placeholder="Date début du cours" data-form-field="date" onfocus="(this.type='date')" onblur="(this.type='text')" class="form-control" value="" id="email-form9-j">
                        </div>
                        <div class="col-lg-4 col-md-12 col-sm-12 mbr-section-btn align-center"><button type="submit" class="btn btn-primary display-4">Submit</button></div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section><section style="background-color: #fff; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Helvetica Neue', Arial, sans-serif; color:#aaa; font-size:12px; padding: 0; align-items: center; display: flex;"></section><script src="/static/jquery/jquery.min.js"></script>  <script src="/static/popper/popper.min.js"></script>  <script src="/static/tether/tether.min.js"></script>  <script src="/static/bootstrap/js/bootstrap.min.js"></script>  <script src="/static/smoothscroll/smooth-scroll.js"></script>  <script src="/static/dropdown/js/nav-dropdown.js"></script>  <script src="/static/dropdown/js/navbar-dropdown.js"></script>  <script src="/static/touchswipe/jquery.touch-swipe.min.js"></script>  <script src="/static/formstyler/jquery.formstyler.js"></script>  <script src="/static/formstyler/jquery.formstyler.min.js"></script>  <script src="/static/datepicker/jquery.datetimepicker.full.js"></script>  <script src="/static/theme/js/script.js"></script>
</body>
</html>
