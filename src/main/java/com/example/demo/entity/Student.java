package com.example.demo.entity;

import com.sun.istack.NotNull;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "STUDENT")
public class Student
{
    @Id
    @Autowired
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

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    public List<Enclosure> getEnclosureList() {
        return enclosureList;
    }

    public void setEnclosureList(List<Enclosure> enclosureList) {
        this.enclosureList = enclosureList;
    }
}
