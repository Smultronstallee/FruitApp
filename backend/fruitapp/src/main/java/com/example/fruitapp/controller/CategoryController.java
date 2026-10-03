package com.example.fruitapp.controller;

import java.util.List;
import java.util.Locale.Category;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import com.example.fruitapp.dto.request.CategoryRequest;
import com.example.fruitapp.dto.response.CategoryResponse;
import com.example.fruitapp.service.CategoryService;

@RestController 
@RequestMapping("/api/categories")
@RequiredArgsConstructor 
public class CategoryController {
    private final CategoryService categoryService;

    //get all categories
    @GetMapping 
    public ResponseEntity<List<CategoryResponse>> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    //get category by id
    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable Integer id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    //add category
    @PostMapping 
    public ResponseEntity<CategoryResponse> addCategory(@RequestBody CategoryRequest req){
        return ResponseEntity.ok(categoryService.addCategory(req));
    }

    //update category
    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> updateCategory(
        @PathVariable Integer id, @RequestBody CategoryRequest req){
            return ResponseEntity.ok(categoryService.updateCategory(id, req));
        }
    
    //delete category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id){
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }

}
