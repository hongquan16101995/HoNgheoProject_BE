package com.codegym.hongheo.category.service.impl;

import com.codegym.hongheo.category.model.dto.CategoryDTO;
import com.codegym.hongheo.category.model.entity.Category;
import com.codegym.hongheo.category.repository.ICategoryRepository;
import com.codegym.hongheo.category.service.ICategoryService;
import com.codegym.hongheo.core.exception.NotFoundException;
import com.codegym.hongheo.core.mapper.CategoryMapper;
import com.codegym.hongheo.core.mapper.UserMapper;
import com.codegym.hongheo.core.model.dto.UserDTO;
import com.codegym.hongheo.core.model.entity.User;
import com.codegym.hongheo.core.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Autowired
    private UserService userService;

    @Override
    public List<CategoryDTO> findAll() {
        return categoryMapper.toDto(iCategoryRepository.findAll());
    }

    @Override
    public CategoryDTO findById(Long id) {
        Category category = iCategoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("category_notfound"));
        return categoryMapper.toDto(category);
    }

    @Override
    public CategoryDTO save(CategoryDTO dto) {
        return categoryMapper.toDto(iCategoryRepository.save(categoryMapper.toEntity(dto)));
    }

    @Override
    public void remove(Long id) {
        iCategoryRepository.deleteById(id);
    }

    @Override
    public List<CategoryDTO> findAllByUser(UserDTO user) {
        return categoryMapper.toDto(iCategoryRepository.findAllByUser(userMapper.toEntity(user)));
    }

    public List<CategoryDTO> findAllCategoryByUser(Long userId) {
        UserDTO userDTO = userService.findById(userId);
        return this.findAllByUser(userDTO);
    }
}
