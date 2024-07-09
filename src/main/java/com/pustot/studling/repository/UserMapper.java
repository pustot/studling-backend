package com.pustot.studling.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pustot.studling.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Insert("INSERT INTO users (email, cognito_sub) VALUES (#{email}, #{cognitoSub}) " +
            "ON DUPLICATE KEY UPDATE cognito_sub = VALUES(cognito_sub)")
    boolean insertOrUpdate(User user);
}
