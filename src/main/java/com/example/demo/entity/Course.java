package com.example.demo.entity;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.*;

@Entity
@Table(name = "COURSE")
public class Course {
    @Id
    @Autowired
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COURSE_ID")
    private Long id;

    @Column(name = "COURSE_NAME")
    private String name;

    @Column(name = "COURSE_DURATION")
    private String duration;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }


}
