package com.example.fruitapp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HomeResponse {
    private List<ProductResponse> flashSale;

    private List<ProductResponse> bestSeller;

    private List<ProductResponse> recommended;

    private List<ProductResponse> newProducts;
    
}
