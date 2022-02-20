package com.example.demo.entity;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
@Table(name = "student")
public class Student
{
    @Id
    @Autowired
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column (name = "student_name")
    private String studentName;

    @Column(name = "courseList")
    private ArrayList<Course> CourseList;


}
