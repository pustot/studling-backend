package com.pustot.studling.controller;

import com.pustot.studling.dto.UserDTO;
import com.pustot.studling.model.User;
import com.pustot.studling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    // TODO: 把创建和更新分开，创建直连后端，更新交给MQ
    @PutMapping("/users")
    public void saveOrUpdateUserIfNull(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setCognitoSub(userDTO.getCognitoSub());
        userService.saveOrUpdateUserIfNull(user);
    }

    @PutMapping("/users/update-info")
    public void updateUserInfo(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        // TODO: 仅供测试，未来加 username 字段
        user.setCognitoSub(userDTO.getUsername());
        try {
            userService.updateUserInfo(user);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}