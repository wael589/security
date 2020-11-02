package com.example.demo.students;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentController {

    private static final List<Student> STUDENTS= Arrays.asList(new Student(1L,"wael sdd"), new Student(2L,"smida ert"), new Student(3L,"walid azer"));

    @GetMapping(path = "{studentId}")
    public Student getStudents(@PathVariable Long studentId){
        return STUDENTS.stream().filter(student -> studentId.equals(student.getStudentId())).
                findFirst().orElseThrow(()->new IllegalStateException("student"+studentId+"don't exist"));
    }
}
