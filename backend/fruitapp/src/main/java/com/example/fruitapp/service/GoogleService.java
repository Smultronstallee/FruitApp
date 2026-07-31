package com.example.fruitapp.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.example.fruitapp.dto.response.AuthResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;

import java.security.GeneralSecurityException;
import java.util.Collections;



@Service
@RequiredArgsConstructor
public class GoogleService {
    @Value("${google.client-id}")
    private String GOOGLE_CLIENT_ID;

    public AuthResponse verify(String idToken)
    {

        try {

            GoogleIdTokenVerifier verifier =
                new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(),
                    new GsonFactory()
                )
                .setAudience(
                    Collections.singletonList(
                        GOOGLE_CLIENT_ID
                    )
                )
                .build();


            GoogleIdToken token =
                verifier.verify(idToken);


            if(token == null){
                throw new RuntimeException(
                    "Invalid Google token"
                );
            }


            GoogleIdToken.Payload payload = token.getPayload();
            System.out.println("Google email: " + payload.getEmail());
            System.out.println("Google subject: " + payload.getSubject());

            return AuthResponse.builder()
                .email(payload.getEmail())
                .userName((String) payload.get("name"))
                .socialId(payload.getSubject())
                .build();

        } catch (GeneralSecurityException e) {
            // Lỗi bảo mật, ví dụ: chứng chỉ không hợp lệ
            throw new RuntimeException("Lỗi bảo mật khi xác thực Google token: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            // Lỗi khi idToken là null hoặc không hợp lệ
            throw new RuntimeException("Google ID token không hợp lệ: " + e.getMessage(), e);
        } catch (Exception e) { // Bắt các lỗi khác, ví dụ: IOException
            throw new RuntimeException("Xác thực Google thất bại: " + e.getMessage(), e);
        }

    }
    
}
