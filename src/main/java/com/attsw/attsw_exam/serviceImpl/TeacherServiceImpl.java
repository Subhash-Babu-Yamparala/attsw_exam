/**
 * Created by: subha_babu
 * Created on: 6/16/2021
 **/
package com.attsw.attsw_exam.serviceImpl;

import com.attsw.attsw_exam.enums.Status;
import com.attsw.attsw_exam.model.Student;
import com.attsw.attsw_exam.model.Teacher;
import com.attsw.attsw_exam.repository.StudentRepository;
import com.attsw.attsw_exam.repository.TeacherRepository;
import com.attsw.attsw_exam.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class TeacherServiceImpl implements TeacherService {

    private static final Logger logger = Logger.getLogger(TeacherServiceImpl.class.getName());
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.teacherRepository = teacherRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Teacher saveTeacher(Teacher teacher) {

        teacher.setStatus(Status.ACTIVE.getStatusSeq());
        logger.info("Teacher Saved Successfully!!");
        Teacher savedTeacher = null;
        try {
            savedTeacher = this.teacherRepository.save(teacher);
        } catch (Exception e) {
            logger.warning("Server Error When Creating Teacher ");
            e.printStackTrace();
        }
        return savedTeacher;

    }

    @Override
    public Teacher updateTeacher(Teacher teacher) {

        teacher.setStatus(Status.ACTIVE.getStatusSeq());
        Teacher savedObject = null;
        try {
            savedObject = this.teacherRepository.save(teacher);
        } catch (Exception e) {
            logger.warning("Server Error When Updating Teacher ");
            e.printStackTrace();
        }
        return savedObject;

    }

    @Override
    public Teacher DeleteTeacher(Teacher teacher) {

        teacher.setStatus(Status.DELETED.getStatusSeq());
        if(teacher.getStudent()!=null) {
            teacher.getStudent().forEach(rec -> rec.setStatus(Status.DELETED.getStatusSeq()));
        }
        return this.teacherRepository.save(teacher);

    }

    @Override
    public List<Teacher> findAllActive() {
       return this.teacherRepository.findAllByStatus(Status.ACTIVE.getStatusSeq());

    }

    @Override
    public List<Teacher> findAllDeactive() {
        return this.teacherRepository.findAllByStatus(Status.DELETED.getStatusSeq());

    }

    @Override
    public List<Teacher> findAll() {
        return this.teacherRepository.findAll();

    }


    @Override
    public Optional<Teacher> findByIdAndStatus(Integer id, Integer status) {
        return this.teacherRepository.findByIdAndStatus(id, Status.ACTIVE.getStatusSeq());
    }

    @Override
    public Optional<Teacher> findByEmailAndStatus(String email, Integer status) {
        return this.teacherRepository.findByEmailAndStatus(email, Status.ACTIVE.getStatusSeq());
    }

}
