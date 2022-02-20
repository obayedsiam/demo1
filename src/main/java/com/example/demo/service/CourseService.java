package com.example.demo.service;

import com.example.demo.dto.CourseDto;
import com.example.demo.entity.Course;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CourseService {

    Map<String, Object> getList(String courseName, Integer page, Integer size);

    List<Course> findAll();

    Optional<Course> findById(Integer id);

    Optional<Course> findByName(String name);

    Course save(CourseDto dto);

    Course update(CourseDto dto, String status);


}
