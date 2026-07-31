package com.example.fruitapp.security;
import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.fruitapp.entity.User;
import com.example.fruitapp.repository.AuthRepository;
import com.example.fruitapp.service.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;



@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final AuthRepository authRepo;

    @Override
protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {

    String path = request.getRequestURI();

    // ✅ BỎ QUA AUTH API
    if (path.startsWith("/api/auth/")) {
        filterChain.doFilter(request, response);
        return;
    }

    String header = request.getHeader("Authorization");

    if (header == null || !header.startsWith("Bearer ")) {
        filterChain.doFilter(request, response);
        return;
    }
    System.out.println("PATH = " + request.getRequestURI());
System.out.println("METHOD = " + request.getMethod());
    String token = header.substring(7);
    String email = jwtService.extractEmail(token);

    if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        User u = authRepo.findByEmail(email).orElse(null);

        if (u != null && jwtService.isTokenValid(token, u.getEmail())) {
            // Tạo danh sách quyền từ vai trò của người dùng
            List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(u.getRole().getName().name()));

            // Đưa toàn bộ đối tượng User vào principal và cung cấp danh sách quyền
            UsernamePasswordAuthenticationToken authToken =
                    new UsernamePasswordAuthenticationToken(u, null, authorities);

            SecurityContextHolder.getContext().setAuthentication(authToken);
        }
    }

    filterChain.doFilter(request, response);
}

}