package com.codegym.hongheo.core.model.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50)", unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(columnDefinition = "varchar(50)")
    private String name;

    @Column(columnDefinition = "varchar(10)")
    private String phone;

    @Column(columnDefinition = "int default 1")
    private int status;

    private String avatar;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();
}
