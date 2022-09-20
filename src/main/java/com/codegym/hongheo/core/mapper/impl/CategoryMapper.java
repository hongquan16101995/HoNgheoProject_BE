package com.codegym.hongheo.core.mapper.impl;

import com.codegym.hongheo.category.model.dto.CategoryDTO;
import com.codegym.hongheo.category.model.entity.Category;
import com.codegym.hongheo.core.mapper.IMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper implements IMapper<Category, CategoryDTO> {
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public Category convertToE(CategoryDTO categoryDTO) {
        return modelMapper.map(categoryDTO, Category.class);
    }

    @Override
    public CategoryDTO convertToD(Category category) {
        return modelMapper.map(category, CategoryDTO.class);
    }
}
