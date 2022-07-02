package com.devkiu.dbsample.controller;

import com.devkiu.dbsample.model.Student;
import com.devkiu.dbsample.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/students")
public class StudentApi {
    private final StudentService studentService;

    @Autowired
    public StudentApi(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> studentList() {
        return studentService.studentList();
    }

    @PostMapping
    public ResponseEntity<Object> addStudentCourse(
            @RequestBody StudentCourseAddRequest studentCourseRequest) {
        studentService.addStudentCourse(
                studentCourseRequest.studentId(),
                studentCourseRequest.courseName());

        return ResponseEntity.ok()
                .body("Added course to student with id " + studentCourseRequest.studentId());
    }

    record StudentCourseAddRequest(long studentId, String courseName) {
    }
}
