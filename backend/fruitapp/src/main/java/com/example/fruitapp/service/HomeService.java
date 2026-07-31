package com.example.fruitapp.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.example.fruitapp.repository.ProductRepository;
import com.example.fruitapp.entity.Product;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.example.fruitapp.dto.response.HomeResponse;
import com.example.fruitapp.dto.response.ProductResponse;
import com.example.fruitapp.repository.OrderItemRepository;
import com.example.fruitapp.mapper.ProductMapper;

@Service
@RequiredArgsConstructor
public class HomeService {
    private final ProductRepository productRepo;
    private final OrderItemRepository orderItemRepo;

    public HomeResponse getHome(Long userId) {
        HomeResponse response = new HomeResponse();
        response.setFlashSale(getFlashSale());
        response.setBestSeller(getBestSeller());
        response.setNewProducts(getNewProducts());
        response.setRecommended(getRecommended(userId));

        return response;
    }

    // flashsale
    public List<ProductResponse> getFlashSale() {
        return productRepo.getFlashSaleProducts()
                .stream()
                .map(ProductMapper::fromEntity)
                .collect(Collectors.toList());

    }

    // best seller
    public List<ProductResponse> getBestSeller() {

        List<Object[]> data = orderItemRepo.findBestSellerIds();

        List<Integer> ids = data.stream()
                .map(r -> ((Number) r[0]).intValue())
                .collect(Collectors.toList());

        List<Product> products = productRepo.findByIdIn(ids);

        Map<Integer, Product> map = products.stream()
                .collect(Collectors.toMap(Product::getId, p -> p));

        return ids.stream()
                .map(map::get)
                .map(ProductMapper::fromEntity)
                .collect(Collectors.toList());
    }

    // recommend
    public List<ProductResponse> getRecommended(Long userId) {

        if (userHasOrder(userId)) {

            // lấy category hay mua nhất
            Integer topCategory = getTopCategory(userId);

            return productRepo
                    .findTop10ByCategoryIdAndIsActiveTrue(topCategory)
                    .stream()
                    .map(ProductMapper::fromEntity)
                    .collect(Collectors.toList());
        }

        // fallback
        return productRepo
                .findTop10ByIsActiveTrueOrderByIdDesc()
                .stream() 
                .map(ProductMapper::fromEntity)
                .collect(Collectors.toList());
    }

    // new product
    public List<ProductResponse> getNewProducts() {

        return productRepo
                .findTop10ByIsActiveTrueOrderByCreatedAtDesc()
                .stream()
                .map(ProductMapper::fromEntity)
                .collect(Collectors.toList());
    }

    private boolean userHasOrder(Long userId) {
        if (userId == null) {
            return false;
        }
        return orderItemRepo.countByOrderUserId(userId) > 0;
    }

    private Integer getTopCategory(Long userId) {
        // This will return the category ID that the user has purchased from the most.
        return orderItemRepo.findTopCategoryIdByUserId(userId);
    }

}
