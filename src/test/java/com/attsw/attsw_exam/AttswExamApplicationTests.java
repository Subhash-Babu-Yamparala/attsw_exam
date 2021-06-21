package com.attsw.attsw_exam;

import com.attsw.attsw_exam.enums.Status;
import com.attsw.attsw_exam.model.Student;
import com.attsw.attsw_exam.model.Teacher;
import com.attsw.attsw_exam.repository.StudentRepository;
import com.attsw.attsw_exam.repository.TeacherRepository;
import com.attsw.attsw_exam.service.StudentService;
import com.attsw.attsw_exam.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = AttswExamApplication.class
)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
class AttswExamApplicationTests {

	@Autowired
	private TeacherService teacherService;

	@MockBean
	private TeacherRepository teacherRepository;

	@Autowired
	private StudentService studentService;

	@MockBean
	private StudentRepository studentRepository;

	@Autowired
	MockMvc mockMvc;

	@Test
	public void testActiveTeachersList() {



		List<Student> listODStudent = new ArrayList<>();
		Student student1 = new Student();
		student1.setId(99);
		student1.setAge(23);
		student1.setCollageName("Ananda");
		student1.setContactNo("0788988988");
		listODStudent.add(student1);

		List<Student> listODStudent2 = new ArrayList<>();
		Student student2 = new Student();
		student2.setId(100);
		student2.setAge(21);
		student2.setCollageName("Nalanda");
		student2.setContactNo("0782827377");
		listODStudent2.add(student2);

		Teacher teacher1 = new Teacher();
		teacher1.setStatus(Status.ACTIVE.getStatusSeq());
		teacher1.setAddress("colombo");
		teacher1.setContactNo("0783833833");
		teacher1.setEmail("sanath@gmail.com");
		teacher1.setName("sanath");
		teacher1.setId(123);
		teacher1.setStudent(listODStudent);

		Teacher teacher2 = new Teacher();
		teacher2.setStatus(Status.ACTIVE.getStatusSeq());
		teacher2.setAddress("anawala");
		teacher2.setContactNo("0763738883");
		teacher2.setEmail("sachin@gmail.com");
		teacher2.setName("sachin");
		teacher2.setId(124);
		teacher2.setStudent(listODStudent2);

		when(teacherRepository.findAllByStatus(Status.ACTIVE.getStatusSeq())).thenReturn(Stream
				.of(teacher1,teacher2).collect(Collectors.toList()));

		assertEquals(2,teacherService.findAllActive().size());
	}

	@Test
	public void testDeactiveTeachers() {

		List<Student> listODStudent2 = new ArrayList<>();
		Student student2 = new Student();
		student2.setId(100);
		student2.setAge(21);
		student2.setCollageName("Nalanda");
		student2.setContactNo("0782827377");
		listODStudent2.add(student2);

		Teacher teacher3 = new Teacher();
		teacher3.setStatus(Status.ACTIVE.getStatusSeq());
		teacher3.setAddress("karachi");
		teacher3.setContactNo("0763738883");
		teacher3.setEmail("ganguli@gmail.com");
		teacher3.setName("ganguli");
		teacher3.setId(125);
		teacher3.setStudent(listODStudent2);


		when(teacherRepository.findAllByStatus(Status.DELETED.getStatusSeq())).thenReturn(Stream
				.of(teacher3).collect(Collectors.toList()));

		assertEquals(1,teacherService.findAllDeactive().size());
	}

	@Test
	public void testSaveTeacher() {

		List<Student> listODStudent2 = new ArrayList<>();
		Student student2 = new Student();
		student2.setId(192);
		student2.setAge(29);
		student2.setCollageName("Raju bhai");
		student2.setContactNo("0753833833");
		listODStudent2.add(student2);

		Teacher teacher3 = new Teacher();
		teacher3.setStatus(Status.ACTIVE.getStatusSeq());
		teacher3.setAddress("Panjab");
		teacher3.setContactNo("0672728882");
		teacher3.setEmail("shewag@gmail.com");
		teacher3.setName("shewag");
		teacher3.setId(128);
		teacher3.setStudent(listODStudent2);


		when(teacherRepository.save(teacher3)).thenReturn(teacher3);

		assertEquals(teacher3,teacherService.saveTeacher(teacher3));
	}

	@Test
	public void testUpdateTeacher() {

		List<Student> listODStudent2 = new ArrayList<>();
		Student student2 = new Student();
		student2.setId(192);
		student2.setAge(29);
		student2.setCollageName("Raju bhai");
		student2.setContactNo("0753833833");
		listODStudent2.add(student2);

		Teacher teacher3 = new Teacher();
		teacher3.setStatus(Status.ACTIVE.getStatusSeq());
		teacher3.setAddress("Panjab");
		teacher3.setContactNo("0672728882");
		teacher3.setEmail("shewagv1@gmail.com");
		teacher3.setName("shewag");
		teacher3.setId(128);
		teacher3.setStudent(listODStudent2);

		teacherService.updateTeacher(teacher3,teacher3);
		verify(teacherRepository,times(1)).save(teacher3);
	}

	@Test
	public void testDeleteTeacher() {

		List<Student> listODStudent2 = new ArrayList<>();
		Student student2 = new Student();
		student2.setId(192);
		student2.setAge(29);
		student2.setCollageName("Raju bhai");
		student2.setContactNo("0753833833");
		listODStudent2.add(student2);

		Teacher teacher3 = new Teacher();
		teacher3.setStatus(Status.ACTIVE.getStatusSeq());
		teacher3.setAddress("Panjab");
		teacher3.setContactNo("0672728882");
		teacher3.setEmail("shewag@gmail.com");
		teacher3.setName("shewag");
		teacher3.setId(128);
		teacher3.setStudent(listODStudent2);

		teacherService.deleteTeacher(teacher3);
		verify(teacherRepository,times(1)).save(teacher3);

	}

	@Test
	public void testFindByIdTeacher() {

		List<Student> listODStudent2 = new ArrayList<>();
		Student student2 = new Student();
		student2.setId(192);
		student2.setAge(29);
		student2.setCollageName("Raju bhai");
		student2.setContactNo("0753833833");
		listODStudent2.add(student2);

		Teacher teacher3 = new Teacher();
		teacher3.setStatus(Status.ACTIVE.getStatusSeq());
		teacher3.setAddress("Panjab");
		teacher3.setContactNo("0672728882");
		teacher3.setEmail("shewag@gmail.com");
		teacher3.setName("shewag");
		teacher3.setId(128);
		teacher3.setStudent(listODStudent2);

		teacherService.findByIdAndStatus(128,Status.ACTIVE.getStatusSeq());
		verify(teacherRepository,times(1)).findByIdAndStatus(128,Status.ACTIVE.getStatusSeq());


	}

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

	/*student controller class*/

	@Test
	public void findAllTest() throws Exception {

		Mockito.when(studentRepository.findAll()).thenReturn(
				Collections.emptyList()
		);

		MvcResult mvcResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/student/findAll")
						.accept(MediaType.APPLICATION_JSON)
		).andReturn();

		System.out.println(mvcResult);

		Mockito.verify(studentRepository).findAllByStatus(Status.ACTIVE.getStatusSeq());
	}
	/*..///....student controller class*/

	@Test
	void contextLoads() {
	}

}
