package com.example.studentCrud.dto;

import com.example.studentCrud.entity.Student;
import com.example.studentCrud.entity.StudentCourse;
import com.example.studentCrud.entity.composite.StudentCoursePK;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StudentCourseDTO {

    private Long courseId;
    private String duration;
    private long version;

    public static StudentCourseDTO fromEntity(StudentCourse studentCourse) {
        StudentCourseDTO studentCourseDTO = new StudentCourseDTO();
        studentCourseDTO.setCourseId(studentCourse.getPk().getCourseId());
        // studentCourseDTO.setVersion(studentCourse.getVersion());
        return studentCourseDTO;
    }

    public StudentCourse toEntity(Student student) {
        StudentCourse studentCourse = new StudentCourse();
        studentCourse.setDuration(duration);
        //  studentCourse.setVersion(version);
        studentCourse.setPk(new StudentCoursePK(student, courseId));
//        student.getStudentCourses().add(studentCourse);
        return studentCourse;
    }
}
