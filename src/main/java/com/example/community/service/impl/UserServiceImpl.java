package com.example.community.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.community.datatransferobject.LoginRequest;
import com.example.community.datatransferobject.LoginResponse;
import com.example.community.datatransferobject.RegisterRequest;
import com.example.community.entity.User;
import com.example.community.mapper.UserMapper;
import com.example.community.service.UserService;
import com.example.community.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void register(RegisterRequest request) {
        Long count = userMapper.selectCount(
                new LambdaQueryWrapper<User>().eq(User::getUsername,request.getUsername())
        );
        if(count>0) {
            throw new IllegalArgumentException("用户名已存在");
        }

        User user = new User();
        user.setUsername(request.getUsername());

        user.setPassword(passwordEncoder.encode(request.getPassword()));

        user.setEmail(request.getEmail());

        user.setStatus(1);
        user.setFansCount(0);
        user.setFollowCount(0);

        userMapper.insert(user);
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user=userMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getUsername,request.getUsername()));

        if(user==null) {
            throw new IllegalArgumentException("用户名或密码错误!");
        }

        if(user.getStatus()!=null && user.getStatus()==0) {
            throw new IllegalArgumentException("该账号已被封禁");
        }

        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())) {
            throw new IllegalArgumentException("用户名或密码错误");
        }

        String token=jwtUtil.generateToken(user.getId(),user.getUsername());

        return new LoginResponse(
                token,
                user.getId(),
                user.getUsername(),
                user.getAvatar()
        );

    }
}
