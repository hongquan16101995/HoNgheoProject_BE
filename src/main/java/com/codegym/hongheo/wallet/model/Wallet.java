package com.codegym.hongheo.wallet.model;

import com.codegym.hongheo.core.model.entity.User;
import com.codegym.hongheo.moneytype.model.MoneyType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<MoneyType> moneyTypes = new HashSet<>();
}
