/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.entity;

import lombok.Getter;
import lombok.Setter;

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
 * @author Neil Farmer/Ruiqing Zhu
 */
@Entity
@Table(name = "COURSE_SESSION")
public class CourseSession implements Serializable {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;

    @Getter
    @Setter
    @Basic(optional = false)
    @Column(name = "START_DATE")
    private Timestamp startDate;

    @Getter
    @Setter
    @Basic(optional = false)
    @Column(name = "END_DATE")
    private Timestamp endDate;

    @Getter
    @Setter
    @Basic(optional = true)
    @Column(name = "MAX_STUDENT")
    private Integer maxStudent;

    @Getter
    @Setter
    @JoinColumn(name = "COURSE_CODE")
    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;

    @Getter
    @Setter
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

}
