package fr.utbm.school.core.controller;

import fr.utbm.school.core.entity.Course;
import fr.utbm.school.core.entity.CourseSession;
import fr.utbm.school.core.service.CourseService;
import fr.utbm.school.core.service.CourseSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@Controller
@RequestMapping("/Course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @Autowired
    private CourseSessionService courseSessionService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addCourse(Course course) throws SQLException {
        courseService.saveCourse(course);

        return "home";

    }

    @RequestMapping(value = "/addpage", method = RequestMethod.GET)
    public String coursePage() {

        return "addCourseForm";
    }

    @GetMapping("/listCourse")
    public String listCourse(@RequestParam(required=false) String keyword, Model model){
        // Get all the course
        ArrayList<Course> selectedCourse = courseService.getCourseByKeyword(keyword);

        // If there's no course then print it to the html page
        if(selectedCourse.isEmpty()){
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

    @GetMapping("/courseDescription")
    public String listCourseSession(@RequestParam String course, Model model) {
        Course courseObject = courseService.searchCourseById(course);

        model.addAttribute("courseCode", courseObject.getCode());
        model.addAttribute("courseTitle", courseObject.getTitle());

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

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public String searchCourse(@RequestParam String keyword, HttpServletResponse response) throws IOException {
        response.sendRedirect("/listCourse?keyword=" + keyword);

        return "home";
    }

    @RequestMapping(value = "/searchPage", method = RequestMethod.GET)
    public String searchCoursePage() {
        return "searchCourse";
    }

}
