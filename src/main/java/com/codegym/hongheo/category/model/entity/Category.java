package com.codegym.hongheo.category.model.entity;

import com.codegym.hongheo.core.model.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "category")
@Getter
@Setter
@RequiredArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50)")
    private String name;

    @Column(columnDefinition = "varchar(255)")
    private String color;

    @Column(columnDefinition = "longtext")
    private String description;

    @Column(columnDefinition = "boolean default true")
    private boolean status;

    @Column(columnDefinition = "datetime")
    private LocalDateTime deleteAt;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}
