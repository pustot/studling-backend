package com.pustot.studling.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pustot.studling.config.JsonRedisTemplate;
import com.pustot.studling.model.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class UserMessageListener {

    @Autowired
    private JsonRedisTemplate jsonRedisTemplate;

    private static final String REDIS_KEY_PREFIX = "User:";

    @RabbitListener(queues = "user.queue")
    public void handleUserMessage(String jsonMessage) {
        try {
            System.out.println("received " + jsonMessage);
            ObjectMapper objectMapper = new ObjectMapper();
            User user = objectMapper.readValue(jsonMessage, User.class);
            // 先更新下 DB
            System.out.println("MySQL 更新了 " + user.getCognitoSub());

            // 更新 Redis
            String redisKey = REDIS_KEY_PREFIX + "email:" + user.getEmail();
            jsonRedisTemplate.opsForValue().set(redisKey, user, 1, TimeUnit.HOURS);
            System.out.println("Redis 更新了 " + user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
