package com.example.demo.entity;

import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@Table(name = "course")

public class Course
{
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private long id;

    @Column(name = "courseName")
    private String courseName;

}
