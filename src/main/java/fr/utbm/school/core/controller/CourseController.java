package fr.utbm.school.core.controller;

import fr.utbm.school.core.entity.Course;
import fr.utbm.school.core.entity.CourseSession;
import fr.utbm.school.core.service.CourseService;
import fr.utbm.school.core.service.CourseSessionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static fr.utbm.school.core.functions.Levenshtein.closestKeyword;

/**
 * @author : Neil Farmer/Ruiqing Zhu
 */
@Controller
@RequestMapping("/Course")
public class CourseController {

    // Logger of the controller
    private static final Logger logger = Logger.getLogger(CourseController.class.getName());

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
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addCourse(Course course, HttpServletResponse response) throws SQLException, IOException {
        logger.trace("Controller to add a course have been called");

        // Save the course
        try {
            courseService.saveCourse(course);
        }catch (Exception ex){
            response.sendRedirect("/LO54/error");
            return "error";
        }


        response.sendRedirect("/Home/Homepage?success=Le cours a ete ajoute");
        return "home";

    }

    /**
     * Method to go to the page to add a course
     * @return
     */
    @RequestMapping(value = "/addpage", method = RequestMethod.GET)
    public String coursePage() {
        logger.trace("Controller to load the page to add a course have been called");

        return "addCourseForm";
    }

    /**
     * Method to get a list of course (with a keyword : optional)
     * @param keyword
     * @param model
     * @return
     */
    @GetMapping("/listCourse")
    public String listCourse(@RequestParam(required=false) String keyword, Model model){
        logger.trace("Controller to get a list of course have been called");

        // Get all the course
        ArrayList<Course> selectedCourse = courseService.getCourseByKeyword(keyword);

        // If there's no course then print it to the html page
        if(selectedCourse.isEmpty()) {
            logger.info("No course found with the keyword");

            if (keyword != null) {
                String bestKeyword = closestKeyword(keyword, courseService.getCourseKeyword(),
                        1, (int) Math.floor(keyword.length() / 2));

                if (bestKeyword != null) {
                    String betterKeyword = "<p>Rechercher plutot : <a href=/Course/listCourse?keyword=" +
                            bestKeyword + ">" + bestKeyword + "</a></p>";

                    model.addAttribute("list", betterKeyword);
                    return "listCourse";
                }
            }

            model.addAttribute("list", "<p>Aucun cours trouvé<p>");
            return "listCourse";

        }


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
        logger.trace("Controller to get a description of a course have been called");

        // search the course by his id
        Course courseObject = courseService.searchCourseById(course);

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
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public String searchCourse(@RequestParam String keyword, HttpServletResponse response) throws IOException {
        logger.trace("Controller to search a course have been called");

        response.sendRedirect("/Course/listCourse?keyword=" + keyword);

        return "listCourse";
    }

    /**
     * Method to load the page to search for a course
     * @return
     */
    @RequestMapping(value = "/searchPage", method = RequestMethod.GET)
    public String searchCoursePage() {
        logger.trace("Controller to load the page to search for a course have been called");

        return "searchCourse";
    }

}
