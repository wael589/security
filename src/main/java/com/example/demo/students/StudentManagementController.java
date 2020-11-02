package com.example.demo.students;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("management/api/v1/students/")
public class StudentManagementController {

    private static final List<Student> STUDENTS= Arrays.asList(new Student(1L,"wael sdd"), new Student(2L,"smida ert"), new Student(3L,"walid azer"));

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_DEV','ROLE_ADMINTRAINEE','ROLE_CEO')")
    public List<Student> getAllStudent(){
        return STUDENTS;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('dev:hack')")
    public void registerNewStudent(@RequestBody Student st){
        System.out.println(st);
    }


    @DeleteMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('dev:hack')")
    public void deleteStudent(@PathVariable Long studentId){
        System.out.println(studentId);
    }

    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('dev:hack')")
    public void updateStudent(@PathVariable Long studentId,@RequestBody Student st){

        System.out.println(String.format("%s %s",st, st));
    }
}
