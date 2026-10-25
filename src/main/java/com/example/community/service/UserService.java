package com.example.community.service;

import com.example.community.datatransferobject.LoginRequest;
import com.example.community.datatransferobject.LoginResponse;
import com.example.community.datatransferobject.RegisterRequest;

public interface UserService {

    void register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}
