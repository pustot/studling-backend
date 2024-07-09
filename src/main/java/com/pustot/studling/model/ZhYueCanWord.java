package com.pustot.studling.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("zh_yue_can_words")
public class ZhYueCanWord {

    @TableId(value = "word_id", type = IdType.AUTO)
    private Integer wordId;

    private String word;

    private String pronunciation;

    private String meaning;

    private String exampleCombination;

    private String exampleSentence;

    private LocalDateTime createdAt;
}
