package com.example.community.controller;

import com.example.community.common.Result;
import com.example.community.datatransferobject.RegisterRequest;
import com.example.community.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
