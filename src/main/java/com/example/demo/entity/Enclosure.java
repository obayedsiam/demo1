package com.example.demo.entity;

import com.sun.istack.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Entity
@Table(name = "ENCLOSURE")
public class Enclosure
{
    @Id
    @Autowired
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ENCLOSURE_ID")
    private Long id;

    @Column(name = "ENCLOSURE_NAME")
    private String name;

    @Column(name = "ENCLOSURE_URL")
    private String url;


    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
}
