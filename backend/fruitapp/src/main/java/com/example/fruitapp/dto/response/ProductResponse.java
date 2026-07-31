package com.example.fruitapp.dto.response;

import com.example.fruitapp.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<String> imageUrls;

}
