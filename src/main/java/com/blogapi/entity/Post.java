package com.blogapi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="posts",uniqueConstraints = {@UniqueConstraint(columnNames = {"title"} )})
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="title",nullable = false)
    private String title;

    @Column(name="descriptions",nullable = false)
    private String descriptions;

    @Column(name="content",nullable = false)
    private String content;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "post")
    private List<Comment>comments=new ArrayList<>();
}
