package com.codegym.hongheo.category.controller;

import com.codegym.hongheo.category.model.dto.CategoryDTO;
import com.codegym.hongheo.category.model.entity.Category;
import com.codegym.hongheo.category.service.ICategoryService;
import com.codegym.hongheo.category.service.impl.CategoryService;
import com.codegym.hongheo.core.mapper.IMapper;
import com.codegym.hongheo.core.model.dto.UserDTO;
import com.codegym.hongheo.core.model.entity.User;
import com.codegym.hongheo.core.security.UserDetail;
import com.codegym.hongheo.core.service.user.IUserService;
import com.sun.security.auth.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private IUserService iUserService;

    @Autowired
    private IMapper<Category, CategoryDTO> iMapperCategory;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDTO>> findAllCategories() {
        return ResponseEntity.ok(iCategoryService.findAll());
    }

    @RequestMapping(value = "/{user_id}", method = RequestMethod.POST)
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO,
                                                      @PathVariable("user_id") Long userId) {
        return ResponseEntity.ok(iCategoryService.save(categoryDTO));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") Long id,
                                                      @RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(iCategoryService.save(categoryDTO));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) {
        iCategoryService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable("id") Long id) {
        CategoryDTO categoryDTO = iCategoryService.findById(id);
        return ResponseEntity.ok(categoryDTO);
    }

    @RequestMapping(value = "/user/{user_id}", method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDTO>> findAllCategoriesByUser(@PathVariable("user_id") Long userId) {

        return new ResponseEntity<>(categoryService.findAllCategoryByUser(userId), HttpStatus.OK);
    }
}
