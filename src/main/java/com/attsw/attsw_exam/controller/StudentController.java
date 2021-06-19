/**
 * Created by: subha_babu
 * Created on: 6/16/2021
 **/
package com.attsw.attsw_exam.controller;

import com.attsw.attsw_exam.enums.Status;
import com.attsw.attsw_exam.model.Student;
import com.attsw.attsw_exam.service.StudentService;
import com.attsw.attsw_exam.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
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
        return Optional.ofNullable(student)
                .map(rec -> Optional.ofNullable(this.studentService.saveStudent(rec))
                        .map(recSav -> new ResponseEntity(recSav, HttpStatus.ACCEPTED))
                        .orElse(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)))
                .orElse(new ResponseEntity(HttpStatus.BAD_REQUEST));

    }

    @PutMapping()
    public ResponseEntity updateStudent(@RequestBody Student student) {
        return Optional.ofNullable(student)
                .map(rec -> Optional.ofNullable(this.studentService.updateStudent(rec))
                        .map(recSav -> new ResponseEntity(recSav, HttpStatus.ACCEPTED))
                        .orElse(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)))
                .orElse(new ResponseEntity(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping()
    public ResponseEntity deleteStudent(@PathVariable("studentId") Integer studentId) {
        return Optional.ofNullable(studentId).map(rec -> this.studentService
                .findByIdAndStatus(studentId, Status.ACTIVE.getStatusSeq())
                .map(filRec -> {
                    Optional.ofNullable(this.studentService.deleteStudent(filRec))
                            .map(deleRec -> new ResponseEntity(deleRec, HttpStatus.OK))
                            .orElse(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR));
                    return new ResponseEntity(filRec, HttpStatus.OK);
                }).orElse(new ResponseEntity("Student Not Found", HttpStatus.NOT_FOUND)))
                .orElse(new ResponseEntity(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/{studentId}")
    public ResponseEntity findStudent(@PathVariable("studentId") Integer studentId) {
        return Optional.ofNullable(studentId).map(rec -> this.studentService
                .findByIdAndStatus(studentId, Status.ACTIVE.getStatusSeq())
                .map(filRec -> new ResponseEntity(filRec, HttpStatus.OK))
                .orElse(new ResponseEntity("Student Not Found", HttpStatus.NOT_FOUND)))
                .orElse(new ResponseEntity(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/findAll")
    public ResponseEntity findAllStudent() {
        return new ResponseEntity(this.studentService.findAll(),HttpStatus.OK);
    }

}
