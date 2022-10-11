package com.codegym.hongheo.category.controller;

import com.codegym.hongheo.category.model.dto.CategoryDTO;
import com.codegym.hongheo.category.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDTO>> findAllCategories() {
        return new ResponseEntity<>(iCategoryService.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<>(iCategoryService.save(categoryDTO), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CategoryDTO> updateCategory(@PathVariable("id") Long id,
                                                      @RequestBody CategoryDTO categoryDTO) {
        categoryDTO.setId(id);
        return new ResponseEntity<>(iCategoryService.save(categoryDTO), HttpStatus.OK);

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCategory(@PathVariable("id") Long id) {
        iCategoryService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<CategoryDTO> findCategoryById(@PathVariable("id") Long id) {
        Optional<CategoryDTO> categoryDTOOptional = iCategoryService.findById(id);
        return categoryDTOOptional.map(categoryDTO -> new ResponseEntity<>(categoryDTO, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDTO>> findAllCategoriesByUser() {
        return new ResponseEntity<>(iCategoryService.findAllByUser(), HttpStatus.OK);
    }

    @RequestMapping(value = "/user_active", method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDTO>> findAllCategoriesByUserAndStatus() {
        return new ResponseEntity<>(iCategoryService.findAllByUserAndStatus(), HttpStatus.OK);
    }


    @RequestMapping(value = "/status/{id}", method = RequestMethod.GET)
    public ResponseEntity<Void> updateStatusCategory(@PathVariable("id") Long id) {
        iCategoryService.isActive(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
