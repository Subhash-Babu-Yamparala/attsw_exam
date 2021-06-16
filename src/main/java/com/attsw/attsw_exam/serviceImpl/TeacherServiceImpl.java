/**
 * Created by: subha_babu
 * Created on: 6/16/2021
 **/
package com.attsw.attsw_exam.serviceImpl;

import com.attsw.attsw_exam.enums.Status;
import com.attsw.attsw_exam.model.Teacher;
import com.attsw.attsw_exam.repository.TeacherRepository;
import com.attsw.attsw_exam.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TeacherServiceImpl implements TeacherService {

    private static final Logger logger = Logger.getLogger(TeacherServiceImpl.class.getName());
    private final TeacherRepository teacherRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public ResponseEntity saveTeacher(Teacher teacher) {
        return Optional.ofNullable(teacher)
                .map(rec -> this.teacherRepository.findByEmailAndStatus(rec.getEmail(), Status.ACTIVE.getStatusSeq())
                        .map(updatingRec -> new ResponseEntity("Email Can't Be A Duplicated One", HttpStatus.BAD_REQUEST)).orElseGet(() -> {
                            rec.setStatus(Status.ACTIVE.getStatusSeq());
                            logger.info("Teacher Saved Successfully!!");
                            Teacher savedTeacher = null;
                            try {
                                savedTeacher = this.teacherRepository.save(rec);
                                return new ResponseEntity(savedTeacher, HttpStatus.ACCEPTED);
                            } catch (Exception e) {
                                logger.warning("Server Error When Creating Teacher ");
                                e.printStackTrace();
                                return new ResponseEntity("Server Error When Updating Teacher", HttpStatus.INTERNAL_SERVER_ERROR);
                            }
                        })).orElse(new ResponseEntity(HttpStatus.BAD_REQUEST));
    }

    @Override
    public ResponseEntity updateTeacher(Teacher teacher) {
        return Optional.ofNullable(teacher)
                .map(rec -> this.teacherRepository.findByIdAndStatus(teacher.getId(), Status.ACTIVE.getStatusSeq())
                        .map(updatingRec -> {
                            rec.setStatus(Status.ACTIVE.getStatusSeq());
                            Teacher savedObject = null;
                            try {
                                savedObject = this.teacherRepository.save(rec);
                                return new ResponseEntity(savedObject, HttpStatus.ACCEPTED);
                            } catch (Exception e) {
                                logger.warning("Server Error When Updating Teacher ");
                                e.printStackTrace();
                                return new ResponseEntity("Server Error When Updating Teacher", HttpStatus.INTERNAL_SERVER_ERROR);
                            }
                        }).orElse(new ResponseEntity("Teacher Object Not Found ", HttpStatus.NOT_FOUND))).orElse(new ResponseEntity(HttpStatus.BAD_REQUEST));

    }

    @Override
    public ResponseEntity SearchTeacherById(Integer teacherId) {
        return Optional.ofNullable(teacherId).map(rec -> this.teacherRepository
                .findByIdAndStatus(teacherId, Status.ACTIVE.getStatusSeq())
                .map(filRec -> new ResponseEntity(filRec, HttpStatus.OK))
                .orElse(new ResponseEntity("Teacher Not Found", HttpStatus.NOT_FOUND)))
                .orElse(new ResponseEntity(HttpStatus.BAD_REQUEST));
    }

    @Override
    public ResponseEntity DeleteTeacher(Integer teacherId) {
        return Optional.ofNullable(teacherId).map(rec -> this.teacherRepository
                .findByIdAndStatus(teacherId, Status.ACTIVE.getStatusSeq())
                .map(filRec -> {
                    filRec.setStatus(Status.DELETED.getStatusSeq());
                    this.teacherRepository.save(filRec);
                    return new ResponseEntity(filRec, HttpStatus.OK);
                }).orElse(new ResponseEntity("Teacher Not Found", HttpStatus.NOT_FOUND)))
                .orElse(new ResponseEntity(HttpStatus.BAD_REQUEST));
    }

    @Override
    public ResponseEntity findAll() {
        List<Teacher> listOfTeacher = this.teacherRepository.findAllByStatus(Status.ACTIVE.getStatusSeq());
        return new ResponseEntity(listOfTeacher, HttpStatus.OK);
    }
}
