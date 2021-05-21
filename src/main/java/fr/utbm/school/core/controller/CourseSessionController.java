package fr.utbm.school.core.controller;

import fr.utbm.school.core.entity.Course;
import fr.utbm.school.core.entity.CourseSession;
import fr.utbm.school.core.entity.Location;
import fr.utbm.school.core.exceptions.CourseSessionException;
import fr.utbm.school.core.service.CourseService;
import fr.utbm.school.core.service.CourseSessionService;
import fr.utbm.school.core.service.LocationService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

/**
 * @author : Neil Farmer/Ruiqing Zhu
 */
@Controller
@RequestMapping("/CourseSession")
public class CourseSessionController {

    // Logger of the controller
    private static final Logger logger = Logger.getLogger(ClientController.class.getName());

    @Autowired
    private CourseSessionService courseSessionService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private LocationService locationService;

    /**
     * Controller to add a course session
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String AddCourseSession(WebRequest request, HttpServletResponse response) throws IOException {
        logger.trace("Controller to save a course session have been called");

        // Get all information entered in the form
        Map<String,String[]> form = request.getParameterMap();
        String startDateString = form.get("startDate")[0];
        String startTimeString = form.get("startTime")[0];
        String endDateString = form.get("endDate")[0];
        String endTimeString = form.get("endTime")[0];
        String courseId = form.get("courseCode")[0];

        Integer maxStudent = null;
        Long locationId = Long.parseLong(form.get("locationId")[0]);

        // Parse the maxStudent value
        if(form.get("maxstudent")[0] != null && !"".equals(form.get("maxstudent")[0])){
            maxStudent = Integer.parseInt(form.get("maxstudent")[0]);
        }else{
            logger.trace("No max student for the new course session");
        }

        // Parse the date
        LocalDate startDate = LocalDate.parse(startDateString);
        LocalDate endDate = LocalDate.parse(endDateString);

        LocalTime startTime = LocalTime.parse(startTimeString);
        LocalTime endTime = LocalTime.parse(endTimeString);

        // Check that there's no null values
        if(locationId != null && startDate != null && endDate != null &&
                startTime != null && endTime != null){

            // Create a new course session with the information given
            CourseSession css = new CourseSession();
            css.setLocation(locationService.searchLocationById(locationId));
            css.setCourse(new Course(courseService.searchCourseById(courseId)));

            Timestamp startTimeStamp = new Timestamp(startDate.getYear() - 1900,
                    startDate.getMonthValue() - 1, startDate.getDayOfMonth(),
                    startTime.getHour(), startTime.getMinute(),
                    startTime.getSecond(), startTime.getNano());
            css.setStartDate(startTimeStamp);

            Timestamp endTimeStamp = new Timestamp(endDate.getYear() - 1900,
                    endDate.getMonthValue() - 1, endDate.getDayOfMonth(),
                    endTime.getHour(), endTime.getMinute(),
                    endTime.getSecond(), endTime.getNano());
            css.setEndDate(endTimeStamp);

            if(maxStudent != null){
                css.setMaxStudent(maxStudent);
            }

            try {
                courseSessionService.saveCourseSession(css);
            } catch (CourseSessionException cex) {
                logger.error("An CourseSession exception happen while saving the course session");

                response.sendRedirect("/lo54/error");
                return "error";
            } catch (Exception ex){
                logger.error("An unknown error happen while saving the course session");

                response.sendRedirect("/lo54/error");
                return "error";
            }

            response.sendRedirect("/Home/Homepage?success=La session a ete ajoute");
            return "home";

        }else{
            logger.error("One or more parameters were null");

            response.sendRedirect("/lo54/error");
            return "error";
        }

    }

    /**
     * Controller to load the page
     * @param course
     * @param model
     * @return
     * @throws SQLException
     */
    @RequestMapping(value = "/addPage", method = RequestMethod.GET)
    public String CourseSessionPage(@RequestParam String course, Model model) throws SQLException {
        logger.trace("Controller to load the page to save a course session have been called");

        // Create the select option for the city
        String locationSelector = ("<select id=\"location_id\" name=\"locationId\" class=\"form-control\">" +
                                    "<option value=\"\" disabled>--Choisir une ville--</option>");

        // Put each city in the select
        for(Location lo:locationService.getListLocation()){
            locationSelector+=("<option value=\"" + lo.getId() + "\">" + lo.getCity() + "</option>");
        }

        locationSelector+="</select>";

        String courseSelected = ("<input hidden=\"hidden\" type=\"text\" name=\"courseCode\" data-form-field=\"courseCode\" class=\"form-control\" value=\"" + course + "\" id=\"courseCode\">");

        model.addAttribute("locationSelect", locationSelector);
        model.addAttribute("courseSelected", courseSelected);
        return "addCourseSession";
    }

