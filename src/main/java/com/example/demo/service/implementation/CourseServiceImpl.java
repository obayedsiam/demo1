package com.example.demo.service.implementation;

import com.example.demo.dto.CourseDto;
import com.example.demo.entity.Course;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.helper.CourseHelper;
import com.example.demo.repository.CourseRepository;
import com.example.demo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

private final CourseRepository repository;
private final CourseHelper helper;
private final EntityManager em;


    @Override
    public Map<String, Object> getList(String name, Integer page, Integer size) {
        return null;
    }

    @Override
    public List<Course> findAll() {
        return null;
    }

    @Override
    public Optional<Course> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Optional<Course> findByName(String name) {
        return Optional.empty();
    }

    @Override
    @Transactional
    public Course save(CourseDto dto) {
       Course course = dto.to();
       helper.getSaveData(course);
       Course saveCourse = repository.save(course);
       return saveCourse;
    }

    @Override
    @Transactional
    public Course update(CourseDto dto, String status) {
        Course course = repository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Course Id: " + dto.getId()));

        dto.update(course);
        helper.getUpdateData(course, status);

        Course updatedCourse = repository.save(course);
        return updatedCourse;
    }

}
