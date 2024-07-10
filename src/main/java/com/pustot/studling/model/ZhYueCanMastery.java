package com.pustot.studling.model;

import lombok.Data;

@Data
public class ZhYueCanMastery {
    private int userId;
    private int wordId;
    private String recentResults;
    private float accuracy;
    private java.sql.Timestamp lastAttemptDate;
}