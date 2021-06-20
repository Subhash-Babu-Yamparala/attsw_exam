/**
 * Created by: subha_babu
 * Created on: 6/16/2021
 **/
package com.attsw.attsw_exam.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

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
    @Column(name = "teacher_name")
    private String name;

    @Basic
    @Column(name = "teacher_contactNo")
    private String contactNo;

    @Basic
    @Column(name = "teacher_address")
    private String address;

    @Basic
    @Email(message = "Email should be valid")
    @Column(name = "teacher_email")
    private String email;

    @OneToMany(cascade = CascadeType.ALL,targetEntity = Student.class)
    @JoinColumn(name = "teacher_id_fk", referencedColumnName = "teacher_id")
    private List<Student> student;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Student> getStudent() {
        return student;
    }

    public void setStudent(List<Student> student) {
        this.student = student;
    }

    public Teacher() {
        super();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return Objects.equals(getId(), teacher.getId()) &&
                Objects.equals(getName(), teacher.getName()) &&
                Objects.equals(getContactNo(), teacher.getContactNo()) &&
                Objects.equals(getAddress(), teacher.getAddress()) &&
                Objects.equals(getEmail(), teacher.getEmail()) &&
                Objects.equals(getStudent(), teacher.getStudent());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getContactNo(), getAddress(), getEmail(), getStudent());
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", student=" + student +
                '}';
    }
}
