package com.example.community;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.community.mapper")
public class CommunityBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommunityBackendApplication.class, args);
	}



}
