/**
 * Created by: nuwan_r
 * Created on: 6/16/2021
 **/
package com.attsw.attsw_exam.controller;

import com.attsw.attsw_exam.dto.StudentDto;
import com.attsw.attsw_exam.model.Student;
import com.attsw.attsw_exam.service.StudentService;
import com.attsw.attsw_exam.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("student")
public class StudentController {

    private static final Logger logger = Logger.getLogger(StudentController.class.getName());

    private final TeacherService teacherService;
    private final StudentService studentService;

    @Autowired
    public StudentController(TeacherService teacherService, StudentService studentService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    @PostMapping()
    public ResponseEntity createStudent(@RequestBody Student student) {
        return this.studentService.saveStudent(student);
    }

    @PutMapping()
    public ResponseEntity updateStudent(@RequestBody Student student) {
        return this.studentService.updateStudent(student);
    }

    @GetMapping("/{studentId}")
    public ResponseEntity findStudent(@PathVariable("studentId") Integer studentId) {
        return this.studentService.SearchStudentById(studentId);
    }

    @GetMapping("/findAll")
    public ResponseEntity findAllStudent() {
        return this.studentService.findAll();
    }

}
