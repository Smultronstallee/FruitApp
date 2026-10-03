package com.example.fruitapp.dto.request;

import lombok.Data;

@Data 
public class CategoryRequest {
    private String name;
    private String image;
    private String description;
    private String publicId;
}
