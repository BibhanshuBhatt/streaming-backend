package com.stream.app.entities;

import jakarta.persistence.*;

@Entity
@Table(name="streamapp-courses")
public class Course {
    @Id
    private String id;
    private String title ;
//    @OneToMany(mappedBy = "course")
//    private List<video> list=new ArrayList<>();
}
