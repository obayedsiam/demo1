package com.example.demo.entity;

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
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STUDENT_ID")
    private Long id;

    @Column (name = "STUDENT_NAME")
    private String name;

    @OneToMany
    @JoinColumn(name = "STUDENT_ID")
    private List<Course> courseList;

    @OneToMany
    @JoinColumn(name = "STUDENT_ID")
    private List<Enclosure> enclosureList;

}
