package com.example.studentCrud.dto;

import com.example.studentCrud.entity.Course;
import com.example.studentCrud.entity.Enclosure;
import com.sun.istack.NotNull;

import java.util.List;

public class StudentDto {

    @NotNull
    private Long id;

    private String name;

    private List<Course> courseList;

    private Enclosure enclosure;

}
