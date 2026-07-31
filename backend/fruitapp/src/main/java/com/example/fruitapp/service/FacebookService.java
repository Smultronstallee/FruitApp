package com.example.fruitapp.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.HttpClientErrorException;

import com.example.fruitapp.dto.response.AuthResponse;

import java.util.Map;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FacebookService {

    public AuthResponse verify(
            String accessToken) {

        String url = "https://graph.facebook.com/me"
                + "?fields=id,name,email"
                + "&access_token="
                + accessToken;

        RestTemplate restTemplate = new RestTemplate();

        try {
            // Lấy phản hồi dưới dạng Map để dễ dàng trích xuất các trường
            Map<String, Object> facebookResponse = (Map<String, Object>) restTemplate.getForObject(url, Map.class);
            System.out.println("Facebook Response = " + facebookResponse);

            if (facebookResponse == null || !facebookResponse.containsKey("id")) {
                throw new RuntimeException("Invalid Facebook token or missing ID.");
            }

            AuthResponse authResponse = new AuthResponse();
            authResponse.setSocialId((String) facebookResponse.get("id"));
            authResponse.setEmail((String) facebookResponse.get("email"));
            authResponse.setUserName((String) facebookResponse.get("name"));

            System.out.println("ID = " + facebookResponse.get("id"));
            System.out.println("NAME = " + facebookResponse.get("name"));
            System.out.println("EMAIL = " + facebookResponse.get("email"));

            return authResponse;

        } catch (HttpClientErrorException e) {
            // Ném ngoại lệ rõ ràng hơn khi token không hợp lệ
            throw new RuntimeException("Facebook verification failed: " + e.getResponseBodyAsString(), e);
        } catch (Exception e) {
            throw new RuntimeException("An error occurred during Facebook verification.", e);
        }
    }
}
