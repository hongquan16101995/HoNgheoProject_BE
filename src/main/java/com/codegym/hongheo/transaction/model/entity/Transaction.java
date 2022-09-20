package com.codegym.hongheo.transaction.model.entity;

import com.codegym.hongheo.category.model.entity.Category;
import com.codegym.hongheo.wallet.model.Wallet;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "transaction")
@Getter
@Setter
@RequiredArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "double")
    private double total;

    @Column(columnDefinition = "datetime")
    private LocalDateTime time;

    @Column(columnDefinition = "longtext")
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Category> categories = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    private Wallet wallet;
}
