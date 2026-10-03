package com.example.fruitapp.dto.request;

import java.math.BigDecimal;
import com.example.fruitapp.entity.Category;
import java.util.List;
import lombok.Data;

@Data 

public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Category category;
    private List<String> imgUrl;
}
