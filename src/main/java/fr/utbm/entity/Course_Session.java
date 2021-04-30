package fr.utbm.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="COURSE_SESSION")
public class Course_Session implements java.io.Serializable   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @Column(name="Start_date")
    private Date start_date;

    @Column(name="End_date")
    private Date end_date;

    @Column(name="Max_Number")
    private Integer maxnumber;

    @Column(name="Course_Id")
    private Integer course_id;

    @Column(name="Location_Id")
    private Integer location_id;
}
