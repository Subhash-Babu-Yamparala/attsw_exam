/**
 * Created by: subha_babu
 * Created on: 6/16/2021
 **/
package com.attsw.attsw_exam.controller;

import com.attsw.attsw_exam.model.Teacher;
import com.attsw.attsw_exam.service.StudentService;
import com.attsw.attsw_exam.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("teacher")
public class TeacherController {

    private static final Logger logger = Logger.getLogger(TeacherController.class.getName());

    private final TeacherService teacherService;
    private final StudentService studentService;

    @Autowired
    public TeacherController(TeacherService teacherService, StudentService studentService) {
        this.teacherService = teacherService;
        this.studentService = studentService;
    }

    /*save teacher*/
    @PostMapping()
    public ResponseEntity createTeacher(@RequestBody Teacher teacher) {
        return this.teacherService.saveTeacher(teacher);
    }

    /*update teacher*/
    @PutMapping()
    public ResponseEntity updateTeacher(@RequestBody Teacher teacher) {
        return this.teacherService.updateTeacher(teacher);
    }

    /*find teacher*/
    @GetMapping("/{teacherId}")
    public ResponseEntity findTeacher(@PathVariable("teacherId") Integer teacherId) {
        return this.teacherService.SearchTeacherById(teacherId);
    }

    @GetMapping("/findAll")
    public ResponseEntity findAllTeachers() {
        return this.teacherService.findAll();
    }


}
