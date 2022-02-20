package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT", updatable = false)
    protected Date createdAt;

    @Version
    @JsonIgnore
    @Column (name = "RECORD_VERSION")
    private Integer recordVersion;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "RECORD_STATUS")
    private  String recordStatus;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_AT")
    protected Date updatedAt;


    @PrePersist
    public void prePersist() {
        this.createdAt = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = new Date();
    }




}
