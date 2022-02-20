package com.example.demo.dto;

import com.example.demo.entity.Course;
import com.sun.istack.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@RequiredArgsConstructor
public class CourseDto {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String duration;

    public CourseDto response(Course course) {
        CourseDto dto = new CourseDto();
        dto.setName(course.getName());
        dto.setDuration(course.getDuration());
        return dto;
    }

    public Course to() {
        Course course = new Course();
        course.setName(this.name);
        course.setDuration(this.duration);
        return course;
    }

    public void update (Course course){
       course.setName(this.name);
       course.setDuration(this.duration);
    }

}

