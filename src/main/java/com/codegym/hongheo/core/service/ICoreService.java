package com.codegym.hongheo.core.service;


import java.util.List;
import java.util.Optional;

public interface ICoreService<T, K>{
    List<T> findAll();

    T findById(K id);

    T save(T t);

    void remove(K id);
}
