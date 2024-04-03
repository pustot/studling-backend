package com.pustot.studling.repository;

import com.pustot.studling.model.User;
import com.pustot.studling.model.Word;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // 这里可以根据需要添加自定义的数据库操作方法
}