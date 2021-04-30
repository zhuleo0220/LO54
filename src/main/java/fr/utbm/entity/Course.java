package fr.utbm.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="COURSE")
public class Course implements java.io.Serializable   {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Integer id;

    @Column(name="CODE")
    private String code;

    @Column(name="TITLE")
    private String title;
}
