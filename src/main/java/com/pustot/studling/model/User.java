package com.pustot.studling.model;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email")
    private String email;

    public User() {
    }

    public User(String userName, String email) {
        this.userName = userName;
        this.email = email;
    }

    // private String password; // 密码，实际开发中应加密存储

    // 构造函数、getter和setter省略
}
