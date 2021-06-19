/**
 * Created by: subha_babu
 * Created on: 6/16/2021
 **/
package com.attsw.attsw_exam.service;

import com.attsw.attsw_exam.model.Teacher;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TeacherService {

    Teacher saveTeacher(Teacher Teacher);

    Teacher updateTeacher(Teacher Teacher);

    Teacher DeleteTeacher(Teacher teacher);

    List<Teacher> findAllActive();

    List<Teacher> findAllDeactive();

    List<Teacher> findAll();

    Optional<Teacher> findByIdAndStatus(Integer id, Integer status);

    Optional<Teacher> findByEmailAndStatus(String email, Integer status);
}
