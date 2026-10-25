package com.example.community.interceptor;

import com.example.community.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    public JwtInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil=jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            return true;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("未登录，请先登录");
        }

        String token = authHeader.substring(7);

        try {
            Long userId = jwtUtil.getUserIdFromToken(token);

            request.setAttribute("userId", userId);

            return true;
        } catch (Exception e) {
            throw new IllegalArgumentException("登录已过期或token无效，请重新登录");
        }
    }
}
