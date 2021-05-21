package fr.utbm.school.core.controller;

import fr.utbm.school.core.Dao.EntityClientDao;
import fr.utbm.school.core.entity.Client;
import fr.utbm.school.core.entity.Course;
import fr.utbm.school.core.entity.CourseSession;
import fr.utbm.school.core.exceptions.ClientException;
import fr.utbm.school.core.service.ClientService;
import fr.utbm.school.core.service.CourseService;
import fr.utbm.school.core.service.CourseSessionService;
import org.apache.log4j.Logger;
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

/**
 * @author : Neil Farmer/Ruiqing Zhu
 */
@Controller
@RequestMapping("/Client")
public class ClientController {

    // Logger of the controller
    private static final Logger logger = Logger.getLogger(ClientController.class.getName());

    @Autowired
    private ClientService clientService;

    @Autowired
    private CourseSessionService courseSessionService;

    @Autowired
    private CourseService courseService;

    /**
     * Method to load the page to register to a course
     * @param courseSession to register
     * @param model
     * @return url of the page
     */
    @GetMapping("/addPage")
    public String registerToCoursePage(@RequestParam String courseSession, Model model){
        logger.trace("Controller to load the register page have been called");

        // Html code of an hidden field
        String courseSessionValueForm = "<input hidden=\"hidden\" type=\"text\" name=\"courseSessionId\" data-form-field=\"courseSessionId\" class=\"form-control\" value=\"" + courseSession + "\" id=\"courseSessionId\">";

        // set the value to the model
        model.addAttribute("courseSessionValue", courseSessionValueForm);
        return "registerCourseSession";
    }

    /**
     * Method to save new registration
     * @param request
     * @param response
     * @return the home page if the client have been saved else the error page
     * @throws SQLException
     * @throws IOException
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String AddClientRegistered(WebRequest request, HttpServletResponse response) throws SQLException, IOException {
        logger.trace("Controller to save a client have been called");

        // Get all parameter of the form
        Map<String,String[]> form = request.getParameterMap();
        String courseSessionId = form.get("courseSessionId")[0];
        String lastname = form.get("lastname")[0];
        String firstname = form.get("firstname")[0];
        String address = form.get("address")[0];
        String phone = form.get("phone")[0].replaceAll("\\s", "");
        String email = form.get("email")[0];

        // Parse the course session id
        Long courseSessionIdInt = null;
        if(courseSessionId != null && !"".equals(courseSessionId)){
            courseSessionIdInt = Long.parseLong(courseSessionId);
        }else{
            logger.error("The course is unknown");

            response.sendRedirect("/lo54/error");
            return "error";
        }

        // Get the course session by the given id
        CourseSession courseSession = courseSessionService.searchCourseSessionById(courseSessionIdInt);

        // Check if all values are not null
        if(courseSession != null && lastname != null && firstname != null &&
                phone != null && email != null && address != null){

            Client cli = new Client();
            cli.setAddress(address);
            cli.setCourseSession(courseSession);
            cli.setEmail(email);
            cli.setFirstName(firstname);
            cli.setLastName(lastname);
            cli.setPhone(phone);

            try {
                clientService.saveClient(cli);
            } catch (ClientException cex) {
                // Client exception occurred

                logger.error("An unknown client exception occurred");

                response.sendRedirect("/lo54/error?error=Une erreur sql est " +
                        "survenue&solution=Redemarer le server, " +
                        "si cela ne change rien, verifier les instructions sql" +
                        " ainsi que les inforamtion saisie");
                return "error";
            } catch (Exception ex){
                // Client exception occurred

                logger.error("An unknown exception occurred");

                response.sendRedirect("/lo54/error");
                return "error";
            }

            response.sendRedirect("/Home/Homepage?success=Votre inscription a ete enregistre");
            return "home";

        }else{
            logger.error("A parameter have null value but it should not");

            response.sendRedirect("/lo54/error?error=Une des valeurs entree" +
                    "est null&solution=Verifier vos informations puis retenter");
            return "error";
        }
    }

    /**
     * Controller to load the page myAccount
     * @param email email of the client to find
     * @param model
     * @return The page
     */
    @GetMapping("/userAccount")
    public String myAccoutPage(@RequestParam String email, Model model){
        logger.trace("Controller to get a client have been called");

        // Get all the course session
        ArrayList<Client> selectedClient = clientService.searchClientByEmail(email);

        // If there's no course then print it to the html page
        if(selectedClient.isEmpty()){
            logger.info("No course have been found for the client with the email :" + email);
            return "<p><b>No session found</b></p>";
        }

        // Create the string to display course sessions
        String courseSessionListString = "";
        for(Client client:selectedClient){

            // get the course session where the client is registered
            // 1 Client = 1 CourseSession
            CourseSession courseSession = client.getCourseSession();

            // Get the year
            int startYear = courseSession.getStartDate().getYear() + 1900;
            int endYear = courseSession.getEndDate().getYear() + 1900;

            // Get the month
            int startMonth = courseSession.getStartDate().getMonth() + 1;
            int endMonth = courseSession.getEndDate().getMonth() + 1;

            // Get the course of the course session
            Course course = courseSession.getCourse();

            // Build the string to display the information about the course session
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

        // End the string
        courseSessionListString += "</div>";

        model.addAttribute("registeredCourse", courseSessionListString);
        return "userAccount";
    }
}
