package com.example.studentCrud.dto;

import com.example.studentCrud.entity.Student;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class StudentCourseListDTO {

    private List<StudentCourseDTO> studentCourses;

    public StudentCourseListDTO(Student student) {
//        this.studentCourses = student.getStudentCourses()
//                .stream().map(StudentCourseDTO::fromEntity).collect(Collectors.toList());
    }
}
