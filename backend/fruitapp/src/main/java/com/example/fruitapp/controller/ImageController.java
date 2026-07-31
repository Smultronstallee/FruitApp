package com.example.fruitapp.controller;

import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

import com.example.fruitapp.service.CloudinaryService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import lombok.RequiredArgsConstructor; 


@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor //
public class ImageController {
    
    private final CloudinaryService cloudinaryService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImg(@RequestParam("file") MultipartFile file){
        try{
            String url = cloudinaryService.uploadFile(file);
            return ResponseEntity.ok(Map.of("url", url));
        }catch (IOException e){
            return ResponseEntity.status(500).body("Upload failed:"+ e.getMessage());
        }
    }
}
