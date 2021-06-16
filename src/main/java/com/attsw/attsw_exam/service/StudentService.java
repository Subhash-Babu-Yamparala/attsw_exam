/**
 * Created by: nuwan_r
 * Created on: 6/16/2021
 **/
package com.attsw.attsw_exam.service;

import com.attsw.attsw_exam.model.Student;
import org.springframework.http.ResponseEntity;

public interface StudentService {

    ResponseEntity saveStudent(Student student);

    ResponseEntity updateStudent(Student student);

    ResponseEntity SearchStudentById(Integer studentId);

    ResponseEntity DeleteStudent(Integer studentId);

    ResponseEntity findAll();

}
