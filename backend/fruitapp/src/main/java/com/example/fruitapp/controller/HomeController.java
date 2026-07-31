package com.example.fruitapp.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.fruitapp.entity.User;
import com.example.fruitapp.dto.response.HomeResponse;
import com.example.fruitapp.service.HomeService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
public class HomeController {
    private final HomeService homeService;

    @GetMapping
    public HomeResponse getHome(@AuthenticationPrincipal User user) {
        // Nếu user chưa đăng nhập, user sẽ là null.
        Long userId = (user != null) ? (long) user.getId() : null;
        return homeService.getHome(userId);
    }
    
}
