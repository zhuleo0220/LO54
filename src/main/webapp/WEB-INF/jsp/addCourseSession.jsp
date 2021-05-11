<%--
    Document   : addCourseSession
    Created on : 30 avr. 2021, 19:13:25
    Author     : Neil FARMER/Zhu RUIQING
--%>

<%@page import="fr.utbm.school.core.entity.Location"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="navBar.jsp" %>
<!DOCTYPE html>
<html  >
<body>

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
</section><section style="background-color: #fff; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Helvetica Neue', Arial, sans-serif; color:#aaa; font-size:12px; padding: 0; align-items: center; display: flex;"></section><script src="/static/jquery/jquery.min.js"></script>  <script src="/static/popper/popper.min.js"></script>  <script src="/static/tether/tether.min.js"></script>  <script src="/static/bootstrap/js/bootstrap.min.js"></script>  <script src="/static/smoothscroll/smooth-scroll.js"></script>  <script src="/static/dropdown/js/nav-dropdown.js"></script>  <script src="/static/dropdown/js/navbar-dropdown.js"></script>  <script src="/static/touchswipe/jquery.touch-swipe.min.js"></script>  <script src="/static/formstyler/jquery.formstyler.js"></script>  <script src="/static/formstyler/jquery.formstyler.min.js"></script>  <script src="/static/datepicker/jquery.datetimepicker.full.js"></script>  <script src="/static/theme/js/script.js"></script>  <script src="/static/formoid/formoid.min.js"></script>
</body>
</html>
