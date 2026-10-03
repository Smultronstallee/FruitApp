package com.example.fruitapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponse {
    private Integer id;
    private String name;
    private String description;
    private BigDecimal price;
    private Integer stock;
    private Boolean isActive;
    private String categoryName;
    private String unit;
    private Double weight;
    private Double rating;
    private Integer soldCount;
    private List<String> imageUrls;

}
