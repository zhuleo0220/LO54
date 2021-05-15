/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Neil FARLER
 */
@Entity
@Table(name = "COURSE_SESSION")
public class CourseSession implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;

    @Basic(optional = false)
    @Column(name = "START_DATE")
    private Timestamp startDate;

    @Basic(optional = false)
    @Column(name = "END_DATE")
    private Timestamp endDate;

    @Basic(optional = true)
    @Column(name = "MAX_STUDENT")
    private Integer maxStudent;

    @JoinColumn(name = "COURSE_CODE")
    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;

    @JoinColumn(name = "LOCATION_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Location location;

    public CourseSession() {
    }

    public CourseSession(Long id, Timestamp startDate, Timestamp endDate,
                         Integer maxStudent, Course course, Location location) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxStudent = maxStudent;
        this.course = course;
        this.location = location;
    }

    public CourseSession(Timestamp startDate, Timestamp endDate,
                         Integer maxStudent, Course course, Location location) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.maxStudent = maxStudent;
        this.course = course;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public Integer getMaxStudent() {
        return maxStudent;
    }

    public Course getCourse() {
        return course;
    }

    public Location getLocation() {
        return location;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public void setMaxStudent(Integer maxStudent) {
        this.maxStudent = maxStudent;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setLocationId(Location location) {
        this.location = location;
    }


}
