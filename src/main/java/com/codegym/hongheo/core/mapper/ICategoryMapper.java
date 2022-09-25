package com.codegym.hongheo.core.mapper;

import com.codegym.hongheo.category.model.dto.CategoryDTO;
import com.codegym.hongheo.category.model.entity.Category;
import org.mapstruct.Mapper;

@Mapper
public interface ICategoryMapper extends EntityMapper<CategoryDTO, Category>{
}
