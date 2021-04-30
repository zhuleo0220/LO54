/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
/**
 *
 * @author Neil FARMER
 */
@Entity
@Table(name = "CLIENT")
public class Client implements Serializable{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;

    @Basic(optional = false)
    @Column(name = "LASTNAME")
    private String lastName;

    @Basic(optional = false)
    @Column(name = "FIRSTNAME")
    private String firstName;

    @Basic(optional = false)
    @Column(name = "ADDRESS")
    private String address;

    @Basic(optional = false)
    @Column(name = "PHONE")
    private String phone;

    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;

    @JoinColumn(name = "Course_SESSION_ID")
    @OneToOne(fetch = FetchType.EAGER)
    private CourseSession courseSession;

    /**
     * Default Constructor
     */
    public Client() {
    }

    /**
     * Constructor
     *
     * @param id
     * @param lastName
     * @param firstName
     * @param address
     * @param phone
     * @param email
     * @param courseSession
     */
    public Client(Long id, String lastName, String firstName, String address,
                  String phone, String email, CourseSession courseSession) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.courseSession = courseSession;
    }

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public CourseSession getCourseSession() {
        return courseSession;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCourseSession(CourseSession courseSession) {
        this.courseSession = courseSession;
    }

}
