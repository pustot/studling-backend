package com.pustot.studling.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pustot.studling.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Insert("INSERT INTO users (email, cognito_sub) VALUES (#{email}, #{cognitoSub}) " +
            "ON DUPLICATE KEY UPDATE cognito_sub = VALUES(cognito_sub)")
    boolean insertOrUpdate(User user);

    @Select("SELECT user_id FROM users WHERE email = #{email}")
    Integer findUserIdByEmail(String email);

    @Update("<script>" +
            "UPDATE users " +
            "<set>" +
            "<if test='username != null'>username = #{username}, </if>" +
            "updated_at = CURRENT_TIMESTAMP" +
            "</set>" +
            "WHERE user_id = #{userId} AND #{userId} IS NOT NULL" +
            "</script>")
    void updateUserInfo(User user);
}
