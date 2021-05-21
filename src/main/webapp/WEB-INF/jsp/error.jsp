<%--
    Document   : error
    Created on : 7 mai 2021, 11:13:08
    Author     : Neil FARMER/Zhu RUIQING
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="navBar.jsp" %>
<!DOCTYPE html>
<html  >
<head>
    <link rel="stylesheet" href="/static/transaction/tabs.css">
    <title>Erreur</title>
</head>
<body>


<section class="tabs content18 cid-svAgZpTjSR" id="tabs1-p">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-12 col-md-8">
                <h3 class="mbr-section-title mb-0 mbr-fonts-style display-2">
                    <strong>Une erreur est survenu</strong></h3>
            </div>
        </div>
        <div class="row justify-content-center mt-4">
            <div class="tabset">
                <!-- Tab 1 -->
                <input type="radio" name="tabset" id="tab1" aria-controls="erreur" checked>
                <label for="tab1">Erreur</label>

                <!-- Tab 2 -->
                <input type="radio" name="tabset" id="tab2" aria-controls="solution">
                <label for="tab2">Solution</label>

                <div class="tab-panels">
                    <section id="erreur" class="tab-panel">
                        <h2>Erreur</h2>
                        <%
                            String err = request.getParameter("error");
                            if(err != null && err != ""){
                                out.println("<p>" + err + "</p>");
                            }else{
                                out.println("<p>Erreur inconnue</p>");
                            }
                        %>
                    </section>
                    <section id="solution" class="tab-panel">
                        <h2>Solution</h2>
                        <%
                            String sol = request.getParameter("solution");
                            if(sol != null && sol != ""){
                                out.println("<p>" + sol + "</p>");
                            }else{
                                out.println("<p>Aucune solution indique pour le moment</p>");
                            }
                        %>
                    </section>
                </div>
            </div>
        </div>
    </div>
</section><section style="background-color: #fff; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', 'Roboto', 'Helvetica Neue', Arial, sans-serif; color:#aaa; font-size:12px; padding: 0; align-items: center; display: flex;"></section><script src="/static/jquery/jquery.min.js"></script>  <script src="/static/popper/popper.min.js"></script>  <script src="/static/tether/tether.min.js"></script>  <script src="/static/bootstrap/js/bootstrap.min.js"></script>  <script src="/static/smoothscroll/smooth-scroll.js"></script>  <script src="/static/dropdown/js/nav-dropdown.js"></script>  <script src="/static/dropdown/js/navbar-dropdown.js"></script>  <script src="/static/touchswipe/jquery.touch-swipe.min.js"></script>  <script src="/static/mbr-tabs/mbr-tabs.js"></script>  <script src="/static/theme/js/script.js"></script>

</body>
</html>
