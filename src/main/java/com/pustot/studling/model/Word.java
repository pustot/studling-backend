package com.pustot.studling.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
// JPA 默认用其小写 word 为表名，或指定 @Table(name = "words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text; // 单词文本
    private String definition; // 单词释义

    // 构造函数、getter和setter省略
}
