/**
 * Created by: nuwan_r
 * Created on: 6/18/2021
 **/
package com.attsw.attsw_exam;

import com.attsw.attsw_exam.enums.Status;
import com.attsw.attsw_exam.model.Student;
import com.attsw.attsw_exam.model.Teacher;
import com.attsw.attsw_exam.repository.StudentRepository;
import com.attsw.attsw_exam.repository.TeacherRepository;
import com.attsw.attsw_exam.service.StudentService;
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
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentControllerTest {

    @Autowired
    private StudentService studentService;

    @MockBean
    private StudentRepository studentRepository;

    @Test
    public void testActiveStudent() {

        Student student1 = new Student();
        student1.setId(99);
        student1.setAge(23);
        student1.setCollageName("Ananda");
        student1.setContactNo("0788988988");
        student1.setStatus(Status.ACTIVE.getStatusSeq());


        studentService.findAllActiveStudent();
        verify(studentRepository,times(1)).findAllByStatus(Status.ACTIVE.getStatusSeq());

    }

    @Test
    public void testFindAllByCollleageName() {

        Student student2 = new Student();
        student2.setContactNo("0783838883");


        studentService.findAllByContactNo(student2.getContactNo());
        verify(studentRepository,times(1)).findAllByContactNo(student2.getContactNo());

    }

    @Test
    public void testUpdateTeacher() {

        Student student2 = new Student();
        student2.setId(192);
        student2.setAge(29);
        student2.setCollageName("Raju bhai");
        student2.setContactNo("0753833833");

        studentService.updateStudent(student2);
        verify(studentRepository,times(1)).save(student2);
    }

    @Test
    public void testDeleteTeacher() {

        Student student2 = new Student();
        student2.setId(192);
        student2.setAge(29);
        student2.setCollageName("Raju bhai");
        student2.setContactNo("0753833833");

        studentService.deleteStudent(student2);
        verify(studentRepository, times(1)).save(student2);

    }

    @Test
    public void testDeactiveStudent() {

        Student student2 = new Student();
        student2.setId(100);
        student2.setAge(21);
        student2.setCollageName("Nalanda");
        student2.setContactNo("0782827377");
        student2.setStatus(Status.DELETED.getStatusSeq());

        Student student3 = new Student();
        student3.setId(101);
        student3.setAge(21);
        student3.setCollageName("Baya");
        student3.setContactNo("07883888383");
        student3.setStatus(Status.ACTIVE.getStatusSeq());


        when(studentRepository.findAllByStatus(Status.DELETED.getStatusSeq())).thenReturn(Stream
                .of(student2,student3).collect(Collectors.toList()));

        assertEquals(2,studentService.findAllDeactive().size());
    }

    @Test
    public void testSystemActiveStudent() {

        Student student2 = new Student();
        student2.setId(100);
        student2.setAge(21);
        student2.setCollageName("Nalanda");
        student2.setContactNo("0782827377");
        student2.setStatus(Status.ACTIVE.getStatusSeq());

        Student student3 = new Student();
        student3.setId(101);
        student3.setAge(21);
        student3.setCollageName("Baya");
        student3.setContactNo("07883888383");
        student3.setStatus(Status.ACTIVE.getStatusSeq());


        when(studentRepository.findAllByStatus(Status.ACTIVE.getStatusSeq())).thenReturn(Stream
                .of(student2,student3).collect(Collectors.toList()));

        assertEquals(2,studentService.findAllActiveStudent().size());
    }



}
