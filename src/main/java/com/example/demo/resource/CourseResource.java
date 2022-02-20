package com.example.demo.resource;

import com.example.demo.service.CourseService;
import com.example.demo.validation.CourseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class CourseResource {

    private final CourseValidator validator;

    private final CourseService service;


}
