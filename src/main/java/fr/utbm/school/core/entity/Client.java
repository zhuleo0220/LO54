/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.entity;

import lombok.Getter;
import lombok.Setter;

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
import javax.validation.constraints.Size;

/**
 *
 * @author Neil FARMER/Ruiqing Zhu
 */
@Entity
@Table(name = "CLIENT")
public class Client implements Serializable{

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
    @Column(name = "LASTNAME", length = 45)
    private String lastName;

    @Getter
    @Setter
    @Basic(optional = false)
    @Column(name = "FIRSTNAME", length = 45)
    private String firstName;

    @Getter
    @Setter
    @Basic(optional = false)
    @Column(name = "ADDRESS", length = 128)
    private String address;

    @Getter
    @Setter
    @Basic(optional = false)
    @Column(name = "PHONE", length = 12)
    @Size(min=7, max=12)
    private String phone;

    @Getter
    @Setter
    @Basic(optional = false)
    @Column(name = "EMAIL", length = 254)
    @Size(min=3, max=254)
    private String email;

    @Getter
    @Setter
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

    /**
     * Constructor
     *
     * @param lastName
     * @param firstName
     * @param address
     * @param phone
     * @param email
     * @param courseSession
     */
    public Client(String lastName, String firstName, String address,
                  String phone, String email, CourseSession courseSession) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.courseSession = courseSession;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", courseSession=" + courseSession +
                '}';
    }
}
