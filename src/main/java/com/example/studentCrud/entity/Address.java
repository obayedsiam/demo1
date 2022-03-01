package com.example.studentCrud.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Data
@Entity
@RequiredArgsConstructor
@Table(name = "ADDRESS")
public class Address extends BaseEntity{

    @Id
    @Autowired
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ADDRESS_ID")
    private Long id;

    @Column(name = "LOCATION")
    private String location;

}
