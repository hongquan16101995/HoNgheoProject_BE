package com.codegym.hongheo.category.controller;

import com.codegym.hongheo.category.model.Category;
import com.codegym.hongheo.category.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private ICategoryService iCategoryService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Category>> findAllCategories() {
        return new ResponseEntity<>(iCategoryService.findAll(), HttpStatus.OK);
    }
}
