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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
@Entity
@Table(name = "LOCATION")
public class Location implements Serializable {

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
    @Column(name = "CITY", length = 45)
    @Size(min = 1, max = 45)
    private String city;

    public Location() {
    }

    public Location(Long id, String city) {
        this.id = id;
        this.city = city;
    }

    public Location(String city) {
        this.city = city;
    }

}
