package com.example.fruitapp.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

import com.example.fruitapp.repository.ProductRepository;
import com.example.fruitapp.dto.response.ProductResponse;
import com.example.fruitapp.mapper.ProductMapper;
import com.example.fruitapp.entity.Product;
import com.example.fruitapp.entity.ProductImage;
import com.example.fruitapp.dto.request.ProductRequest;

import java.util.List;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepo;

    //get all products
    public List<ProductResponse> getAllProducts(){
        return productRepo.findAll()
               .stream()
               .map(ProductMapper::mapProductResponse)
               .toList();
    }

    //get product by id
    public ProductResponse getProductById(Integer id){
        Product product = productRepo.findById(id)
               .orElseThrow(() -> new RuntimeException("Product not found"));
        return ProductMapper.mapProductResponse(product);
    }
    
    //find products by name
    public List<ProductResponse> searchProducts(String keyword){
        return productRepo
               .findByNameContainingIgnoreCase(keyword)
               .stream()
                .map(ProductMapper::mapProductResponse)
                .toList();
    }

    //add product
    public ProductResponse addProduct(ProductRequest req){

        Product pd = Product.builder()
                .name(req.getName())
                .description(req.getDescription())
                .price(req.getPrice())
                .stock(req.getStock())
                .category(req.getCategory())
                .build();

        if(req.getImgUrl()!=null){
            List<ProductImage> imgs =IntStream.range(0, req.getImgUrl().size())
                    .mapToObj(i -> 
                        ProductImage.builder()
                                    .product(pd)
                                    .imageUrl(req.getImgUrl().get(i))
                                    .isPrimary(i==0)
                                    .sortOrder(i)
                                    .build() 
                                ) .toList();
                                pd.setImages(imgs);  
               }
            
        Product saveProduct = productRepo.save(pd);
        return ProductMapper.mapProductResponse(saveProduct);
    }

    //update product
    public ProductResponse updateProduct(Integer id, ProductRequest req){
        Product pd = productRepo.findById(id)
                .orElseThrow(() -> 
                new RuntimeException("Product not found"));

                pd.setName(req.getName());
                pd.setDescription(req.getDescription());
                pd.setPrice(req.getPrice());
                pd.setStock(req.getStock());
                pd.setCategory(req.getCategory());

                //delete img old
                pd.getImages().clear();

                //add img new
                if(req.getImgUrl()!=null){
                    List<ProductImage> imgs = 
                    IntStream.range(0, req.getImgUrl().size())
                            .mapToObj(i->
                                ProductImage.builder()
                                        .product(pd)
                                        .imageUrl(req.getImgUrl().get(i))
                                        .isPrimary(i==0)
                                        .sortOrder(i)
                                        .build()   
                            )   
                            .toList();
                    pd.getImages().addAll(imgs);
                }
                Product updateProduct = productRepo.save(pd);

                return ProductMapper.mapProductResponse(updateProduct);
    }

    //delete product
    public void deleteProduct(Integer id){
        if(!productRepo.existsById(id)){
            throw new RuntimeException("Product not found");
        }

        productRepo.deleteById(id);
    }

}
