package fr.utbm.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="CLIENT")
public class Client implements java.io.Serializable   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @Column(name="LASTNAME")
    private String lastname;

    @Column(name="FIRSTNAME")
    private String firstname;

    @Column(name="ADDRESS")
    private String address;

    @Column(name="PHONE")
    private String phone;

    @Column(name="EMAIL")
    private String email;

    @Column(name="COURSE_SESSION_ID")
    private String course_session_id;
}
