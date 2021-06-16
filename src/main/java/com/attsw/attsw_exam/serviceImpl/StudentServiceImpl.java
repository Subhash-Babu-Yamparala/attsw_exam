/**
 * Created by: subha_babu
 * Created on: 6/16/2021
 **/
package com.attsw.attsw_exam.serviceImpl;

import com.attsw.attsw_exam.controller.StudentController;
import com.attsw.attsw_exam.enums.Status;
import com.attsw.attsw_exam.model.Student;
import com.attsw.attsw_exam.repository.StudentRepository;
import com.attsw.attsw_exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class StudentServiceImpl implements StudentService {

    private static final Logger logger = Logger.getLogger(StudentServiceImpl.class.getName());
    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public ResponseEntity saveStudent(Student student) {
        return saveOrUpdate(student);
    }

    private ResponseEntity saveOrUpdate(Student student) {
        return Optional.ofNullable(student)
                .map(rec -> {
                    rec.setStatus(Status.ACTIVE.getStatusSeq());
                    Student savedStudentObject = null;
                    try {
                        logger.info("Student Update/Saved Successfully!!");
                        savedStudentObject = studentRepository.save(rec);
                        return new ResponseEntity(savedStudentObject, HttpStatus.ACCEPTED);
                    } catch (Exception e) {
                        logger.warning("Server Error When Updating Student ");
                        e.printStackTrace();
                        return new ResponseEntity("Server Error When Updating Student", HttpStatus.INTERNAL_SERVER_ERROR);
                    }
                })
                .orElse(new ResponseEntity(HttpStatus.BAD_REQUEST));
    }

    @Override
    public ResponseEntity updateStudent(Student student) {
        return saveOrUpdate(student);
    }

    @Override
    public ResponseEntity SearchStudentById(Integer studentId) {
        return Optional.ofNullable(studentId).map(rec -> this.studentRepository
                .findByIdAndStatus(studentId, Status.ACTIVE.getStatusSeq())
                .map(filRec -> new ResponseEntity(filRec, HttpStatus.OK))
                .orElse(new ResponseEntity("Student Not Found", HttpStatus.NOT_FOUND)))
                .orElse(new ResponseEntity(HttpStatus.BAD_REQUEST));
    }

    @Override
    public ResponseEntity DeleteStudent(Integer studentId) {
        return Optional.ofNullable(studentId).map(rec -> this.studentRepository
                .findByIdAndStatus(studentId, Status.ACTIVE.getStatusSeq())
                .map(filRec -> {
                    filRec.setStatus(Status.DELETED.getStatusSeq());
                    this.studentRepository.save(filRec);
                    return new ResponseEntity(filRec, HttpStatus.OK);
                }).orElse(new ResponseEntity("Student Not Found", HttpStatus.NOT_FOUND)))
                .orElse(new ResponseEntity(HttpStatus.BAD_REQUEST));
    }

    @Override
    public ResponseEntity findAll() {
        List<Student> listOfStudent = this.studentRepository.findAllByStatus(Status.ACTIVE.getStatusSeq());
        return new ResponseEntity(listOfStudent, HttpStatus.OK);
    }
}
