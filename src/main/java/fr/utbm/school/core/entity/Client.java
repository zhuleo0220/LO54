/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.utbm.school.core.entity;

import lombok.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.io.Serializable;
import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Neil FARMER/Ruiqing Zhu
 */
// Using data result in stack overflow error !
//@Data
@RequiredArgsConstructor
//@NoArgsConstructor
@AllArgsConstructor
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Cacheable
@Entity
@Table(name = "CLIENT")
public class Client implements Serializable{

    private static final long serialVersionUID = 6529685098267757691L;

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
    @NotEmpty
    @NotNull
    private String lastName;

    @Getter
    @Setter
    @Basic(optional = false)
    @Column(name = "FIRSTNAME", length = 45)
    @NotEmpty
    @NotNull
    private String firstName;

    @Getter
    @Setter
    @Basic(optional = false)
    @Column(name = "ADDRESS", length = 128)
    @NotEmpty
    @NotNull
    private String address;

    @Getter
    @Setter
    @Basic(optional = false)
    @Column(name = "PHONE", length = 12)
    @Size(min=7, max=12)
    @NotEmpty
    @NotNull
    private String phone;

    @Getter
    @Setter
    @Basic(optional = false)
    @Column(name = "EMAIL", length = 254)
    @Size(min=3, max=254)
    @Pattern(message = "Invalid email", regexp = "^.+@.+\\..+$")
    @NotEmpty
    @NotNull
    private String email;

    @Valid
    @Getter
    @Setter
    @JoinColumn(name = "Course_SESSION_ID")
    @OneToOne(fetch = FetchType.LAZY)
    private CourseSession courseSession;

}
