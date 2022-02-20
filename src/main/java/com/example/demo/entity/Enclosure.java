package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.File;

@Entity
public class Enclosure
{
    @Id
    private long id;
    private File enclosureName;

}
