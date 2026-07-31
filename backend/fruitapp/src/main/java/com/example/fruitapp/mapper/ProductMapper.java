package com.example.fruitapp.mapper;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;

import com.example.fruitapp.entity.Product;
import com.example.fruitapp.dto.response.ProductResponse;

@Component
@RequiredArgsConstructor
public class ProductMapper {
    public static ProductResponse fromEntity(Product product) {
        if (product == null) {
            return null;
        }
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .isActive(product.getIsActive())
                .categoryName(product.getCategory() != null ? product.getCategory().getName() : null)
                .imageUrls(product.getImages() != null ? product.getImages().stream()
                        .map(img -> img.getImageUrl())
                        .collect(Collectors.toList()) : null)
                .build();
    }
    
}
