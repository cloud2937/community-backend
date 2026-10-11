package com.example.community;

import com.example.community.entity.User;
import com.example.community.mapper.UserMapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CommunityBackendApplicationTests {

    @Autowired
    private UserMapper userMapper;

	@Test
	void testDatabaseConnection() {
        System.out.println("---------开始测试数据库连接---------------");
        List<User> users = userMapper.selectList(null);
        System.out.println("查询成功！目前用户表里有" + users.size() + "条数据。");
	}

}
