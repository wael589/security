package com.example.demo.students;

public class Student {

    private final Long studentId;
    private final String studentName;

    public Student(Long studentId, String studentName) {
        this.studentId = studentId;
        this.studentName = studentName;
    }

    public Long getStudentId() {
        return studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", studentName=" + studentName +
                '}';
    }
}
