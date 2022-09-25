package com.codegym.hongheo.category.service;

import com.codegym.hongheo.category.model.entity.Category;
import com.codegym.hongheo.core.model.entity.User;
import com.codegym.hongheo.core.service.ICoreService;

import java.util.List;

public interface ICategoryService extends ICoreService<Category, Long> {
    List<Category> findAllByUser(User user);

    List<Category> findAllByUserAndStatus(User user);
}
