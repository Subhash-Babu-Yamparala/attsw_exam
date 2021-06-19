/**
 * Created by: subha_babu
 * Created on: 6/16/2021
 **/
package com.attsw.attsw_exam.controller;

import com.attsw.attsw_exam.enums.Status;
import com.attsw.attsw_exam.model.Teacher;
import com.attsw.attsw_exam.service.StudentService;
import com.attsw.attsw_exam.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
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

        return Optional.ofNullable(teacher)
                .map(teacherObj -> this.teacherService.findByEmailAndStatus(teacher.getEmail(), Status.ACTIVE.getStatusSeq())
                        .map(updatingRec -> new ResponseEntity("Email Can't Be A Duplicated One", HttpStatus.BAD_REQUEST))
                        .orElseGet(() -> Optional.ofNullable(this.teacherService.saveTeacher(teacher))
                                .map(val -> new ResponseEntity(val, HttpStatus.ACCEPTED))
                                .orElse(new ResponseEntity("Teacher Not Saved", HttpStatus.INTERNAL_SERVER_ERROR))))
                .orElse(new ResponseEntity(HttpStatus.BAD_REQUEST));

    }

    /*update teacher*/
    @PutMapping()
    public ResponseEntity updateTeacher(@RequestBody Teacher teacher) {
        return Optional.ofNullable(teacher)
                .map(rec -> this.teacherService.findByIdAndStatus(teacher.getId(), Status.ACTIVE.getStatusSeq())
                        .map(updatingRec -> Optional.ofNullable(this.teacherService.updateTeacher(updatingRec))
                                .map(val -> new ResponseEntity(val, HttpStatus.OK))
                                .orElse(new ResponseEntity("Teacher Not Saved", HttpStatus.INTERNAL_SERVER_ERROR)))
                        .orElse(new ResponseEntity("Teacher Object Not Found ", HttpStatus.NOT_FOUND)))
                .orElse(new ResponseEntity(HttpStatus.BAD_REQUEST));

    }

    @DeleteMapping("/{teacherId}")
    public ResponseEntity deleteTeacher(@PathVariable("teacherId") Integer teacherId){
        return Optional.ofNullable(teacherId)
                .map(rec -> this.teacherService.findByIdAndStatus(teacherId, Status.ACTIVE.getStatusSeq())
                        .map(filRec -> Optional.ofNullable(this.teacherService.DeleteTeacher(filRec))
                                .map(deletedRec -> new ResponseEntity(deletedRec, HttpStatus.OK))
                                .orElse(new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR)))
                        .orElse(new ResponseEntity("Teacher Not Found", HttpStatus.NOT_FOUND)))
                .orElse(new ResponseEntity(HttpStatus.BAD_REQUEST));
    }


    /*find teacher*/
    @GetMapping("/{teacherId}")
    public ResponseEntity findTeacher(@PathVariable("teacherId") Integer teacherId) {
        return Optional.ofNullable(teacherId).map(rec -> this.teacherService
                .findByIdAndStatus(teacherId, Status.ACTIVE.getStatusSeq())
                .map(filRec -> new ResponseEntity(filRec, HttpStatus.OK))
                .orElse(new ResponseEntity("Teacher Not Found", HttpStatus.NOT_FOUND)))
                .orElse(new ResponseEntity(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/findAll")
    public ResponseEntity findAllTeachers() {
        return new ResponseEntity(this.teacherService.findAll(),HttpStatus.OK);
    }

    @GetMapping("/findAllActive")
    public ResponseEntity findAllActiveTeachers() {
        return new ResponseEntity(this.teacherService.findAllActive(),HttpStatus.OK);
    }

    @GetMapping("/findAllDeactive")
    public ResponseEntity findAllDeactiveTeachers() {
        return new ResponseEntity(this.teacherService.findAllDeactive(),HttpStatus.OK);
    }


}
