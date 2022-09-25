package com.codegym.hongheo.category.controller;

import com.codegym.hongheo.category.model.dto.CategoryDTO;
import com.codegym.hongheo.category.model.entity.Category;
import com.codegym.hongheo.category.service.ICategoryService;
import com.codegym.hongheo.core.mapper.ICategoryMapper;
import com.codegym.hongheo.core.model.entity.User;
import com.codegym.hongheo.core.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private ICategoryMapper iCategoryMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDTO>> findAllCategories() {
        List<Category> categories = iCategoryService.findAll();
        List<CategoryDTO> categoryDTOS = iCategoryMapper.toDto(categories);
        return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
    }

    @RequestMapping(value = "/{user_id}", method = RequestMethod.POST)
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO,
                                                      @PathVariable("user_id") Long userId) {
        Optional<User> userOptional = iUserService.findById(userId);
        if (userOptional.isPresent()) {
            Category category = iCategoryMapper.toEntity(categoryDTO);
            category.setUser(userOptional.get());
            return new ResponseEntity<>(iCategoryMapper.toDto(iCategoryService.save(category)), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") Long id,
                                                      @RequestBody CategoryDTO categoryDTO) {
        Optional<Category> categoryOptional = iCategoryService.findById(id);
        if (categoryOptional.isPresent()) {
            Category category = iCategoryMapper.toEntity(categoryDTO);
            category.setId(id);
            category.setUser(categoryOptional.get().getUser());
            return new ResponseEntity<>(iCategoryMapper.toDto(iCategoryService.save(category)), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) {
        Optional<Category> categoryOptional = iCategoryService.findById(id);
        if (categoryOptional.isPresent()) {
            iCategoryService.remove(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable("id") Long id) {
        Optional<Category> categoryOptional = iCategoryService.findById(id);
        return categoryOptional.map(category -> new ResponseEntity<>(iCategoryMapper.toDto(category), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/user/{user_id}", method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDTO>> findAllCategoriesByUser(@PathVariable("user_id") Long userId) {
        Optional<User> userOptional = iUserService.findById(userId);
        if (userOptional.isPresent()) {
            List<Category> categories = iCategoryService.findAllByUser(userOptional.get());
            List<CategoryDTO> categoryDTOS = iCategoryMapper.toDto(categories);
            return new ResponseEntity<>(categoryDTOS, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
