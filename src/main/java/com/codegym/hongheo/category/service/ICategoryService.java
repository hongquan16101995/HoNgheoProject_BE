package com.codegym.hongheo.category.service;

import com.codegym.hongheo.category.model.dto.CategoryDTO;
import com.codegym.hongheo.category.model.entity.Category;
import com.codegym.hongheo.core.model.dto.UserDTO;
import com.codegym.hongheo.core.model.entity.User;
import com.codegym.hongheo.core.service.ICoreService;

import java.util.List;

public interface ICategoryService extends ICoreService<CategoryDTO, Long> {
    List<CategoryDTO> findAllByUser(UserDTO user);
}
