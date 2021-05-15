package fr.utbm.school.core.controller;

import fr.utbm.school.core.entity.Client;
import fr.utbm.school.core.entity.Course;
import fr.utbm.school.core.entity.CourseSession;
import fr.utbm.school.core.exceptions.ClientException;
import fr.utbm.school.core.service.ClientService;
import fr.utbm.school.core.service.CourseService;
import fr.utbm.school.core.service.CourseSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

@Controller
@RequestMapping("/Client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private CourseSessionService courseSessionService;

    @Autowired
    private CourseService courseService;

    @GetMapping("/addPage")
    public String registerToCoursePage(@RequestParam String courseSession, Model model){

        String courseSessionValueForm = "<input hidden=\"hidden\" type=\"text\" name=\"courseSessionId\" data-form-field=\"courseSessionId\" class=\"form-control\" value=\"" + courseSession + "\" id=\"courseSessionId\">";

        model.addAttribute("courseSessionValue", courseSessionValueForm);
        return "registerCourseSession";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String AddClientRegistered(WebRequest request, HttpServletResponse response) throws SQLException, IOException {
        Map<String,String[]> form = request.getParameterMap();
        String courseSessionId = form.get("courseSessionId")[0];
        String lastname = form.get("lastname")[0];
        String firstname = form.get("firstname")[0];
        String address = form.get("address")[0];
        String phone = form.get("phone")[0].replaceAll("\\s", "");
        String email = form.get("email")[0];

        Long courseSessionIdInt = null;
        if(courseSessionId != null && !"".equals(courseSessionId)){
            courseSessionIdInt = Long.parseLong(courseSessionId);
        }else{
            response.sendRedirect("/error");
            return "error";
        }

        CourseSession courseSession = courseSessionService.searchCourseSessionById(courseSessionIdInt);

        if(courseSession != null && lastname != null && firstname != null &&
                phone != null && email != null && address != null){

            Client cli = new Client();
            cli.setAddress(phone);
            cli.setCourseSession(courseSession);
            cli.setEmail(email);
            cli.setFirstName(firstname);
            cli.setLastName(lastname);
            cli.setPhone(phone);

            try {
                clientService.saveClient(cli);
            } catch (ClientException ex) {
                response.sendRedirect("/error?error=Une erreur sql est " +
                        "survenue&solution=Redemarer le server, " +
                        "si cela ne change rien, verifier les instructions sql" +
                        " ainsi que les inforamtion saisie");
                return "error";
            }

            response.sendRedirect("/Home/Homepage?success=Votre inscription a ete enregistre");

        }else{
            response.sendRedirect("/error?error=Une des valeurs entree" +
                    "est null&solution=Verifier vos informations puis retenter");
        }
        return "home";
    }

    @GetMapping("/userAccount")
    public String myAccoutPage(@RequestParam String email, Model model){

        // Get all the course seesion
        ArrayList<Client> selectedClient = clientService.searchClientByEmail(email);

        // If there's no course then print it to the html page
        if(selectedClient.isEmpty()){
            return "<p><b>No session found</b></p>";
        }

        String courseSessionListString = "";
        for(Client client:selectedClient){

            CourseSession courseSession = client.getCourseSession();

            int startYear = courseSession.getStartDate().getYear() + 1900;
            int endYear = courseSession.getEndDate().getYear() + 1900;

            int startMonth = courseSession.getStartDate().getMonth() + 1;
            int endMonth = courseSession.getEndDate().getMonth() + 1;

            Course course = courseSession.getCourse();

            courseSessionListString += (
                    "<br>" +
                            "<table border=\"1\" frame=\"void\" rules=\"all\" style=\"background-color: #e6e6e6;width:75%;margin-left: auto;margin-right: auto;border-radius:10px;\">" +
                            "<tr>" +
                            "<td>" +
                            "<strong>" + courseSession.getLocation().getCity() + "</strong>" +
                            "</td>" +
                            "<th colspan = \"3\" style=\"text-align:center\">" + course.getCode() + " : " + course.getTitle() + "</th>" +
                            "</tr>" +
                            "<tr>" +
                            "</tr>" +
                            "<tr>" +
                            "<th rowspan=\"2\">Date</th>" +
                            "<td colspan=\"2\" style=\"text-align:left\">" +
                            "Date debut : " + String.format("%02d", courseSession.getStartDate().getDate()) + "/" + String.format("%02d", startMonth) + "/" + startYear + " a " + String.format("%02d", courseSession.getStartDate().getHours()) + "h" + String.format("%02d", courseSession.getStartDate().getMinutes()) +
                            "</td>" +
                            "</tr>" +
                            "<tr>" +
                            "<td colspan=\"2\" style=\"text-align:left\">" +
                            "Date Fin : " + String.format("%02d", courseSession.getEndDate().getDate()) + "/" + String.format("%02d", endMonth) + "/" + endYear + " a " + String.format("%02d", courseSession.getEndDate().getHours()) + "h" + String.format("%02d", courseSession.getEndDate().getMinutes()) +
                            "</td>" +
                            "</tr>" +
                            "</table>"
            );
        }

        courseSessionListString += "</div>";

        model.addAttribute("registeredCourse", courseSessionListString);
        return "userAccount";
    }
}
