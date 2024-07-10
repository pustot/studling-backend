package com.pustot.studling.dto;

import lombok.Data;

@Data
public class TrainingResultDTO {
    private String userEmail;
    private String languageCode;
    private int wordId;
    // 写 isCorrect 会被误认？
    // 目前，需要前端传 0 1 而不是原生 false true，相信由于Spring底层
    private boolean correct;
}