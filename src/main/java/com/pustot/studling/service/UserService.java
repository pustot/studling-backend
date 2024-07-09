package com.pustot.studling.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.pustot.studling.model.User;
import com.pustot.studling.repository.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findByEmail(String email) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("email", email);
        return userMapper.selectOne(queryWrapper);
    }

    public void saveOrUpdateUser(User user) {
        User existingUser = findByEmail(user.getEmail());
        if (existingUser == null) {
            try {
                userMapper.insert(user);
            } catch (DuplicateKeyException e) {
                // 捕获并忽略重复键错误
                // 由于并发引起，但说明已经成功记录了这个user，故不必多处理
                // 记录日志
                System.out.println("User with email " + user.getEmail() + " already exists. Ignoring insert.");
            }
        }else {
            existingUser.setCognitoSub(user.getCognitoSub());
            userMapper.updateById(existingUser);
        }
    }
}