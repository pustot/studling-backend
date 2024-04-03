package com.pustot.studling.model;

import jakarta.persistence.*;

@Entity
public class TrainingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user; // 关联的用户

    @ManyToOne
    @JoinColumn(name = "word_id")
    private Word word; // 关联的单词

    private int trainingTimes; // 训练次数
    private int correctTimes; // 正确次数

    // 构造函数、getter和setter省略
}
