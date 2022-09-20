package com.codegym.hongheo.wallet.model;

import com.codegym.hongheo.core.model.entity.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "wallet")
@Getter
@Setter
@RequiredArgsConstructor
public class Wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(255)")
    private String icon;

    @Column(columnDefinition = "varchar(50)")
    private String name;

    @Column(columnDefinition = "int default 1")
    private int status;

    @Column(columnDefinition = "double")
    private double money;

    @ManyToOne(fetch = FetchType.EAGER)
    private User user;
}
