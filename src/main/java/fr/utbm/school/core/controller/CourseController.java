package fr.utbm.school.core.controller;

import com.codahale.metrics.Timer;
import fr.utbm.school.core.entity.Course;
import fr.utbm.school.core.entity.CourseSession;
import fr.utbm.school.core.service.CourseService;
import fr.utbm.school.core.service.CourseSessionService;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static fr.utbm.school.core.functions.Levenshtein.closestKeyword;
import static fr.utbm.school.core.functions.MoreFunction.nvl;

/**
 * @author : Neil Farmer/Ruiqing Zhu
 */
@Log4j
@Controller
@RequestMapping("/Course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseSessionService courseSessionService;

    /**
     * Method to add a course
     * @param course to save
     * @return
     * @throws SQLException
     */
    @ExceptionHandler
    @PostMapping(value = "/add")
    @ResponseBody
    public String addCourse(@Valid Course course, HttpServletResponse response) throws SQLException, IOException {
        log.trace("Controller to add a course have been called");

        // Metrics
        Timer timer = new Timer();
        // Time the controller
        Timer.Context contextController = timer.time();

        // Save the course
        try {
            // save the course
            courseService.saveCourse(course);
        }catch (Exception ex){
            // end of the timer of teh controller
            long timeController = contextController.stop();
            // print it into the log
            log.trace("Metric : time spent in myAccoutPage(nano-seconds) : " + timeController);

            // return statement
            response.sendRedirect("/LO54/error");
            return "error";
        }

        // end of the timer of teh controller
        long timeController = contextController.stop();
        // print it into the log
        log.trace("Metric : time spent in myAccoutPage(nano-seconds) : " + timeController);

        // return statement
        response.sendRedirect("/Home/Homepage?success=Le cours a ete ajoute");
        return "home";

    }

    /**
     * Method to go to the page to add a course
     * @return
     */
    @GetMapping(value = "/addpage")
    public String coursePage() {
        log.trace("Controller to load the page to add a course have been called");

        return "addCourseForm";
    }

    /**
     * Method to get a list of course (with a keyword : optional)
     * @param keyword
     * @param model
     * @return
     */
    @GetMapping("/listCourse")
    public String listCourse(@RequestParam(required=false) String keyword,
                             @RequestParam(required=false) Integer page, Model model){
        log.trace("Controller to get a list of course have been called");

        // Metrics
        Timer timer = new Timer();
        // Time the controller
        Timer.Context contextController = timer.time();

        // initialize the page number
        if(page == null){
            page = 1;
        }

        // Time the transaction
        Timer.Context contextTransaction = timer.time();

        // Get all the course
        ArrayList<Course> selectedCourse = courseService.getCourseByKeyword(keyword, page);

        // end the timer
        long timeTransaction = contextTransaction.stop();

        // If there's no course then print it to the html page
        if(selectedCourse.isEmpty()) {
            log.info("No course found with the keyword");

            // If the keyword was not null
            // We will search for a better keyword
            if (keyword != null) {
                // Levenshtein algo
                String bestKeyword = closestKeyword(keyword, courseService.getCourseKeyword(),
                        1, (int) Math.floor(keyword.length() / 2));

                // if a keyword have been found
                if (bestKeyword != null) {
                    // Display the best keyword
                    String betterKeyword = "<p>Rechercher plutot : <a href=/Course/listCourse?keyword=" +
                            bestKeyword + ">" + bestKeyword + "</a></p>";

                    // end of the timer of teh controller
                    long timeController = contextController.stop();
                    // print it into the log
                    log.trace("Metric : time spent in listCourse(nano-seconds) : " + timeController + " | time spent in getCourseByKeyword(nano-seconds) : " + timeTransaction);

                    // return statement
                    model.addAttribute("list", betterKeyword);
                    return "listCourse";
                }
            }

            // end of the timer of teh controller
            long timeController = contextController.stop();
            // print it into the log
            log.trace("Metric : time spent in listCourse(nano-seconds) : " + timeController + " | time spent in getCourseByKeyword(nano-seconds) : " + timeTransaction);

            // return statement
            model.addAttribute("list", "<p>Aucun cours trouvé<p>");
            return "listCourse";

        }

        // The pagination number
        String pagin = "";
        pagin += "<div class=\"pagination\">";
        for(Integer nbPage = 1; nbPage <= this.courseService.getNbPageNeeded(keyword); nbPage++){
            if(nbPage == page){
                pagin += "<a href=\"#\" class=\"active\">" + nbPage + "</a>";
            }else{
                pagin += "<a href=\"/Course/listCourse?keyword=" + nvl(keyword, "") + "&page=" + nbPage + "\">" + nbPage + "</a>";
            }
        }
        // end the div section
        pagin += "</div>";

        // add the pagination to the model
        model.addAttribute("pagination", pagin);

        // String that will be returned to print the course found
        String courseListString = "";

        // For all course : Add their informations in the HTML
        for(Course course:selectedCourse){
            courseListString += (
                    "<div class=\"card col-12\">" +
                            "<div class=\"card-wrapper\">" +
                                "<div class=\"top-line row\">" +
                                    "<div class=\"col\">" +
                                        "<h4 class=\"card-title mbr-fonts-style display-5\"><strong>" + course.getCode() + "</strong></h4>" +
                                    "</div>" +
                                    "<div class=\"col-auto\">" +
                                        "<a href=/Course/courseDescription?course=" + course.getCode() + " class=\"price mbr-fonts-style display-5\">Description</a>" +
                                    "</div>" +
                            "</div>" +
                            "<div class=\"bottom-line\">" +
                                "<p class=\"mbr-text mbr-fonts-style display-7\">" + course.getTitle() + "</p>" +
                            "</div>" +
                            "</div>" +
                            "</div>");
        }

        // end of the timer of teh controller
        long timeController = contextController.stop();
        // print it into the log
        log.trace("Metric : time spent in listCourse(nano-seconds) : " + timeController + " | time spent in getCourseByKeyword(nano-seconds) : " + timeTransaction);

        // return statement
        // return the string with all informations
        model.addAttribute("list", courseListString);
        return "listCourse";
    }

    /**
     * Get the description ans all the course session linked to a given course
     * @param course
     * @param model
     * @return
     */
    @GetMapping("/courseDescription")
    public String listCourseSession(@RequestParam String course, Model model) {
        log.trace("Controller to get a description of a course have been called");

        // Metrics
        Timer timer = new Timer();
        // Time the controller
        Timer.Context contextController = timer.time();

        // Time the transaction
        Timer.Context contextTransaction = timer.time();

        // search the course by his id
        Course courseObject = courseService.searchCourseById(course);

        // end the timer
        long timeTransaction = contextTransaction.stop();

        // Put information about this course for the JSP
        model.addAttribute("courseCode", courseObject.getCode());
        model.addAttribute("courseTitle", courseObject.getTitle());

        // Create the button to add a new session to this course
        String buttonAdd = "<a class=\"btn btn-primary display-4\" href=\"/CourseSession/addPage?course=" + course + "\">Ajouter une nouvelle session</a>";
        model.addAttribute("buttonAddCourseSession", buttonAdd);

        // Get all the course seesion
        ArrayList<CourseSession> selectedCourseSession = courseSessionService.searchCourseSessionByCourseId(course);

        // If there's no course then print it to the html page
        if(selectedCourseSession.isEmpty()){
            // end of the timer of teh controller
            long timeController = contextController.stop();
            // print it into the log
            log.trace("Metric : time spent in listCourseSession(nano-seconds) : " + timeController + " | time spent in searchCourseById(nano-seconds) : " + timeTransaction);

            // return statement
            model.addAttribute("courseSessionFound", "<p><b>No session found</b></p>");
            return "courseDescription";
        }

        // String that will be returned to print the course found
        String courseSessionListString = "";

        // For all course : Add their informations in the HTML
        for(CourseSession courseSession:selectedCourseSession){
            // convert year
            int startYear = courseSession.getStartDate().getYear() + 1900;
            int endYear = courseSession.getEndDate().getYear() + 1900;

            // convert month
            int startMonth = courseSession.getStartDate().getMonth() + 1;
            int endMonth = courseSession.getEndDate().getMonth() + 1;

            // percent filled
            int percentFilled = courseSessionService.getPercentStudent(courseSession.getId());

            courseSessionListString += (
                    "<br>" +
                            "<table border=\"1\" frame=\"void\" rules=\"all\" style=\"background-color: #e6e6e6;width:75%;margin-left: auto;margin-right: auto;border-radius:10px;\">" +
                            "<tr>" +
                            "<td>" +
                            "<strong>" + courseSession.getLocation().getCity() + "</strong>" +
                            "</td>" +
                            "<td style=\"text-align:center\">" +
                            "<progress id=\"filled\" value=\"" + percentFilled + "\" max=\"100\"></progress>" +
                            "<label for=\"filled\">  " + percentFilled + "%</label>" +
                            "</td>" +
                            "<td style=\"text-align:center\">" +
                            "<a href=/Client/addPage?courseSession=" + courseSession.getId().toString() + ">S'inscrire</a>" +
                            "</td>" +
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

        // end of the timer of teh controller
        long timeController = contextController.stop();
        // print it into the log
        log.trace("Metric : time spent in listCourseSession(nano-seconds) : " + timeController + " | time spent in searchCourseById(nano-seconds) : " + timeTransaction);

        // return statement
        model.addAttribute("courseSessionFound", courseSessionListString);
        return "courseDescription";
    }

    /**
     * Method to search for a word by a keyword
     * @param keyword
     * @param response
     * @return
     * @throws IOException
     */
    @ExceptionHandler
    @PostMapping(value = "/search")
    @ResponseBody
    public String searchCourse(@RequestParam String keyword, HttpServletResponse response) throws IOException {
        log.trace("Controller to search a course have been called");

        response.sendRedirect("/Course/listCourse?keyword=" + keyword);
        return "listCourse";
    }

    /**
     * Method to load the page to search for a course
     * @return
     */
    @GetMapping(value = "/searchPage")
    public String searchCoursePage() {
        log.trace("Controller to load the page to search for a course have been called");

        return "searchCourse";
    }

}
