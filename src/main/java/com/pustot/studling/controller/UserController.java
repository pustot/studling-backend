package com.pustot.studling.controller;

import com.pustot.studling.dto.UserDTO;
import com.pustot.studling.model.User;
import com.pustot.studling.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/users")
    public void upsertUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setEmail(userDTO.getEmail());
        user.setCognitoSub(userDTO.getCognitoSub());
        userService.saveOrUpdateUser(user);
    }
}