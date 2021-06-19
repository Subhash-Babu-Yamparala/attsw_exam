/**
 * Created by: subha_babu
 * Created on: 6/16/2021
 **/
package com.attsw.attsw_exam.service;

import com.attsw.attsw_exam.model.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {

    Student saveStudent(Student student);

    Student updateStudent(Student student);

    Student deleteStudent(Student student);

    List<Student> findAll();

    Optional<Student> findByIdAndStatus(Integer id, Integer status);
}