    /**
     * Controller to get the list of course session
     * @param locationId
     * @param date
     * @param model
     * @return
     */
    @GetMapping("/list")
    public String listCourseSession(@RequestParam(required=false) String locationId,
                                    @RequestParam(required=false) String date, Model model) {

        logger.trace("Controller to get list of course session have been called");

        // date
        Timestamp dateChoosen = null;

        // parse the date
        try{
            if(date != null && date != ""){
                Date dateGoodFormat = new SimpleDateFormat("yyyy-MM-dd").parse(date);
                dateChoosen = new Timestamp(dateGoodFormat.getYear(),
                        dateGoodFormat.getMonth(),
                        dateGoodFormat.getDate(), 0, 0, 0, 0);

                logger.trace("The date hae successfully been parsed");
                logger.info("The course sessions returned must start the " + dateChoosen.toString());
            }
        }catch (ParseException ex){
            logger.error("An error while parsing the date occurred");
            ex.printStackTrace();
        }

        // location id
        Long locationIdLong = null;

        // parse the location
        if(locationId != null && locationId != ""){
            locationIdLong = Long.parseLong(locationId);
            logger.info("The course sessions returned must be at the city with the id " + locationIdLong.toString());
        }

        // Get all the course seesion
        ArrayList<CourseSession> selectedCourseSession = courseSessionService.searchCourseSessionByParameter(dateChoosen, locationIdLong);

        // If there's no course then print it to the html page
        if(selectedCourseSession.isEmpty()){
            model.addAttribute("listSession","<p><b>No session found</b></p>");
            return "listCourseSession";
        }

        String courseSessionListString = "";
        for(CourseSession courseSession:selectedCourseSession){
            int startYear = courseSession.getStartDate().getYear() + 1900;
            int endYear = courseSession.getEndDate().getYear() + 1900;

            int startMonth = courseSession.getStartDate().getMonth() + 1;
            int endMonth = courseSession.getEndDate().getMonth() + 1;

            int percentFilled = courseSessionService.getPercentStudent(courseSession.getId());
            Course course = courseSession.getCourse();

            courseSessionListString += (
                    "<br>" +
                            "<table border=\"1\" frame=\"void\" rules=\"all\" style=\"background-color: #e6e6e6;width:75%;margin-left: auto;margin-right: auto;border-radius:10px;\">" +
                            "<tr>" +
                            "<th colspan = \"3\" style=\"text-align:center\">" + course.getCode() + " : " + course.getTitle() + "</th>" +
                            "</tr>" +
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

        courseSessionListString += "</div>";
        model.addAttribute("listSession", courseSessionListString);

        return "listCourseSession";
    }

    /**
     * Method to load the page to search for page
     * @param model
     * @return
     */
    @GetMapping("/searchPage")
    public String searchCourseSessionPage(Model model){
        logger.trace("Controller to load the page to search a course session have been called");

        String locationSelector = "";

        for(Location lo:locationService.getListLocation()){
            locationSelector += ("<option value=\"" + lo.getId() + "\">" + lo.getCity() + "</option>");
        }

        model.addAttribute("locationSelect", locationSelector);
        return "searchCourseSession";
    }

    /**
     * Controller to search for a course session
     * @param locationId
     * @param date
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    @ResponseBody
    public String searchCourse(@RequestParam String locationId, @RequestParam String date, HttpServletResponse response) throws IOException {
        logger.trace("Controller to search a list of course session have been called");

        response.sendRedirect("/CourseSession/list?locationId=" + locationId + "&date=" + date);

        return "listCourseSession";
    }

}