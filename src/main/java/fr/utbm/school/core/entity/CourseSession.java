/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.entity;

import fr.utbm.school.core.validation.annotation.NotOverBooked;
import fr.utbm.school.core.validation.annotation.SessionDurationPositive;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Formula;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Neil Farmer/Ruiqing Zhu
 */
// Using data result in stack overflow error !
//@Data
@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
@SessionDurationPositive
@NotOverBooked
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
@Entity
@Table(name = "COURSE_SESSION")
public class CourseSession implements Serializable {

    private static final long serialVersionUID = 6529685098267757692L;

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Long id;

    @Getter
    @Setter
    @Future
    @Basic(optional = false)
    @Column(name = "START_DATE")
    @NotNull
    private Timestamp startDate;

    @Getter
    @Setter
    @Future
    @Basic(optional = false)
    @Column(name = "END_DATE")
    @NotNull
    private Timestamp endDate;

    @Getter
    @Setter
    @Min(1)
    @Basic(optional = true)
    @Column(name = "MAX_STUDENT")
    private Integer maxStudent;

    @Getter
    @Setter
    @Valid
    @Basic(optional = false)
    @JoinColumn(name = "COURSE_CODE")
    @ManyToOne(fetch = FetchType.EAGER)
    private Course course;

    @Getter
    @Setter
    @Valid
    @Basic(optional = false)
    @JoinColumn(name = "LOCATION_ID")
    @ManyToOne(fetch = FetchType.EAGER)
    private Location location;

    @Getter
    @Setter
    @OneToMany(mappedBy = "courseSession", fetch = FetchType.LAZY)
    private Set<Client> registeredClients = new HashSet<Client>();

}
