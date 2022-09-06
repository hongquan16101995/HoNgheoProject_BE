package com.codegym.hongheo.core.mapper.impl;

import com.codegym.hongheo.category.model.dto.CategoryDTO;
import com.codegym.hongheo.category.model.entity.Category;
import com.codegym.hongheo.category.service.ICategoryService;
import com.codegym.hongheo.core.mapper.IMapper;
import com.codegym.hongheo.core.model.dto.UserDTO;
import com.codegym.hongheo.core.model.entity.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryMapper implements IMapper<Category, CategoryDTO> {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private IMapper<User, UserDTO> iMapperUser;

    @Override
    public Category convertToE(CategoryDTO categoryDTO) {
        Category category = modelMapper.map(categoryDTO, Category.class);
        if (categoryDTO.getUserDTO() != null) {
            User user = modelMapper.map(categoryDTO.getUserDTO(), User.class);
            category.setUser(user);
        } else {
            category.setUser(null);
        }
        return category;
    }

    @Override
    public CategoryDTO convertToD(Category category) {
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        if (category.getUser() != null) {
            UserDTO userDTO = iMapperUser.convertToD(category.getUser());
            categoryDTO.setUserDTO(userDTO);
        } else {
            categoryDTO.setUserDTO(null);
        }
        return categoryDTO;
    }
}
