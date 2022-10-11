package com.codegym.hongheo.category.repository;

import com.codegym.hongheo.category.model.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "select * from category where user_id = :id",nativeQuery = true)
    List<Category> findAllByUser(@Param("id") Long id);

    @Query(value = "select * from category where user_id = :id and status = 1",nativeQuery = true)
    List<Category> findAllByUserAndStatus(@Param("id") Long id);
}
