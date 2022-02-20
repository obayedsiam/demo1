package com.example.demo.validation;

import com.example.demo.dto.CourseDto;
import com.example.demo.entity.Course;
import com.example.demo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CourseValidator implements Validator {


    private final CourseService service;
    @Override
    public boolean supports(Class<?> clazz) {
        return CourseDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        CourseDto dto = (CourseDto) target;
        if(dto.getName().isEmpty()){
            Optional<Course> course = service.findByName(dto.getName());
            if(course.isPresent()){
                errors.rejectValue("name", null , "Already Exists");
            }
        }

    }
}
