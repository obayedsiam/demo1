package com.example.studentCrud.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "STUDENT")
public class Student
{
    @Id
    @Column(name = "STUDENT_ID")
    private Long id;

    @Column (name = "STUDENT_NAME")
    private String name;

    @OneToMany
    @JoinColumn(name = "STUDENT_ID")
    private List<Course> courseList;

    @OneToOne
    @JoinColumn(name = "STUDENT_ID")
    private Enclosure enclosure;

}
