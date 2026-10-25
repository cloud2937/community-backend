package com.example.community.controller;

import com.example.community.common.Result;
import com.example.community.datatransferobject.LoginRequest;
import com.example.community.datatransferobject.LoginResponse;
import com.example.community.datatransferobject.RegisterRequest;
import com.example.community.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public Result<Void> register(@RequestBody @Valid RegisterRequest request) {

        userService.register(request);

        return Result.success();
    }

    @PostMapping("/login")
    public Result<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        LoginResponse response = userService.login(request);
        return Result.success(response);
    }

    @GetMapping("/me")
    public Result<Long> getMyId(jakarta.servlet.http.HttpServletRequest request) {
        Long userId=(Long)request.getAttribute("userId");
        return Result.success(userId);
    }

}
