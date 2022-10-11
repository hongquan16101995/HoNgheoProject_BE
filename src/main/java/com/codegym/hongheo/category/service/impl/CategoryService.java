package com.codegym.hongheo.category.service.impl;

import com.codegym.hongheo.category.model.dto.CategoryDTO;
import com.codegym.hongheo.category.model.entity.Category;
import com.codegym.hongheo.category.repository.ICategoryRepository;
import com.codegym.hongheo.category.service.ICategoryService;
import com.codegym.hongheo.core.mapper.ICategoryMapper;
import com.codegym.hongheo.core.service.auth.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository iCategoryRepository;

    @Autowired
    private ICategoryMapper iCategoryMapper;

    @Autowired
    private JwtService jwtService;

    @Override
    public List<CategoryDTO> findAll() {
        List<Category> categories = iCategoryRepository.findAll();
        return iCategoryMapper.toDto(categories);
    }

    @Override
    public Optional<CategoryDTO> findById(Long id) {
        Optional<Category> categoryOptional = iCategoryRepository.findById(id);
        CategoryDTO categoryDTO = null;
        if (categoryOptional.isPresent()) {
            categoryDTO = iCategoryMapper.toDto(categoryOptional.get());
        }
        return Optional.ofNullable(categoryDTO);
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        if (categoryDTO.getId() != null) {
            Optional<Category> categoryOptional = iCategoryRepository.findById(categoryDTO.getId());
            if (!categoryOptional.isPresent()) {
                return null;
            }
        }
        Category category = iCategoryMapper.toEntity(categoryDTO);
        category.setUser(jwtService.getUserWithJwt());
        return iCategoryMapper.toDto(iCategoryRepository.save(category));
    }

    @Override
    public void remove(Long id) {
        Optional<Category> categoryOptional = iCategoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            categoryOptional.get().setDeleteAt(LocalDateTime.now());
            iCategoryRepository.save(categoryOptional.get());
        }
    }


    @Override
    public List<CategoryDTO> findAllByUser() {
        Long userId = jwtService.getIdOfUserWithJwt();
        return iCategoryMapper.toDto(iCategoryRepository.findAllByUser(userId));
    }

    @Override
    public List<CategoryDTO> findAllByUserAndStatus() {
        Long userId = jwtService.getIdOfUserWithJwt();
        return iCategoryMapper.toDto(iCategoryRepository.findAllByUserAndStatus(userId));
    }

    @Override
    public void isActive(Long id) {
        Optional<Category> categoryOptional = iCategoryRepository.findById(id);
        if (categoryOptional.isPresent()) {
            categoryOptional.get().setStatus(!categoryOptional.get().isStatus());
            iCategoryRepository.save(categoryOptional.get());
        }
    }
}
