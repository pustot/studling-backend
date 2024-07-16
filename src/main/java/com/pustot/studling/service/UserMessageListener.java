package com.pustot.studling.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pustot.studling.config.JsonRedisTemplate;
import com.pustot.studling.model.User;
import com.pustot.studling.repository.UserMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

@Service
public class UserMessageListener {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private JsonRedisTemplate jsonRedisTemplate;

    private static final String REDIS_KEY_PREFIX = "User:";

    @RabbitListener(queues = "user.queue")
    public void handleUserInfoMessage(String jsonMessage) {
        try {
            System.out.println("received " + jsonMessage);
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(jsonMessage, User.class);
            User existingUser = userService.findByEmail(user.getEmail());
            // 检查 Info 字段至少一个非空，是 DB 里面 updateInfo 方法真正会更新的字段
            if (existingUser != null && (user.getUsername() != null)) {

                user.setUserId(existingUser.getUserId());

                // 非 info 字段，其实仅用于 Redis 缓存
                user.setCognitoSub(existingUser.getCognitoSub());
                user.setCreatedAt(existingUser.getCreatedAt());
                user.setUpdatedAt(LocalDateTime.now());
                // 先更新下 DB
                userMapper.updateUserInfo(user);
                System.out.println("MySQL 更新了 " + user.getUsername() + " 于 " + user.getUserId());

                // 更新 Redis
                String redisKey = REDIS_KEY_PREFIX + "email:" + user.getEmail();
                jsonRedisTemplate.opsForValue().set(redisKey, user, 1, TimeUnit.HOURS);
                System.out.println("Redis 更新了 " + user.getEmail());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
