package com.devkiu.dbsample.service;

import com.devkiu.dbsample.model.Course;
import com.devkiu.dbsample.model.Student;
import com.devkiu.dbsample.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {
    private final StudentRepository studentRepository;

    public List<Student> studentList() {
        return studentRepository.findAll();
    }

    public void addStudentCourse(long id, String courseName) {
        Optional<Student> optionalStudent = studentRepository.findById(id);

        optionalStudent.ifPresentOrElse(
                student -> student.getCourseList().add(new Course(null, courseName)),
                () -> {
                    throw new RuntimeException("Student not found");
                }
        );
    }
}
