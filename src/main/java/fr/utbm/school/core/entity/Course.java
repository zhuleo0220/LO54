/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.redisson.api.annotation.REntity;
import org.redisson.api.annotation.RId;
import org.redisson.api.annotation.RIndex;

/**
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
@Entity
@REntity
@Table(name = "COURSE")
public class Course implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;

    @Pattern(message = "The course code must contain to upper letter and two digit", regexp = "[A-Z]{2}[0-9]{2}")
    @Id
    @RId
    @Basic(optional = false)
    @Column(name = "CODE")
    @NotEmpty
    @NotNull
    private String code;

    @RIndex
    @Basic(optional = false)
    @Column(name = "TITLE", length = 45)
    @Size(min = 3, max = 45)
    @NotEmpty
    @NotNull
    private String title;

    /**
     * Copy constructor
     *
      * @param course to copy
     */
    public Course(@NonNull Course course) {
        this.code = course.getCode();
        this.title = course.getTitle();
    }
}
