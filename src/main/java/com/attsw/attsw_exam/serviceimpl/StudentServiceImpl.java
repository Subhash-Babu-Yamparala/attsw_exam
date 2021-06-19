/**
 * Created by: subha_babu
 * Created on: 6/16/2021
 **/
package com.attsw.attsw_exam.serviceimpl;

import com.attsw.attsw_exam.enums.Status;
import com.attsw.attsw_exam.model.Student;
import com.attsw.attsw_exam.repository.StudentRepository;
import com.attsw.attsw_exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Student saveStudent(Student student) {
        return saveOrUpdate(student);
    }

    private Student saveOrUpdate(Student student) {

        try {
            student.setStatus(Status.ACTIVE.getStatusSeq());
            logger.info("Student Update/Saved Successfully!!");
        } catch (Exception e) {
            logger.warning("Server Error When Updating Student ");
        }
        return studentRepository.save(student);

    }

    @Override
    public Student updateStudent(Student student) {
        return saveOrUpdate(student);
    }

    @Override
    public Student deleteStudent(Student student) {

        try {
            student.setStatus(Status.DELETED.getStatusSeq());
            logger.info("Student Deleted Successfully!!");
            return student;
        } catch (Exception e) {
            logger.warning("Server Error When Delete Student ");
            return null;
        }

    }

    @Override
    public List<Student> findAll() {
        return this.studentRepository.findAllByStatus(Status.ACTIVE.getStatusSeq());
    }

    @Override
    public Optional<Student> findByIdAndStatus(Integer id, Integer status) {
        return this.studentRepository.findByIdAndStatus(id, Status.ACTIVE.getStatusSeq());
    }
}
