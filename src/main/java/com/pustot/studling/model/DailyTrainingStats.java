package com.pustot.studling.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("daily_training_stats")
public class DailyTrainingStats {
    @TableId
    private int userId;
    private String languageCode;
    private java.sql.Date trainingDate;
    private int totalAttempts;
    private int correctAttempts;
    private int incorrectAttempts;
}