package com.example.fruitapp.service;

import org.springframework.stereotype.Service;

import com.example.fruitapp.dto.request.CategoryRequest;
import com.example.fruitapp.dto.response.CategoryResponse;
import com.example.fruitapp.repository.CategoryRepository;
import com.example.fruitapp.mapper.CategoryMapper;
import com.example.fruitapp.entity.Category;
import java.util.List;

import lombok.RequiredArgsConstructor;

@Service 
@RequiredArgsConstructor 
public class CategoryService {
    private final CategoryRepository categoryRepo;
    private final CloudinaryService cloudinaryService;

    //get all categories
    public List<CategoryResponse> getAllCategories(){
        return categoryRepo.findAll()
               .stream()
               .map(CategoryMapper::mapCategoryResponse)
               .toList();
    }

    //get category by id
    public CategoryResponse getCategoryById(Integer id){
        Category ca = categoryRepo.findById(id)
                 .orElseThrow(() -> new RuntimeException("Category not found"));
        return  CategoryMapper.mapCategoryResponse(ca);
    }

    //add category
    public CategoryResponse addCategory(CategoryRequest req){
        Category ca = Category.builder()
                 .name(req.getName())
                 .image(req.getImage())
                 .description(req.getDescription())
                 .build();

        Category savedCategory = categoryRepo.save(ca);
        return  CategoryMapper.mapCategoryResponse(savedCategory);
    }

    //update category
    public CategoryResponse updateCategory(Integer id, CategoryRequest req){
        Category ca = categoryRepo.findById(id)
                 .orElseThrow(() ->
                new RuntimeException("Category not found"));

                //new image
                if(req.getImage() != null && !req.getImage().isBlank()){
                    //delete img old
                    if(ca.getPublicId() != null && !ca.getPublicId().isBlank()){
                        cloudinaryService.deleteFile(ca.getPublicId());
                    }

                    ca.setImage(req.getImage());
                    ca.setPublicId(req.getPublicId());
                }

                ca.setName(req.getName());
                ca.setDescription(req.getDescription());
                
                Category updatedCategory = categoryRepo.save(ca);
                return CategoryMapper.mapCategoryResponse(updatedCategory);
    }

    //delete product
    public void deleteCategory(Integer id){
        if(!categoryRepo.existsById(id)){
            throw new RuntimeException("Category not found");
        }
        categoryRepo.deleteById(id);
    }
    
}
