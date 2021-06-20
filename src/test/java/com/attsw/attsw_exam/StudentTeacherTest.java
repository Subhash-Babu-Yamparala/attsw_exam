/**
 * Created by: nuwan_r
 * Created on: 6/20/2021
 **/
package com.attsw.attsw_exam;

import com.attsw.attsw_exam.enums.Status;
import com.attsw.attsw_exam.model.Student;
import com.attsw.attsw_exam.model.Teacher;
import com.attsw.attsw_exam.repository.TeacherRepository;
import com.attsw.attsw_exam.service.TeacherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentTeacherTest {

    @Autowired
    private TeacherService teacherService;

    @MockBean
    private TeacherRepository teacherRepository;

    @Test
    public void testFindByEmailTeacher() {

        Teacher teacher1 = new Teacher();
        teacher1.setStatus(Status.ACTIVE.getStatusSeq());
        teacher1.setAddress("colombo");
        teacher1.setContactNo("0783833833");
        teacher1.setEmail("sanath@gmail.com");
        teacher1.setName("sanath");
        teacher1.setId(123);

        Teacher teacher2 = new Teacher();
        teacher2.setStatus(Status.ACTIVE.getStatusSeq());
        teacher2.setAddress("anawala");
        teacher2.setContactNo("0763738883");
        teacher2.setEmail("sachin@gmail.com");
        teacher2.setName("sachin");
        teacher2.setId(124);

        teacherService.findByEmailAndStatus("sachin@gmail.com",Status.ACTIVE.getStatusSeq());
        verify(teacherRepository,times(1)).findByEmailAndStatus("sachin@gmail.com",Status.ACTIVE.getStatusSeq());
    }
}
