/**
 * Created by: subha_babu
 * Created on: 6/16/2021
 **/
package com.attsw.attsw_exam.service;

import com.attsw.attsw_exam.model.Teacher;
import org.springframework.http.ResponseEntity;

public interface TeacherService {

    ResponseEntity saveTeacher(Teacher Teacher);

    ResponseEntity updateTeacher(Teacher Teacher);

    ResponseEntity SearchTeacherById(Integer TeacherId);

    ResponseEntity DeleteTeacher(Integer TeacherId);

    ResponseEntity findAll();
    
}
