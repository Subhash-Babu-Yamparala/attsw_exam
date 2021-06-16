/**
 * Created by: nuwan_r
 * Created on: 6/16/2021
 **/
package com.attsw.attsw_exam.serviceImpl;

import com.attsw.attsw_exam.model.Teacher;
import com.attsw.attsw_exam.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Override
    public ResponseEntity saveTeacher(Teacher Teacher) {
        return null;
    }

    @Override
    public ResponseEntity updateTeacher(Teacher Teacher) {
        return null;
    }

    @Override
    public ResponseEntity SearchTeacherById(Integer TeacherId) {
        return null;
    }

    @Override
    public ResponseEntity DeleteTeacher(Integer TeacherId) {
        return null;
    }

    @Override
    public ResponseEntity findAll() {
        return null;
    }
}
