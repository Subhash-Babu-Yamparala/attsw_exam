/**
 * Created by: subha_babu
 * Created on: 6/16/2021
 **/
package com.attsw.attsw_exam.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "student")
public class    Student extends SharedModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "student_id")
    private Integer id;

    @Basic
    @NotNull(message = "name cannot be null")
    @Column(name = "student_name")
    private String name;

    @Basic
    @NotNull(message = "studnet age cannot be null")
    @Column(name = "student_age")
    private Integer age;

    @Basic
    @NotNull(message = "student college name cannot be null")
    @Column(name = "student_collage_name")
    private String collageName;

    @Basic
    @NotNull(message = "student contact number cannot be null")
    @Column(name = "student_contact_no")
    private String contactNo;

}
