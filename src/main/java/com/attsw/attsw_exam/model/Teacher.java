/**
 * Created by: subha_babu
 * Created on: 6/16/2021
 **/
package com.attsw.attsw_exam.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "teacher")
public class Teacher extends SharedModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "teacher_id")
    private Integer id;

    @Basic
    @NotNull(message = "name cannot be null")
    @Column(name = "teacher_name")
    private String name;

    @Basic
    @NotNull(message = "contact number can not be null")
    @Column(name = "teacher_contactNo")
    private String contactNo;

    @Basic
    @Column(name = "teacher_address")
    private String address;

    @Basic
    @NotNull(message = "email can not be null")
    @Email(message = "Email should be valid")
    @Column(name = "teacher_email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL,targetEntity = Student.class)
    @JoinColumn(name = "teacher_id_fk", referencedColumnName = "teacher_id")
    private List<Student> student;


    public Teacher() {
        super();

    }


}
