package com.pustot.studling.model;

import jakarta.persistence.*;

@Entity
// JPA 默认用其小写 word 为表名，或指定 @Table(name = "words")
@Table(name = "words")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "word_id")
    private Long id;

    @Column(name = "word_name")
    private String wordName;

    @Column(name = "word_roma")
    private String wordRoma;

    @Column(name = "word_meaning")
    private String wordMeaning;

    // 构造函数、getter和setter省略
    public Word() {
    }

    public Word(String wordName, String wordRoma, String wordMeaning) {
        this.wordName = wordName;
        this.wordRoma = wordRoma;
        this.wordMeaning = wordMeaning;
    }
}
