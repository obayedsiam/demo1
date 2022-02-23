package com.example.studentCrud.service;

import com.example.studentCrud.dto.CourseDto;
import com.example.studentCrud.entity.Course;
import com.example.studentCrud.enums.RecordStatus;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CourseService {

    Map<String, Object> getList(String courseName, Integer page, Integer size);

    List<Course> findAll();

    Optional<Course> findById(Long id, RecordStatus recordStatus);

    Optional<Course> findByName(String name);

    Course insertCourse(CourseDto dto, RecordStatus recordStatus);

    Course update(CourseDto dto, RecordStatus recordStatus);



}
