package com.pustot.studling;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

// 等等再来配置 Security
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@MapperScan("com.pustot.studling.repository")
public class StudlingApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudlingApplication.class, args);
	}

}
