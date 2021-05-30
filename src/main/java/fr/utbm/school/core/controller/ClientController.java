package fr.utbm.school.core.controller;

import com.codahale.metrics.Timer;
import fr.utbm.school.core.entity.Client;
import fr.utbm.school.core.entity.Course;
import fr.utbm.school.core.entity.CourseSession;
import fr.utbm.school.core.exceptions.ClientException;
import fr.utbm.school.core.service.ClientService;
import fr.utbm.school.core.service.CourseService;
import fr.utbm.school.core.service.CourseSessionService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static fr.utbm.school.core.functions.MoreFunction.nvl;

/**
 * @author : Neil Farmer/Ruiqing Zhu
 */
@Log4j
@Controller
@RequestMapping("/Client")
public class ClientController {

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
    public String registerToCoursePage(@RequestParam String courseSession, @CookieValue(value="emailUser", required=false) String email, Model model){
        log.trace("Controller to load the register page have been called");

        // Metrics
        Timer timer = new Timer();
        // Time the controller
        Timer.Context contextController = timer.time();

        // Html code of an hidden field
        String courseSessionValueForm = "<input hidden=\"hidden\" type=\"text\" name=\"courseSessionId\" data-form-field=\"courseSessionId\" class=\"form-control\" value=\"" + courseSession + "\" id=\"courseSessionId\">";
        // set the value to the model
        model.addAttribute("courseSessionValue", courseSessionValueForm);
        // set the email of user value to the model
        model.addAttribute("emailUser", nvl(email, "\"\""));

        // Time the transaction
        Timer.Context contextTransaction = timer.time();

        // Get the client with this email
        ArrayList<Client> client = this.clientService.searchClientByEmail(nvl(email, ""));

        // End the transaction's timer
        long timeTransaction = contextTransaction.stop();

        // Will fill the first name field
        Set<String> firstName = client.stream().map(cli->cli.getFirstName()).collect(Collectors.toSet());
        if(firstName.size() == 1){
            model.addAttribute("firstName", "\"" + nvl(firstName.iterator().next() + "\"", "\"\""));
        }else{
            model.addAttribute("firstName", "\"\"");
        }

        // Will fill the last name field
        Set<String> lastName = client.stream().map(cli->cli.getLastName()).collect(Collectors.toSet());
        if(lastName.size() == 1){
            model.addAttribute("lastName", "\"" + nvl(lastName.iterator().next() + "\"", "\"\""));
        }else{
            model.addAttribute("lastName", "\"\"");
        }

        // Will fill the phone field
        Set<String> phone = client.stream().map(cli->cli.getPhone()).collect(Collectors.toSet());
        if(phone.size() == 1){
            model.addAttribute("phone", "\"" + nvl(phone.iterator().next() + "\"", "\"\""));
        }else{
            model.addAttribute("phone", "\"\"");
        }

        // Will fill the address field
        Set<String> address = client.stream().map(cli->cli.getAddress()).collect(Collectors.toSet());
        if(address.size() == 1){
            model.addAttribute("address", "\"" + nvl(address.iterator().next() + "\"", "\"\""));
        }else{
            model.addAttribute("address", "\"\"");
        }

        // Stop the timer of the controller
        long timeController = contextController.stop();

        // log the metrics
        log.trace("Metric : time spent in registerToCoursePage(nano-seconds) : " + timeController + " | time spent in transaction(nano-seconds) : "  + timeTransaction);

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
    @ExceptionHandler
    @PostMapping(value = "/add")
    @ResponseBody
    public String addClientRegistered(WebRequest request, HttpServletResponse response) throws SQLException, IOException {
        log.trace("Controller to save a client have been called");

        // Metrics
        Timer timer = new Timer();
        // Time the controller
        Timer.Context contextController = timer.time();

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
            log.error("The course is unknown");

            // end of the timer of teh controller
            long timeController = contextController.stop();
            // print it into the log
            log.trace("Metric : time spent in addClientRegistered(nano-seconds) : " + timeController);

            // return statements
            response.sendRedirect("/lo54/error");
            return "error";
        }

        // Time the transaction
        Timer.Context contextTransaction = timer.time();

        // Get the course session by the given id
        CourseSession courseSession = courseSessionService.searchCourseSessionById(courseSessionIdInt);

        // end the timer
        long timeTransaction = contextTransaction.stop();

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

                log.error("An unknown client exception occurred");

                // end of the timer of teh controller
                long timeController = contextController.stop();
                // print it into the log
                log.trace("Metric : time spent in addClientRegistered(nano-seconds) : " + timeController + " | time spent in searchCourseSessionById(nano-seconds) : " + timeTransaction);

                // return statement
                response.sendRedirect("/lo54/error?error=Une erreur sql est " +
                        "survenue&solution=Redemarer le server, " +
                        "si cela ne change rien, verifier les instructions sql" +
                        " ainsi que les inforamtion saisie");
                return "error";
            } catch (Exception ex){
                // Client exception occurred
                log.error("An unknown exception occurred");

                // end of the timer of teh controller
                long timeController = contextController.stop();
                // print it into the log
                log.trace("Metric : time spent in addClientRegistered(nano-seconds) : " + timeController + " | time spent in searchCourseSessionById(nano-seconds) : " + timeTransaction);

                // return statement
                response.sendRedirect("/lo54/error");
                return "error";
            }

            // end of the timer of teh controller
            long timeController = contextController.stop();
            // print it into the log
            log.trace("Metric : time spent in addClientRegistered(nano-seconds) : " + timeController + " | time spent in searchCourseSessionById(nano-seconds) : " + timeTransaction);

            // return statement
            response.sendRedirect("/Home/Homepage?success=Votre inscription a ete enregistre");
            return "home";

        }else{
            log.error("A parameter have null value but it should not");

            // end of the timer of teh controller
            long timeController = contextController.stop();
            // print it into the log
            log.trace("Metric : time spent in addClientRegistered(nano-seconds) : " + timeController + " | time spent in searchCourseSessionById(nano-seconds) : " + timeTransaction);

            // return statement
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
        log.trace("Controller to get a client have been called");

        // Metrics
        Timer timer = new Timer();
        // Time the controller
        Timer.Context contextController = timer.time();

        // Time the transaction
        Timer.Context contextTransaction = timer.time();

        // Get all the course session
        ArrayList<Client> selectedClient = clientService.searchClientByEmail(email);

        // end the timer
        long timeTransaction = contextTransaction.stop();

        // If there's no course then print it to the html page
        if(selectedClient.isEmpty()){
            log.info("No course have been found for the client with the email :" + email);

            // end of the timer of teh controller
            long timeController = contextController.stop();
            // print it into the log
            log.trace("Metric : time spent in myAccoutPage(nano-seconds) : " + timeController + " | time spent in searchClientByEmail(nano-seconds) : " + timeTransaction);

            // return statement
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

        // end of the timer of teh controller
        long timeController = contextController.stop();
        // print it into the log
        log.trace("Metric : time spent in myAccoutPage(nano-seconds) : " + timeController + " | time spent in searchClientByEmail(nano-seconds) : " + timeTransaction);

        // return statement
        model.addAttribute("registeredCourse", courseSessionListString);
        return "userAccount";
    }
}
