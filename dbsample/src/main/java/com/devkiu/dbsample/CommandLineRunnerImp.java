package com.devkiu.dbsample;

import com.devkiu.dbsample.model.Course;
import com.devkiu.dbsample.model.Student;
import com.devkiu.dbsample.repository.CourseRepository;
import com.devkiu.dbsample.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class CommandLineRunnerImp implements CommandLineRunner {
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Override
    public void run(String... args) {
        List<Course> courses = List.of(
                new Course(null, "Software Engineering"),
                new Course(null, "Advanced Programming")
        );
        studentRepository.save(new Student(
                null,
                "kelvin",
                "ndisi",
                "kelvin@gmail.com",
                courses
        ));
    }
}
