/**
 * Created by: nuwan_r
 * Created on: 6/16/2021
 **/
package com.attsw.attsw_exam.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "student")
public class Student extends SharedModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "student_id")
    private Integer id;

    @Basic
    @Column(name = "student_name")
    private String name;

    @Basic
    @Column(name = "student_age")
    private Integer age;

    @Basic
    @Column(name = "student_collage_name")
    private String collageName;

    @Basic
    @Column(name = "student_contact_no")
    private String contactNo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

}
