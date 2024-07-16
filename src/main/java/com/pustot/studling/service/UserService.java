package com.pustot.studling.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pustot.studling.config.JsonRedisTemplate;
import com.pustot.studling.config.RabbitMQConfig;
import com.pustot.studling.model.User;
import com.pustot.studling.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JsonRedisTemplate jsonRedisTemplate;

    @Autowired
    private RabbitMQSender rabbitMQSender;

    @Autowired
    private RabbitMQConfig rabbitMQConfig;

    private static final String REDIS_KEY_PREFIX = "User:";

    public User findByEmail(String email) {
        String redisKey = REDIS_KEY_PREFIX + "email:" + email;
        User user = (User) jsonRedisTemplate.opsForValue().get(redisKey);

        if (user == null) {
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("email", email);
            user = userMapper.selectOne(queryWrapper);

            if (user != null) {
                jsonRedisTemplate.opsForValue().set(redisKey, user, 1, TimeUnit.HOURS);
            }
        }

        return user;
    }

//    public User findByEmail(String email) {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("email", email);
//        return userMapper.selectOne(queryWrapper);
//    }

    // TODO: 更新用户信息时，用 MQ 让 Redis 更新信息
    // 创建用户时，时效性要求高，直接更新Redis缓存；更新用户信息时，时效性要求低，交由MQ处理
    public void saveOrUpdateUserIfNull(User user) {
        User existingUser = findByEmail(user.getEmail());
        String redisKey = REDIS_KEY_PREFIX + "email:" + user.getEmail();
        if (existingUser == null) { // 创建用户
            try {
                userMapper.insert(user);
                jsonRedisTemplate.opsForValue().set(redisKey, user, 1, TimeUnit.HOURS);
            } catch (DuplicateKeyException e) {
                // 捕获并忽略重复键错误
                // 由于并发引起，但说明已经成功记录了这个user，故不必多处理
                // 记录日志
                System.out.println("User with email " + user.getEmail() + " already exists. Ignoring insert.");
            }
        }else { // 用户已存在，若数据有变，更新用户
            if (!Objects.equals(existingUser.getCognitoSub(), user.getCognitoSub())) {
                existingUser.setCognitoSub(user.getCognitoSub());
                userMapper.updateById(existingUser);
                rabbitMQSender.send(rabbitMQConfig.exchange().getName(), "user.update", existingUser);
            }
        }
    }

    public void updateUserInfo(User user) throws Exception {
//        ByteArrayOutputStream bo = new ByteArrayOutputStream();
//        ObjectOutputStream oo = new ObjectOutputStream(bo);
//        oo.writeObject(user);
//        byte[] javaByte = bo.toByteArray();
        String jsonUser = new ObjectMapper().writeValueAsString(user);
        System.out.println("sent " + jsonUser);
        rabbitMQSender.send(rabbitMQConfig.exchange().getName(), "user.update-username", jsonUser);
    }
}