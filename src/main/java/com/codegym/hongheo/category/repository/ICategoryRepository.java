package com.codegym.hongheo.category.repository;

import com.codegym.hongheo.category.model.entity.Category;
import com.codegym.hongheo.core.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByUser(User user);

    List<Category> findAllByUserAndStatus(User user, int status);
}
