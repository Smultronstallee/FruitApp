package com.example.fruitapp.mapper;

import com.example.fruitapp.entity.Category;

import org.springframework.stereotype.Component;

import com.example.fruitapp.dto.response.CategoryResponse;

@Component 
public class CategoryMapper {
    public static CategoryResponse mapCategoryResponse(Category ca){
        if(ca==null) return null;
        return CategoryResponse.builder()
                .id(ca.getId())
                .name(ca.getName())
                .image(ca.getImage())
                .description(ca.getDescription())
                .build();
    }
}
