/**
 * Created by: nuwan_r
 * Created on: 6/16/2021
 **/
package com.attsw.attsw_exam.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "teacher")
public class Teacher extends SharedModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "teacher_id")
    private Integer id;

    @Basic
    @Column(name = "teacher_name")
    private String name;

    @Basic
    @Column(name = "teacher_contactNo")
    private String contactNo;

    @Basic
    @Column(name = "teacher_address")
    private String address;

    @Basic
    @Column(name = "teacher_email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "teacher")
    private List<Student> student;

}
