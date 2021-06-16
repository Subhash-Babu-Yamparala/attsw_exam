/**
 * Created by: nuwan_r
 * Created on: 6/16/2021
 **/
package com.attsw.attsw_exam.serviceImpl;

import com.attsw.attsw_exam.model.Student;
import com.attsw.attsw_exam.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Override
    public ResponseEntity saveStudent(Student student) {
        return null;
    }

    @Override
    public ResponseEntity updateStudent(Student student) {
        return null;
    }

    @Override
    public ResponseEntity SearchStudentById(Integer studentId) {
        return null;
    }

    @Override
    public ResponseEntity DeleteStudent(Integer studentId) {
        return null;
    }

    @Override
    public ResponseEntity findAll() {
        return null;
    }
}
