package com.example.fruitapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.utils.ObjectUtils;
import com.cloudinary.Cloudinary;

import java.io.IOException;
import java.util.Map;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CloudinaryService {
    private final Cloudinary cloudinary;

    @SuppressWarnings("unchecked")
    public String uploadFile(MultipartFile file) throws IOException{
        Map<String, Object> uploadResult = cloudinary.uploader().upload(
            file.getBytes(),
            ObjectUtils.asMap("resource_type", "auto")
        );
        return uploadResult.get("secure_url").toString();
    }

    public void deleteFile(String publicId){
        try{
            cloudinary.uploader().destroy(publicId, ObjectUtils.emptyMap());

        } catch(IOException e){
            throw new RuntimeException("Failed to delete image from cloudinary", e);
        }
    }
    
}
