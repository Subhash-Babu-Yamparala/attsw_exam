/**
 * Created by: subha_babu
 * Created on: 6/16/2021
 **/
package com.attsw.attsw_exam.dto;

import com.attsw.attsw_exam.model.Student;
import lombok.Data;

import java.util.List;

@Data
public class TeacherDto extends SharedModelDto {

    private Integer id;

    private String name;

    private String contactNo;

    private String address;

    private String email;

    private List<Student> student;
}
