package com.codegym.hongheo.moneytype.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "money_type")
@Getter
@Setter
@RequiredArgsConstructor
public class MoneyType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "varchar(50)", unique = true, nullable = false)
    private String name;

    @Column(columnDefinition = "double")
    private double rate;
}
