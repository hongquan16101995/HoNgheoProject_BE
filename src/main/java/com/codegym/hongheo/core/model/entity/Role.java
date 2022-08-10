package com.codegym.hongheo.core.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(10)")
    private String name;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Permission> permissions;
}
