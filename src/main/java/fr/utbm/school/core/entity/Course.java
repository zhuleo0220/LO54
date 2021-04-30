/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import org.redisson.api.annotation.REntity;
import org.redisson.api.annotation.RId;

/**
 *
 * @author Neil FARMER
 */
@Entity
@REntity
@Table(name = "COURSE")
public class Course implements Serializable {

    @Id
    @RId
    @Basic(optional = false)
    @Column(name = "CODE")
    private String code;

    @Basic(optional = false)
    @Column(name = "TITLE")
    private String title;

    public Course() {
    }

    public Course(String code, String title) {
        this.code = code;
        this.title = title;
    }

    public Course(Course course) {
        this.code = course.getCode();
        this.title = course.getTitle();
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Course other = (Course) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return Objects.equals(this.title, other.title);
    }



}
